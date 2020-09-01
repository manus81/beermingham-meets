package com.beermingham.meets.application.dto;

public class TemperatureDTO {

    public Double temperature;

    public TemperatureDTO() {
    }

    public TemperatureDTO(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
