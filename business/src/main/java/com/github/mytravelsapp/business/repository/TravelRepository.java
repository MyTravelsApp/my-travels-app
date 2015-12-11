package com.github.mytravelsapp.business.repository;

import com.github.mytravelsapp.business.dto.TravelDto;

import java.util.List;

/**
 * @author fjtorres
 */
public interface TravelRepository {

    void save(TravelDto dto);

    TravelDto findById(long identifier);

    List<TravelDto> find(String textFilter);
}
