package com.github.mytravelsapp.business.dto;

import java.util.Date;
import java.util.List;

/**
 * @author fjtorres
 */
public class TravelDto implements Dto {

    private long id;

    private String name;

    private String destination;

    private Date startDate;

    private Date finishDate;

    private List<TravelDayPlanningDto> daysPlanning;

    public TravelDto() {

    }

    public TravelDto(final long pId, final String pName, final String pDestination, final Date pStartDate, final Date pFinishDate) {
        this.id = pId;
        this.destination = pDestination;
        this.name = pName;
        this.startDate = pStartDate;
        this.finishDate = pFinishDate;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public List<TravelDayPlanningDto> getDaysPlanning() {
        return daysPlanning;
    }

    public void setDaysPlanning(List<TravelDayPlanningDto> daysPlanning) {
        this.daysPlanning = daysPlanning;
    }
}
