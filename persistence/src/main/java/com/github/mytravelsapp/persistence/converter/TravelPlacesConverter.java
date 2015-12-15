package com.github.mytravelsapp.persistence.converter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.entity.TravelPlaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class TravelPlacesConverter {

    @Inject
    public TravelPlacesConverter() {
    }

    public TravelPlaces convert(TravelPlacesDto source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelPlaces target = new TravelPlaces();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setObservations(source.getObservations());
        target.setCategory(source.getCategory());
        target.setTravel(new Travel(source.getTravelId()));
        return target;
    }

    public List<TravelPlaces> convert(List<TravelPlacesDto> sourceList) {
        List<TravelPlaces> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final TravelPlacesDto source : sourceList) {
                resultList.add(convert(source));
            }
        }
        return resultList;
    }

    public TravelPlacesDto convertToDto(TravelPlaces source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelPlacesDto target = new TravelPlacesDto();
        target.setName(source.getName());
        target.setObservations(source.getObservations());
        target.setCategory(source.getCategory());
        target.setTravelId(source.getTravel().getId());
        target.setId(source.getId());
        return target;
    }

    public List<TravelPlacesDto> convertToDto(List<TravelPlaces> sourceList) {
        List<TravelPlacesDto> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final TravelPlaces source : sourceList) {
                resultList.add(convertToDto(source));
            }
        }
        return resultList;
    }
}
