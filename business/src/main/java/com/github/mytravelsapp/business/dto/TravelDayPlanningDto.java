package com.github.mytravelsapp.business.dto;

import com.github.mytravelsapp.business.Utils;

import java.util.Date;
import java.util.Objects;

/**
 * @author fjtorres
 */
public class TravelDayPlanningDto implements Dto {

    private Long travelPlaceId;

    private Date day;

    private int order;

    public TravelDayPlanningDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelDayPlanningDto)) return false;
        TravelDayPlanningDto that = (TravelDayPlanningDto) o;
        return Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getTravelPlaceId(), that.getTravelPlaceId()) &&
                Objects.equals(getDay(), that.getDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTravelPlaceId(), getDay(), getOrder());
    }

    public Long getTravelPlaceId() {
        return travelPlaceId;
    }

    public void setTravelPlaceId(Long travelPlaceId) {
        this.travelPlaceId = travelPlaceId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
