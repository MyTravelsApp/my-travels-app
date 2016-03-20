package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.CategoryRepository;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.converter.CategoryConverter;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.entity.Category;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author stefani
 */
@Singleton
@Named("databaseCategoryRepository")
public class DatabaseCategoryRepository extends DatabaseRepository<Category, Long> implements CategoryRepository {

    private final CategoryConverter converter;

    @Inject
    public DatabaseCategoryRepository(final CategoryConverter pConverter, final DatabaseHelper pDbHelper) {
        super(pDbHelper, Category.class);
        this.converter = pConverter;
    }


    @Override
    public void save(CategoryDto dto) throws PersistenceException {
        Category entity = converter.convert(dto);
        try {
            if (entity.getId() == -1) {
                getDao().create(entity);
                dto.setId(entity.getId());
            } else {
                getDao().update(entity);
            }
        } catch (final SQLException e) {
            throw new PersistenceException("Error when try to save category", e);
        }
    }

    @Override
    public List<CategoryDto> find(final String textFilter) throws PersistenceException {
        // FIXME Add filters
        try {
            final QueryBuilder<Category, Long> builder = getDao().queryBuilder();
            if (textFilter != null && textFilter.trim().length() > 0) {
                final String likeFilter = "%" + textFilter + "%";
                builder.where().like(Travel.FIELD_NAME, likeFilter);
            }

            return converter.convertToDto(getDao().query(builder.prepare()));
        } catch (final SQLException e) {
            throw new PersistenceException("Error find categories", e);
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
            throw new PersistenceException("Error delete category with id: " + identifier, e);
        }
    }
    
}
