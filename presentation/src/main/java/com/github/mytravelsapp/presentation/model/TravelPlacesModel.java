package com.github.mytravelsapp.presentation.model;

import com.github.mytravelsapp.persistence.entity.Travel;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesModel {

    public static final long DEFAULT_ID = -1;
    private final long id;
    private String name;
    private String category;
    private String observations;
    private long travelId;


    public TravelPlacesModel(final long pId, final long pTravelId) {
        this.id = pId;
        this.travelId = pTravelId;
    }

    public TravelPlacesModel(long id, String name, long travelId, String category) {
        this.id = id;
        this.name = name;
        this.travelId = travelId;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
