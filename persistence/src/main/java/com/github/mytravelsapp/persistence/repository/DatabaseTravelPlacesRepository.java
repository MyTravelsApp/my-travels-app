package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.converter.TravelPlacesConverter;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.entity.TravelPlaces;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
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

    private final TravelPlacesConverter converter;
    private final DatabaseHelper dbHelper;

    @Inject
    public DatabaseTravelPlacesRepository(TravelPlacesConverter converter, DatabaseHelper dbHelper) {
        this.converter = converter;
        this.dbHelper = dbHelper;
    }

    @Override
    public void save(final TravelPlacesDto dto) throws PersistenceException {
        TravelPlaces entity = converter.convert(dto);
        try {
            if (entity.getId() == -1) {
                getDao().create(entity);
            } else {
                getDao().update(entity);
            }
        } catch (final SQLException e) {
            throw new PersistenceException("Error when try to save travel places", e);
        }
    }

    @Override
    public TravelPlacesDto findById(final long identifier) throws PersistenceException {
        try {
            return converter.convertToDto(getDao().queryForId(identifier));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find travel places by identifier", e);
        }
    }

    @Override
    public List<TravelPlacesDto> find(final String textFilter) throws PersistenceException {
        // FIXME Add filters
        try {
            final QueryBuilder<TravelPlaces, Long> builder = getDao().queryBuilder();
            if (textFilter != null && textFilter.trim().length() > 0) {
                builder.where().like(TravelPlaces.FIELD_NAME, textFilter).or().like(TravelPlaces.FIELD_CATEGORY, textFilter);
            }

            return converter.convertToDto(getDao().query(builder.prepare()));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find travels", e);
        }
    }

    private Dao<TravelPlaces, Long> getDao() throws SQLException {
        return dbHelper.getDao(TravelPlaces.class);
    }
}
