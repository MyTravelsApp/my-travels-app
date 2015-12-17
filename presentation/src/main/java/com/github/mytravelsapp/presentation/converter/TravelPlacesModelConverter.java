package com.github.mytravelsapp.presentation.converter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.persistence.entity.TravelPlaces;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class TravelPlacesModelConverter {

    private final TravelModelConverter converterTravel;

    @Inject
    public TravelPlacesModelConverter(TravelModelConverter pConverterTravel) {
        this.converterTravel = pConverterTravel;
    }

    public TravelPlacesModel convert(TravelPlacesDto source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelPlacesModel target = new TravelPlacesModel();
        target.setId(source.getId());
        target.setTravelModel(this.converterTravel.convert(source.getTravelDto()));
        target.setName(source.getName());
        target.setObservations(source.getObservations());
        target.setCategory(source.getCategory());
        return target;
    }

    public List<TravelPlacesModel> convert(List<TravelPlacesDto> sourceList) {
        List<TravelPlacesModel> resultList;
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

    public TravelPlacesDto convertToDto(TravelPlacesModel source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TravelPlacesDto target = new TravelPlacesDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setObservations(source.getObservations());
        target.setCategory(source.getCategory());
        target.setTravelDto(converterTravel.convertToDto(source.getTravelModel()));
        return target;
    }

    public List<TravelPlacesDto> convertToDto(List<TravelPlacesModel> sourceList) {
        List<TravelPlacesDto> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final TravelPlacesModel source : sourceList) {
                resultList.add(convertToDto(source));
            }
        }
        return resultList;
    }
}
