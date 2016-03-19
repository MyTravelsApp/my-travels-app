package com.github.mytravelsapp.persistence.helper;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author fjtorres
 */
public class JsonHelper {

    public static TravelDayPlanningDto createDayPlanning(final JSONObject json) throws JSONException {
        final TravelDayPlanningDto dto = new TravelDayPlanningDto();
        dto.setDay(Utils.parseDate(json.getString("day"), Utils.DATE_FORMAT));
        dto.setOrder(json.getInt("order"));
        dto.setTravelPlaceId(json.getLong("travelPlaceId"));
        return dto;
    }

    public static JSONObject convertDayPlanning(final TravelDayPlanningDto dto) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("travelPlaceId", dto.getTravelPlaceId());
        json.put("day", Utils.formatDate(dto.getDay(), Utils.DATE_FORMAT));
        json.put("order", dto.getOrder());
        return json;
    }
}
