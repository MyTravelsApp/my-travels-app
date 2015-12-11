package com.github.mytravelsapp.business.service;

import com.github.mytravelsapp.business.dto.TravelDto;

import java.util.List;

/**
 * Created by kisco on 11/12/2015.
 */
public interface TravelService {
    void save(TravelDto dto);

    TravelDto findById(long identifier);

    List<TravelDto> find(String textFilter);
}
