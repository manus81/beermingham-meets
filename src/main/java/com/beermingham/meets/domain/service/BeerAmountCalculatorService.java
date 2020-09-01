package com.beermingham.meets.domain.service;

import com.beermingham.meets.domain.model.BeerAmountStrategyEnum;

public class BeerAmountCalculatorService {

	private final static Integer BOXES_AMOUNT = 6;

	public static Integer calculateBeerBoxes(Double beerAmount) {
		Double boxesAmount = beerAmount / BOXES_AMOUNT;
		if (beerAmount % BOXES_AMOUNT == 0) {
			return boxesAmount.intValue();
		}
		// Porque en caso que falte, siempre hay que comprar de mas
		return boxesAmount.intValue() + 1;
	}

	public static BeerAmountStrategyEnum getStrategyBeer(Double temperature) {
		BeerAmountStrategyEnum strategyCalculatorBeer = BeerAmountStrategyEnum.MEDIUM;
		if (temperature > 24)
			strategyCalculatorBeer = BeerAmountStrategyEnum.HIGH;
		else if (temperature < 20)
			strategyCalculatorBeer = BeerAmountStrategyEnum.MINIMUM;
		return strategyCalculatorBeer;
	}
}
