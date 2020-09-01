package com.beermingham.meets.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.beermingham.meets.application.dto.BeerDTO;
import com.beermingham.meets.application.dto.MeetDTO;
import com.beermingham.meets.application.dto.TemperatureDTO;
import com.beermingham.meets.application.service.IMeetService;
import com.beermingham.meets.domain.model.Meet;

@RestController
@RequestMapping(value = "/meets")
public class MeetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetController.class);

	private IMeetService meetService;

	@Autowired
	public MeetController(IMeetService meetService) {
		this.meetService = meetService;
	}

	@GetMapping()
	public List<Meet> getAllMeets() {
		LOGGER.info("Getting all Meet Ups");
		return meetService.getAllMeets();
	}

	/**
	 * Punto 3 del enunciado
	 */
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public boolean saveMeet(@RequestBody MeetDTO meetDto) {
		LOGGER.info("Saving new Meet Up: {}", meetDto);
		return meetService.createNewMeet(meetDto) != null;
	}

	@PutMapping(value = "/{meet_id}/close")
	public void closeMeet(@PathVariable("meet_id") String meetId) {
		LOGGER.info("Closing meet up for id [{}]", meetId);
		meetService.closeMeet(meetId);
	}

	/**
	 * Punto 2 del enunciado
	 */
	@GetMapping(value = "/{meet_id}/temperature")
	public TemperatureDTO getTemperature(@PathVariable("meet_id") String meetId) {
		LOGGER.info("Getting temperature for meet up for id [{}]", meetId);
		TemperatureDTO temperatureDto = meetService.getTemperature(meetId);
		return temperatureDto;
	}

	/**
	 * Punto 1 del enunciado (la meet debe estar cerrada para poder calcularlo)
	 */
	@GetMapping(value = "{meet_id}/beers")
	public BeerDTO getBeers(@PathVariable("meet_id") String meetId) {
		LOGGER.info("Getting beers for meet up for id [{}]", meetId);
		BeerDTO beerDto = meetService.getBeerBoxesAmount(meetId);
		return beerDto;
	}

	/**
	 * Punto 4 del enunciado
	 */
	@PostMapping(value = "/{meet_id}/users/{user_id}/join")
	public void addUser(@PathVariable("meet_id") String meetId, @PathVariable("user_id") String userId) {
		LOGGER.info("Checking for userId: [{}] for meeting [{}]", meetId, userId);
		meetService.addUser(meetId, userId);
	}

	/**
	 * Punto 4 del enunciado
	 */
	@PostMapping(value = "/{meet_id}/users/{user_id}/checkin")
	public void checkinUser(@PathVariable("meet_id") String meetId, @PathVariable("user_id") String userId) {
		LOGGER.info("Checking for userId: [{}] for meeting [{}]", meetId, userId);
		meetService.checkinUser(meetId, userId);
	}
}
