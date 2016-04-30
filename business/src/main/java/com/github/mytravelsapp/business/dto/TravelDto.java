package com.github.mytravelsapp.business.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author fjtorres
 */
public class TravelDto implements Dto {

    private long id;

    private String name;

    private String destination;

    private Date startDate;

    private Date finishDate;

    private Map<Date, List<TravelDayPlanningDto>> daysPlanningMap;

    public TravelDto() {

    }

    public TravelDto(final long pId, final String pName, final String pDestination, final Date pStartDate, final Date pFinishDate) {
        this.id = pId;
        this.destination = pDestination;
        this.name = pName;
        this.startDate = pStartDate;
        this.finishDate = pFinishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelDto)) return false;
        TravelDto travelDto = (TravelDto) o;
        return Objects.equals(getId(), travelDto.getId()) &&
                Objects.equals(getName(), travelDto.getName()) &&
                Objects.equals(getDestination(), travelDto.getDestination()) &&
                Objects.equals(getStartDate(), travelDto.getStartDate()) &&
                Objects.equals(getFinishDate(), travelDto.getFinishDate()) &&
                Objects.equals(getDaysPlanningMap(), travelDto.getDaysPlanningMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDestination(), getStartDate(), getFinishDate(), getDaysPlanningMap());
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

    public Map<Date, List<TravelDayPlanningDto>> getDaysPlanningMap() {
        return daysPlanningMap;
    }

    public void setDaysPlanningMap(Map<Date, List<TravelDayPlanningDto>> daysPlanningMap) {
        this.daysPlanningMap = daysPlanningMap;
    }
}
