package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
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
public class DatabaseTravelRepository implements TravelRepository {

    private final TravelConverter converter;
    private final DatabaseHelper dbHelper;

    @Inject
    public DatabaseTravelRepository(final TravelConverter pConverter, final DatabaseHelper pDbHelper) {
        this.converter = pConverter;
        this.dbHelper = pDbHelper;
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
    public TravelDto findById(final long identifier) throws PersistenceException {
        try {
            return converter.convertToDto(getDao().queryForId(identifier));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find travel by identifier", e);
        }
    }

    @Override
    public List<TravelDto> find(final String textFilter) throws PersistenceException {
        // FIXME Add filters
        try {
            final QueryBuilder<Travel, Long> builder = getDao().queryBuilder();
            if (textFilter != null && textFilter.trim().length() > 0) {
                builder.where().like(Travel.FIELD_NAME, textFilter).or().like(Travel.FIELD_DESTINATION, textFilter);
            }

            return converter.convertToDto(getDao().query(builder.prepare()));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find travels", e);
        }
    }

    private Dao<Travel, Long> getDao() throws SQLException {
        return dbHelper.getDao(Travel.class);
    }
}
