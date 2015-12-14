package com.github.mytravelsapp.business.service.impl;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.business.service.TravelService;

import java.util.List;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelServiceImpl implements TravelService {

    private final TravelRepository travelRepository;

    @Inject
    public TravelServiceImpl(final TravelRepository pTravelRepository) {
        this.travelRepository = pTravelRepository;
    }

    @Override
    public void save(final TravelDto dto) throws PersistenceException {
        travelRepository.save(dto);
    }

    @Override
    public TravelDto findById(final long identifier) throws PersistenceException {
        return travelRepository.findById(identifier);
    }

    @Override
    public List<TravelDto> find(final String textFilter) throws PersistenceException {
        return travelRepository.find(textFilter);
    }
}
