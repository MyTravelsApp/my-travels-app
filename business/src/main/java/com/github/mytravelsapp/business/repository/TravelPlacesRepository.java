package com.github.mytravelsapp.business.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;

import java.util.List;

/**
 * @author stefani
 */
public interface TravelPlacesRepository {

    void save(TravelPlacesDto dto) throws PersistenceException;

    TravelPlacesDto findById(long identifier) throws PersistenceException;

    List<TravelPlacesDto> find(String textFilter) throws PersistenceException;

    List<TravelPlacesDto> findByTravel(long travelId) throws PersistenceException;

    void remove(Long identifier) throws PersistenceException;

    void removeByTravel(Long travelId) throws PersistenceException;
}
