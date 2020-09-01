package com.beermingham.meets.infrastructure.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.beermingham.meets.application.dto.WeatherDTO;
import com.beermingham.meets.application.service.IWeatherService;

@Component
@Qualifier("weatherService")
public class MockedWeatherService implements IWeatherService {

	public WeatherDTO getWeatherInfo() {
		
		@SuppressWarnings("deprecation")
		WeatherDTO weatherDto = MockUtils.mapObjectfromFile("mocks/WeatherResponse.json", WeatherDTO.class);
		
		return weatherDto;
	}
	
}
