package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.entity.Travel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author fjtorres
 */
@Singleton
@Named("databaseTravelRepository")
public class DatabaseTravelRepository implements TravelRepository {

    private static final List<Travel> STATIC_DATA = new ArrayList<>();

    static {
        STATIC_DATA.add(new Travel(1L, "Viaje a Roma", "Roma", new Date(), new Date()));
        STATIC_DATA.add(new Travel(2L, "Viaje a Londres", "Londres", new Date(), new Date()));
        STATIC_DATA.add(new Travel(3L, "Viaje a Paris", "Paris", new Date(), new Date()));
        STATIC_DATA.add(new Travel(4L, "Viaje a Riviera maya", "Playa del carmen", new Date(), new Date()));
        STATIC_DATA.add(new Travel(5L, "Viaje a Mallorca", "Mallorca", new Date(), new Date()));
        STATIC_DATA.add(new Travel(6L, "Viaje a Lisboa", "Lisboa", new Date(), new Date()));
        STATIC_DATA.add(new Travel(7L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(8L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(9L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(10L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(11L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(12L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(13L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(14L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(15L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(16L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(17L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(18L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(19L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(20L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(21L, "Puente de XX", "XX", new Date(), new Date()));
        STATIC_DATA.add(new Travel(22L, "Puente de XX", "XX", new Date(), new Date()));
    }

    private final TravelConverter converter;

    @Inject
    public DatabaseTravelRepository(final TravelConverter pConverter) {
        this.converter = pConverter;
    }

    @Override
    public void save(final TravelDto dto) {
        // FIXME Development!!
        if (dto.getId() == -1) {
            dto.setId(STATIC_DATA.size());
            STATIC_DATA.add(converter.convert(dto));
        }
    }

    @Override
    public TravelDto findById(final long identifier) {
        // FIXME Development!!
        TravelDto result = null;
        if (identifier <= 0 && identifier > STATIC_DATA.size()) {
            result = converter.convertToDto(STATIC_DATA.get(new Long(identifier).intValue()));
        }

        return result;
    }

    @Override
    public List<TravelDto> find(final String textFilter) {
        // FIXME Development!!
        return converter.convertToDto(STATIC_DATA);
    }
}
