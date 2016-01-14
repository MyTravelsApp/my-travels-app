package com.github.mytravelsapp.business.service.impl;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.business.service.TravelPlacesService;
import com.github.mytravelsapp.business.service.TravelService;

import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class TravelPlacesServiceImpl implements TravelPlacesService {

    private final TravelPlacesRepository travelPlacesRepository;

    @Inject
    public TravelPlacesServiceImpl(final TravelPlacesRepository pTravelPlacesRepository) {
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public void save(final TravelPlacesDto dto) throws PersistenceException{
        travelPlacesRepository.save(dto);
    }

    @Override
    public TravelPlacesDto findById(final long identifier) throws PersistenceException {
        return travelPlacesRepository.findById(identifier);
    }

    @Override
    public List<TravelPlacesDto> find(final String textFilter, final long travelId) throws PersistenceException{
        return travelPlacesRepository.find(textFilter, travelId);
    }
}
