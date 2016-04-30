package com.github.mytravelsapp.persistence.converter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.converter.Converter;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.helper.JsonHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelConverter implements Converter<TravelDto, Travel> {

    @Inject
    public TravelConverter() {
    }

    public Travel convert(TravelDto source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Travel target = new Travel();
        target.setDestination(source.getDestination());
        target.setFinishDate(source.getFinishDate());
        target.setId(source.getId());
        target.setName(source.getName());
        target.setStartDate(source.getStartDate());

        if (!Utils.isEmpty(source.getDaysPlanningMap())) {
            target.setDaysPlanning(JsonHelper.toJson(source.getDaysPlanningMap()));
        }
        return target;
    }

    public List<Travel> convert(List<TravelDto> sourceList) {
        List<Travel> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final TravelDto source : sourceList) {
                resultList.add(convert(source));
            }
        }
        return resultList;
    }

    public TravelDto convertToDto(Travel source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelDto target = new TravelDto();
        target.setDestination(source.getDestination());
        target.setFinishDate(source.getFinishDate());
        target.setId(source.getId());
        target.setName(source.getName());
        target.setStartDate(source.getStartDate());

        if (!Utils.isEmpty(source.getDaysPlanning())) {

            final Type mapType = new TypeToken<Map<Date, List<TravelDayPlanningDto>>>() {
            }.getType();
            final Map<Date, List<TravelDayPlanningDto>> daysMap = JsonHelper.fromJson(source.getDaysPlanning(), mapType);
            target.setDaysPlanningMap(daysMap);
        }

        return target;
    }

    public List<TravelDto> convertToDto(List<Travel> sourceList) {
        List<TravelDto> resultList;
        if (Utils.isEmpty(sourceList)) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final Travel source : sourceList) {
                resultList.add(convertToDto(source));
            }
        }
        return resultList;
    }
}
