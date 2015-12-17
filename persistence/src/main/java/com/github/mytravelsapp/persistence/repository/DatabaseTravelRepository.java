package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author fjtorres
 */
@Singleton
@Named("databaseTravelRepository")
public class DatabaseTravelRepository extends DatabaseRepository<Travel, Long> implements TravelRepository {

    private final TravelPlacesRepository travelPlacesRepository;

    private final TravelConverter converter;

    @Inject
    public DatabaseTravelRepository(final TravelPlacesRepository pTravelPlacesRepository, final TravelConverter pConverter, final DatabaseHelper pDbHelper) {
        super(pDbHelper, Travel.class);
        this.converter = pConverter;
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public void save(final TravelDto dto) throws PersistenceException {
        Travel entity = converter.convert(dto);
        try {
            if (entity.getId() == -1) {
                getDao().create(entity);
            } else {
                getDao().update(entity);
            }
        } catch (final SQLException e) {
            throw new PersistenceException("Error when try to save travel", e);
        }
    }

    @Override
    public TravelDto findById(final Long identifier) throws PersistenceException {
        if (identifier == null) {
            throw new IllegalArgumentException("identifier cannot be null");
        }
        try {
            return converter.convertToDto(getDao().queryForId(identifier));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find travel with id: " + identifier, e);
        }
    }

    @Override
    public List<TravelDto> find(final String textFilter) throws PersistenceException {
        // FIXME Add filters
        try {
            final QueryBuilder<Travel, Long> builder = getDao().queryBuilder();
            if (textFilter != null && textFilter.trim().length() > 0) {
                final String likeFilter = "%" + textFilter + "%";
                builder.where().like(Travel.FIELD_NAME, likeFilter).or().like(Travel.FIELD_DESTINATION, likeFilter);
            }

            return converter.convertToDto(getDao().query(builder.prepare()));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find travels", e);
        }
    }

    @Override
    public void remove(final Long identifier) throws PersistenceException {
        if (identifier == null) {
            throw new IllegalArgumentException("identifier cannot be null");
        }
        try {
            travelPlacesRepository.removeByTravel(identifier);
            getDao().deleteById(identifier);
        } catch (final SQLException e) {
            throw new PersistenceException("Error delete travel with id: " + identifier, e);
        }
    }
}
