package com.beermingham.meets.application.service;

import java.util.List;

import com.beermingham.meets.application.dto.BeerDTO;
import com.beermingham.meets.application.dto.MeetDTO;
import com.beermingham.meets.application.dto.TemperatureDTO;
import com.beermingham.meets.domain.model.Meet;

public interface IMeetService {

	public Meet createNewMeet(MeetDTO meetDTO);

	public void closeMeet(String meetingId);

	public TemperatureDTO getTemperature(String meetingId);

	public BeerDTO getBeerBoxesAmount(String meetingId);

	public List<Meet> getAllMeets();

	void addUser(String meetId, String userId);

	void checkinUser(String meetId, String userId);
	
}
