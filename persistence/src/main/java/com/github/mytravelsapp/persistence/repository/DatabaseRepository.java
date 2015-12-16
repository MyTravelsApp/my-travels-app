package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * @author fjtorres
 */
public abstract class DatabaseRepository<E, Id> {
    private final Dao<E, Id> dao;

    public DatabaseRepository(final DatabaseHelper dbHelper, final Class<E> entityClass) {
        try {
            dao = dbHelper.getDao(entityClass);
        } catch (final SQLException e) {
            throw new RuntimeException("Error creating DAO for class: " + entityClass, e);
        }
    }

    public Dao<E, Id> getDao() {
        return dao;
    }
}
