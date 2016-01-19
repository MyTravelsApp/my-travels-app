package com.github.mytravelsapp.persistence.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Travel entity for persistence layer.
 *
 * @author stefani
 */
@DatabaseTable(tableName = "CATEGORY")
public class Category implements Serializable {

    public static final String FIELD_ID = "_ID";
    public static final String FIELD_NAME = "NAME";
    public static final String FILED_IS_SYSTEM = "IS_SYSTEM";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private long id;

    @DatabaseField(columnName = FIELD_NAME)
    private String name;

    @DatabaseField(columnName = FILED_IS_SYSTEM)
    private boolean isSystem;

    public Category() {

    }

    public Category(final String pName, final boolean pIsSystem) {
        this.name = pName;
        this.isSystem = pIsSystem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }
}
