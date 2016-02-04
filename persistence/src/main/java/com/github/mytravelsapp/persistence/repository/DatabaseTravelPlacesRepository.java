package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.persistence.converter.TravelPlacesConverter;
import com.github.mytravelsapp.persistence.entity.TravelPlaces;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author stefani
 */
@Singleton
@Named("databaseTravelPlacesRepository")
public class DatabaseTravelPlacesRepository extends DatabaseRepository<TravelPlaces, Long> implements TravelPlacesRepository {

    private final TravelPlacesConverter converter;

    @Inject
    public DatabaseTravelPlacesRepository(TravelPlacesConverter converter, DatabaseHelper dbHelper) {
        super(dbHelper, TravelPlaces.class);
        this.converter = converter;
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
    public List<TravelPlacesDto> find(final String textFilter, final long travelId) throws PersistenceException {
        // FIXME Add filters
        try {
            final QueryBuilder<TravelPlaces, Long> builder = getDao().queryBuilder();
            final Where<TravelPlaces, Long> where = builder.where();
            if (textFilter != null && textFilter.trim().length() > 0) {
                final String likeFilter = "%" + textFilter + "%";
               where.like(TravelPlaces.FIELD_NAME, likeFilter);
               where.and().eq(TravelPlaces.FIELD_ID_TRAVEL, travelId);
            } else {
               where.eq(TravelPlaces.FIELD_ID_TRAVEL, travelId);
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
            getDao().deleteById(identifier);
        } catch (final SQLException e) {
            throw new PersistenceException("Error delete travel places with id: " + identifier, e);
        }
    }

    @Override
    public void removeByTravel(final Long travelId) throws PersistenceException {
        if (travelId == null) {
            throw new IllegalArgumentException("travelId cannot be null");
        }
        try {
            final DeleteBuilder<TravelPlaces, Long> deleteBuilder = getDao().deleteBuilder();
            deleteBuilder.where().eq(TravelPlaces.FIELD_ID_TRAVEL, travelId);
            getDao().delete(deleteBuilder.prepare());
        } catch (final SQLException e) {
            throw new PersistenceException("Error delete travel places associated with travel: " + travelId, e);
        }
    }
}
