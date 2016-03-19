package com.github.mytravelsapp.business.dto;

import java.util.Date;
import java.util.Locale;

/**
 * @author stefani
 */
public class TravelPlacesDto implements Dto {

    private long id;
    private String name;
    private CategoryDto categoryDto;
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

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
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
