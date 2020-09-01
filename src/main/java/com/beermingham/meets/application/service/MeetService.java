package com.beermingham.meets.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beermingham.meets.application.dto.BeerDTO;
import com.beermingham.meets.application.dto.MeetDTO;
import com.beermingham.meets.application.dto.TemperatureDTO;
import com.beermingham.meets.application.dto.WeatherDTO;
import com.beermingham.meets.application.exception.DuplicateEntityException;
import com.beermingham.meets.application.exception.ValidationException;
import com.beermingham.meets.domain.model.Meet;
import com.beermingham.meets.domain.model.User;
import com.beermingham.meets.domain.repository.IMeetRepository;
import com.beermingham.meets.domain.service.BeerAmountCalculatorService;

@Service
public class MeetService implements IMeetService {

	private IMeetRepository meetRepository;
	private IWeatherService weatherService;

	@Autowired
	public MeetService(IMeetRepository meetRepository, IWeatherService weatherService) {
		this.meetRepository = meetRepository;
		this.weatherService = weatherService;
	}

	public Meet createNewMeet(MeetDTO meetDTO) {

		if (this.meetRepository.existsByDate(meetDTO.getDate())) {
			throw new DuplicateEntityException("A meet has already saved for this date: " + meetDTO.getDate());
		}

		WeatherDTO weatherDTO = weatherService.getWeatherInfo();
		Meet meet = new Meet(meetDTO.getGuestIds(), meetDTO.getDate(), weatherDTO.getTemp());
		return meetRepository.save(meet);
	}

	public void closeMeet(String meetingId) {
		Meet meet = meetRepository.get(meetingId);
		meet.close();
		meetRepository.save(meet);
	}

	public TemperatureDTO getTemperature(String meetingId) {
		Meet meet = meetRepository.get(meetingId);
		return new TemperatureDTO(meet.getTemperature());
	}

	public BeerDTO getBeerBoxesAmount(String meetingId) {
		Meet meet = meetRepository.get(meetingId);

		if (!meet.isClosed()) {
			throw new ValidationException(String.format("The meet wih id: %s is not closed", meet.getId()));
		}

		Integer beerBoxesAmount = BeerAmountCalculatorService.calculateBeerBoxes(meet.getBeersAmount());
		return new BeerDTO(beerBoxesAmount);
	}

	public List<Meet> getAllMeets() {
		return meetRepository.getAll();
	}

	public void addUser(String meetId, String userId) {
		Meet meet = this.meetRepository.get(meetId);
		meet.addGuest(new User(userId));
		meetRepository.save(meet);
	}

	public void checkinUser(String meetId, String userId) {
		Meet meet = this.meetRepository.get(meetId);
		meet.userChecking(userId);
		meetRepository.save(meet);
	}
}
