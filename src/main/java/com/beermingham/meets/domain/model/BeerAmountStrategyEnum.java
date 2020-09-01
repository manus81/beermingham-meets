package com.beermingham.meets.domain.model;

public enum BeerAmountStrategyEnum {

	MINIMUM {
		@Override
		public Double getBeersAmount() {
			return Double.valueOf("0.75");
		}
	},
	MEDIUM {
		@Override
		public Double getBeersAmount() {
			return Double.valueOf("1");
		}
	},
	HIGH {
		@Override
		public Double getBeersAmount() {
			return Double.valueOf("2");
		}
	};

	public abstract Double getBeersAmount();

	public Double calculateBeerAmount(Integer guests) {
		return Double.valueOf(guests) * this.getBeersAmount();
	}
}
