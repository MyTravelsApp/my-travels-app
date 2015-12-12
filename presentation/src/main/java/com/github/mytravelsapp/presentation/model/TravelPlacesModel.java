package com.github.mytravelsapp.presentation.model;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesModel {

    public static final long DEFAULT_ID = -1;

    private final long id;

    private String place;

    public TravelPlacesModel(final long pId) {

        this.id=pId;
    }

    public TravelPlacesModel(String place, long id) {
        this.place = place;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}