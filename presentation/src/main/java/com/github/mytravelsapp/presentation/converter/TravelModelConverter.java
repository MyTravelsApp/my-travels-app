package com.github.mytravelsapp.presentation.converter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        target.setDestination(source.getDestination());
        target.setFinishDate(source.getFinishDate());
        target.setName(source.getName());
        target.setStartDate(source.getStartDate());
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
        target.setDestination(source.getDestination());
        target.setFinishDate(source.getFinishDate());
        target.setId(source.getId());
        target.setName(source.getName());
        target.setStartDate(source.getStartDate());
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
}
