package com.github.mytravelsapp.persistence.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by stefani on 14/12/2015.
 */
@DatabaseTable(tableName = "TRAVEL_PLACES")
public class TravelPlaces implements Serializable {

    public static final String FIELD_ID = "_ID";
    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_OBERVATIONS = "OBSERVATIONS";
    public static final String FIELD_ID_TRAVEL = "ID_TRAVEL";
    public static final String FIELD_CATEGORY = "CATEGORY";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private long id;
    @DatabaseField(canBeNull = false, columnName = FIELD_NAME)
    private String name;
    @DatabaseField(columnName = FIELD_OBERVATIONS)
    private String observations;
    @DatabaseField(foreign=true, canBeNull = false, columnName = FIELD_ID_TRAVEL)
    private Travel travel;
    @DatabaseField(canBeNull = false, columnName = FIELD_CATEGORY)
    private String category;

    public TravelPlaces() {

    }

    public TravelPlaces(long id, String name, Travel travel, String category) {
        this.id = id;
        this.name = name;
        this.travel = travel;
        this.category = category;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
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

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
