package com.beermingham.meets.domain.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.beermingham.meets.domain.exception.ValidationException;
import com.beermingham.meets.domain.service.BeerAmountCalculatorService;

public class Meet {

	private String id;
	private Double temperature;
	private BeerAmountStrategyEnum strategy;
	private LocalDate date;
	private Double beersAmount;
	private Set<User> guests = new HashSet<>();
	private boolean closed;

	/** Only for instance via reflection */
	@SuppressWarnings("unused")
	private Meet() {
	}

	public Meet(List<String> guestIds, LocalDate meetDate, Double temp) {
		this.id = UUID.randomUUID().toString();
		this.date = meetDate;
		Double temperature = temp;
		this.temperature = temperature;
		BeerAmountStrategyEnum strategy = BeerAmountCalculatorService.getStrategyBeer(temperature);
		this.strategy = strategy;
		Set<User> meetGuests = guestIds.stream().map(u -> new User(u)).collect(Collectors.toSet());
		this.addGuests(meetGuests);
	}

	public String getId() {
		return id;
	}

	public Double getTemperature() {
		return temperature;
	}

	public LocalDate getDate() {
		return date;
	}

	public Double getBeersAmount() {
		return beersAmount;
	}

	public Set<User> getGuests() {
		return Collections.unmodifiableSet(this.guests);
	}

	public boolean addGuests(Set<User> guests) {
		if (guests != null) {
			return this.guests.addAll(guests);
		}
		return false;
	}

	public boolean addGuest(User guest) {
		if (closed) {
			throw new ValidationException("Meet [" + id + "] is closed");
		}
		
		return this.guests.add(guest);
	}

	public BeerAmountStrategyEnum getStrategy() {
		return strategy;
	}

	public boolean isClosed() {
		return closed;
	}

	public void close() {
		beersAmount = this.strategy.calculateBeerAmount(this.guests.size());
		this.closed = true;
	}

	public void userChecking(String userId) {
		if (!closed) {
			throw new ValidationException("Meet [" + id + "] is not closed");
		}

		Optional<User> user = this.getGuests().stream().filter(u -> u.getId().equals(userId)).findFirst();
		if (!user.isPresent()) {
			throw new ValidationException("User [" + userId + "] of meet [" + id + "] not found");
		}

		user.get().checkin();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
