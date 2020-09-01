package com.beermingham.meets.application.dto;

public class WeatherDTO {

	private MainDTO main;

	public MainDTO getMain() {
		return main;
	}

	public void setMain(MainDTO main) {
		this.main = main;
	}

	public Double getTemp() {
		return main != null ? main.getTemp() : null;
	}

	/** main inner class */
	public class MainDTO {

		private Double temp;
		private Double pressure;
		private Integer humidity;
		private Double tempMin;
		private Double tempMax;

		public Double getTemp() {
			return temp;
		}

		public void setTemp(Double temp) {
			this.temp = temp;
		}

		public Double getPressure() {
			return pressure;
		}

		public void setPressure(Double pressure) {
			this.pressure = pressure;
		}

		public Integer getHumidity() {
			return humidity;
		}

		public void setHumidity(Integer humidity) {
			this.humidity = humidity;
		}

		public Double getTempMin() {
			return tempMin;
		}

		public void setTempMin(Double tempMin) {
			this.tempMin = tempMin;
		}

		public Double getTempMax() {
			return tempMax;
		}

		public void setTempMax(Double tempMax) {
			this.tempMax = tempMax;
		}
	}
}
