package com.github.mytravelsapp.business.dto;

import java.util.Date;

/**
 * @author stefani
 */
public class TravelPlacesDto {

    private long id;
    private String name;
    private String category;
    private String observations;
    private TravelDto travelDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TravelPlacesDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public TravelDto getTravelDto() {
        return travelDto;
    }

    public void setTravelDto(TravelDto travelDto) {
        this.travelDto = travelDto;
    }
}
