package com.github.mytravelsapp.persistence.entity;

import java.io.Serializable;

/**
 * Created by stefani on 14/12/2015.
 */
public class TravelPlaces implements Serializable {

    private long id;
    private String name;

    public TravelPlaces() {

    }

    public TravelPlaces(long id, String name) {
        this.id = id;
        this.name = name;
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

}
