package com.github.mytravelsapp.presentation.model;

import java.util.Date;

/**
 * @author fjtorres
 */
public class TravelModel {

    public static final long DEFAULT_ID = -1;

    private final long id;

    private String name;

    private String destination;

    private Date startDate;

    private Date finishDate;

    public TravelModel(final long pId) {
        this(pId, null, null);
    }

    public TravelModel(final long pId, final String pName, final String pDestination) {
        this(pId, pName, pDestination, null, null);
    }

    public TravelModel(final long pId, final String pName, final String pDestination, final Date pStartDate, final Date pFinishDate) {
        this.id = pId;
        this.destination = pDestination;
        this.name = pName;
        this.startDate = pStartDate;
        this.finishDate = pFinishDate;
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
