package com.github.mytravelsapp.presentation.model;

import java.util.Date;

/**
 * @author fjtorres
 */
public class TravelModel {
    private final long id;

    private String name;

    private String destination;

    private Date startDate;

    private Date finishDate;

    public TravelModel(final long pId) {
        this.id = pId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
