package com.github.mytravelsapp.presentation.converter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
import com.github.mytravelsapp.business.dto.TravelDestinationDto;
import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.presentation.model.TravelDayPlanningModel;
import com.github.mytravelsapp.presentation.model.TravelDestinationModel;
import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelModelConverter {

    @Inject
    public TravelModelConverter() {
    }

    public TravelModel convert(TravelDto source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelModel target = new TravelModel(source.getId());
        target.setDestination(convertTravelDestinationToModel(source.getDestination()));
        target.setFinishDate(source.getFinishDate());
        target.setName(source.getName());
        target.setStartDate(source.getStartDate());
        if (!Utils.isEmpty(source.getDaysPlanningMap())) {
            target.setDaysPlanningMap(new HashMap<Date, List<TravelDayPlanningModel>>());

            for (final Map.Entry<Date, List<TravelDayPlanningDto>> entry : source.getDaysPlanningMap().entrySet()) {
                if (!target.getDaysPlanningMap().containsKey(entry.getKey())) {
                    target.getDaysPlanningMap().put(entry.getKey(), new ArrayList<TravelDayPlanningModel>());
                }

                for (final TravelDayPlanningDto dayPlanningDto : entry.getValue()) {
                    target.getDaysPlanningMap().get(entry.getKey()).add(convertDayPlanningToModel(dayPlanningDto));
                }
            }
        }


        return target;
    }

    public List<TravelModel> convert(List<TravelDto> sourceList) {
        List<TravelModel> resultList;
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

    public TravelDto convertToDto(TravelModel source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelDto target = new TravelDto();
        target.setDestination(convertTravelDestinationToDto(source.getDestination()));
        target.setFinishDate(source.getFinishDate());
        target.setId(source.getId());
        target.setName(source.getName());
        target.setStartDate(source.getStartDate());

        if (!Utils.isEmpty(source.getDaysPlanningMap())) {
            target.setDaysPlanningMap(new HashMap<Date, List<TravelDayPlanningDto>>());

            for (final Map.Entry<Date, List<TravelDayPlanningModel>> entry : source.getDaysPlanningMap().entrySet()) {
                if (!target.getDaysPlanningMap().containsKey(entry.getKey())) {
                    target.getDaysPlanningMap().put(entry.getKey(), new ArrayList<TravelDayPlanningDto>());
                }

                for (final TravelDayPlanningModel dayPlanningModel : entry.getValue()) {
                    target.getDaysPlanningMap().get(entry.getKey()).add(convertDayPlanningToDto(dayPlanningModel));
                }
            }
        }

        return target;
    }

    public List<TravelDto> convertToDto(List<TravelModel> sourceList) {
        List<TravelDto> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final TravelModel source : sourceList) {
                resultList.add(convertToDto(source));
            }
        }
        return resultList;
    }

    private TravelDayPlanningDto convertDayPlanningToDto(final TravelDayPlanningModel source) {
        final TravelDayPlanningDto target = new TravelDayPlanningDto();
        target.setDay(source.getDay());
        target.setTravelPlaceId(source.getTravelPlaceId());
        target.setOrder(source.getOrder());

        return target;
    }

    private TravelDayPlanningModel convertDayPlanningToModel(final TravelDayPlanningDto source) {
        final TravelDayPlanningModel target = new TravelDayPlanningModel();
        target.setDay(source.getDay());
        target.setTravelPlaceId(source.getTravelPlaceId());
        target.setOrder(source.getOrder());

        return target;
    }

    private TravelDestinationDto convertTravelDestinationToDto(final TravelDestinationModel source) {
        TravelDestinationDto target = null;
        if (source != null) {
            target = new TravelDestinationDto();
            target.setDestinationPlaceId(source.getDestinationPlaceId());
            target.setDestinationPlaceName(source.getDestinationPlaceName());
            target.setDestinationPlaceLatitude(source.getDestinationPlaceLatitude());
            target.setDestinationPlaceLongitude(source.getDestinationPlaceLongitude());
        }
        return target;
    }

    private TravelDestinationModel convertTravelDestinationToModel(final TravelDestinationDto source) {
        TravelDestinationModel target = null;
        if (source != null) {
            target = new TravelDestinationModel();
            target.setDestinationPlaceId(source.getDestinationPlaceId());
            target.setDestinationPlaceName(source.getDestinationPlaceName());
            target.setDestinationPlaceLatitude(source.getDestinationPlaceLatitude());
            target.setDestinationPlaceLongitude(source.getDestinationPlaceLongitude());
        }
        return target;
    }
}
