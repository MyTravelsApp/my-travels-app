package com.github.mytravelsapp.persistence.converter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.converter.Converter;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.helper.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        if (!Utils.isEmpty(source.getDaysPlanning())) {
            try {
                JSONArray jsonArray = new JSONArray();
                for (final TravelDayPlanningDto dayPlanning : source.getDaysPlanning()) {
                    jsonArray.put(JsonHelper.convertDayPlanning(dayPlanning));
                }
                target.setDaysPlanning(jsonArray.toString());
            } catch (final JSONException e) {
                // FIXME ¿QUE HACEMOS?
            }
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
            target.setDaysPlanning(new ArrayList<TravelDayPlanningDto>());
            try {
                final JSONArray jsonArray = new JSONArray(source.getDaysPlanning());
                for (int i = 0; i <= jsonArray.length(); i++) {
                    final TravelDayPlanningDto dayPlanning = JsonHelper.createDayPlanning(jsonArray.getJSONObject(i));
                    target.getDaysPlanning().add(dayPlanning);
                }
            } catch (final JSONException e) {
                // FIXME ¿QUE HACEMOS?
            }
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
