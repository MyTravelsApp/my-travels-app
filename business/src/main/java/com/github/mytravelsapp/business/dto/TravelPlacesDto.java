package com.github.mytravelsapp.business.dto;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author stefani
 */
public class TravelPlacesDto implements Dto {

    private long id;
    private String name;
    private CategoryDto categoryDto;
    private String observations;
    private TravelDto travelDto;

    public TravelPlacesDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelPlacesDto)) return false;
        TravelPlacesDto that = (TravelPlacesDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCategoryDto(), that.getCategoryDto()) &&
                Objects.equals(getObservations(), that.getObservations()) &&
                Objects.equals(getTravelDto(), that.getTravelDto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCategoryDto(), getObservations(), getTravelDto());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
