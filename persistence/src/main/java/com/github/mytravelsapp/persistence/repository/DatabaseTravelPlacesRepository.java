package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.converter.TravelPlacesConverter;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.entity.TravelPlaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author stefani
 */
@Singleton
@Named("databaseTravelPlacesRepository")
public class DatabaseTravelPlacesRepository implements TravelPlacesRepository {

    private static final List<TravelPlaces> STATIC_DATA = new ArrayList<>();

    static {
        STATIC_DATA.add(new TravelPlaces(1L, "Foro romano"));
        STATIC_DATA.add(new TravelPlaces(2L, "Panteon"));
        STATIC_DATA.add(new TravelPlaces(3L, "Basilica san pedro"));
        STATIC_DATA.add(new TravelPlaces(4L, "Coliseo"));
        STATIC_DATA.add(new TravelPlaces(5L, "Termas de Caracalla"));
        STATIC_DATA.add(new TravelPlaces(6L, "Catacumbas San Calixto"));
        STATIC_DATA.add(new TravelPlaces(7L, "Basilica santa maria la mayor"));
        STATIC_DATA.add(new TravelPlaces(8L, "Museos Capitolinos"));
        STATIC_DATA.add(new TravelPlaces(9L, "Monumento Victor Manuel"));
        STATIC_DATA.add(new TravelPlaces(10L, "Fontana di trevi"));
    }

    private final TravelPlacesConverter converter;

    @Inject
    public DatabaseTravelPlacesRepository(final TravelPlacesConverter pConverter) {
        this.converter = pConverter;
    }

    @Override
    public void save(final TravelPlacesDto dto) {
        // FIXME Development!!
        if (dto.getId() == -1) {
            dto.setId(STATIC_DATA.size());
            STATIC_DATA.add(converter.convert(dto));
        }
    }

    @Override
    public TravelPlacesDto findById(final long identifier) {
        // FIXME Development!!
        TravelPlacesDto result = null;
        if (identifier <= 0 && identifier > STATIC_DATA.size()) {
            result = converter.convertToDto(STATIC_DATA.get(new Long(identifier).intValue()));
        }

        return result;
    }

    @Override
    public List<TravelPlacesDto> find(final String textFilter) {
        // FIXME Development!!
        return converter.convertToDto(STATIC_DATA);
    }
}
