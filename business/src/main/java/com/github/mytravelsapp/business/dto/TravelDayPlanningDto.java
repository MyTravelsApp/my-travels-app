package com.github.mytravelsapp.business.dto;

import com.github.mytravelsapp.business.Utils;

import java.util.Date;

/**
 * @author fjtorres
 */
public class TravelDayPlanningDto implements Dto {

    private Long travelPlaceId;

    private Date day;

    private int order;

    public TravelDayPlanningDto() {

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
