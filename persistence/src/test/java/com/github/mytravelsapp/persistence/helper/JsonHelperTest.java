package com.github.mytravelsapp.persistence.helper;

import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fjtorres
 */
public class JsonHelperTest {

    @Test
    public void mapTest () {
        final Map<Date, List<TravelDayPlanningDto>> testMap = new HashMap<>();

        final Date testDate = new Date();

        final TravelDayPlanningDto testDay = new TravelDayPlanningDto();
        testDay.setOrder(1);
        testDay.setTravelPlaceId(1L);
        testDay.setDay(testDate);

        List<TravelDayPlanningDto> testList = new ArrayList<>();
        testList.add(testDay);


        testMap.put(testDate, testList);


        final String json = JsonHelper.toJson(testMap);
        System.out.println(json);

        Type gsonType = new TypeToken<Map<Date, List<TravelDayPlanningDto>>>(){}.getType();

        final Map<Date, List<TravelDayPlanningDto>> result = JsonHelper.fromJson(json, gsonType);

        Assert.assertEquals(testMap, result);
    }
}
