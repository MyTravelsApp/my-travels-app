package com.github.mytravelsapp.business.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;

import java.util.List;

/**
 * @author stefani
 */
public interface TravelPlacesRepository {

    void save(TravelPlacesDto dto);

    TravelPlacesDto findById(long identifier);

    List<TravelPlacesDto> find(String textFilter);
}
