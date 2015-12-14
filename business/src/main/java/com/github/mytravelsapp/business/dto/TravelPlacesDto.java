package com.github.mytravelsapp.business.dto;

import java.util.Date;

/**
 * @author stefani
 */
public class TravelPlacesDto {

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public TravelPlacesDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
