package com.github.mytravelsapp.business.service;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;

import java.util.List;

/**
 * Created by stefani on 14/12/2015.
 */
public interface TravelPlacesService {

    void save(TravelPlacesDto dto) throws PersistenceException;

    TravelPlacesDto findById(long identifier) throws PersistenceException;

    List<TravelPlacesDto> find(String textFilter) throws PersistenceException;
}
