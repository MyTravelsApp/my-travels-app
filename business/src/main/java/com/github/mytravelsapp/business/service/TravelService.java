package com.github.mytravelsapp.business.service;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;

import java.util.List;

/**
 * @author fjtorres
 */
public interface TravelService {
    void save(TravelDto dto) throws PersistenceException;

    TravelDto findById(long identifier) throws PersistenceException;

    List<TravelDto> find(String textFilter) throws PersistenceException;
}
