package com.github.mytravelsapp.persistence.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.mytravelsapp.persistence.entity.Category;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.entity.TravelPlaces;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @author fjtorres
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "mytravelsapp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Travel.class);
            TableUtils.createTable(connectionSource, TravelPlaces.class);
            TableUtils.createTable(connectionSource, Category.class);
            createCategoryDefault();
        } catch (final SQLException e) {
           throw new RuntimeException(e);// FIXME Exception!!!!
        }
    }

    private void createCategoryDefault(){
        try {
            Dao<Category,String> categoryDao=this.getDao(Category.class);
            categoryDao.create(new Category("Parks", true));
            categoryDao.create(new Category("Museums", true));
            categoryDao.create(new Category("Squares", true));
            categoryDao.create(new Category("Monuments", true));
        } catch (SQLException e) {
            throw new RuntimeException(e);// FIXME Exception!!!!
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, TravelPlaces.class, true);
            TableUtils.dropTable(connectionSource, Travel.class, true);
            onCreate(database, connectionSource);
        } catch (final SQLException e) {
            throw new RuntimeException(e);// FIXME Exception!!!!
        }
    }
}
