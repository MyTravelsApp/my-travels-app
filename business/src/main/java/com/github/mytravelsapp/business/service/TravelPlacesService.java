package com.github.mytravelsapp.business.service;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;

import java.util.List;

/**
 * Created by stefani on 14/12/2015.
 */
public interface TravelPlacesService {
    void save(TravelPlacesDto dto);

    TravelPlacesDto findById(long identifier);

    List<TravelPlacesDto> find(String textFilter);
}
