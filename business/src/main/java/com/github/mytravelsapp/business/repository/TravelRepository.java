package com.github.mytravelsapp.business.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;

import java.util.List;

/**
 * @author fjtorres
 */
public interface TravelRepository {

    void save(TravelDto dto) throws PersistenceException;

    TravelDto findById(long identifier) throws PersistenceException;

    List<TravelDto> find(String textFilter) throws PersistenceException;
}
