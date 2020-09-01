package com.beermingham.meets.application.dto;

public class BeerDTO {

    private Integer beerBoxes;

    public BeerDTO() {
    }

    public BeerDTO(Integer beerBoxes) {
        this.beerBoxes = beerBoxes;
    }

    public Integer getBeerBoxes() {
        return beerBoxes;
    }

    public void setBeerBoxes(Integer beerBoxes) {
        this.beerBoxes = beerBoxes;
    }
}
