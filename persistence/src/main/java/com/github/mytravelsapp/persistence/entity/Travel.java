package com.github.mytravelsapp.persistence.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Travel entity for persistence layer.
 *
 * @author fjtorres
 */
@DatabaseTable(tableName = "TRAVELS")
public class Travel implements Serializable {

    public static final String FIELD_ID = "_ID";
    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_DESTINATION = "DESTINATION";
    public static final String FIELD_START_DATE = "START_DATE";
    public static final String FIELD_FINISH_DATE = "FINISH_DATE";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private long id;

    @DatabaseField(columnName = FIELD_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_DESTINATION)
    private String destination;

    @DatabaseField(columnName = FIELD_START_DATE)
    private Date startDate;

    @DatabaseField(columnName = FIELD_FINISH_DATE)
    private Date finishDate;

    public Travel() {

    }

    public Travel(final long pId, final String pName, final String pDestination, final Date pStartDate, final Date pFinishDate) {
        this.id = pId;
        this.destination = pDestination;
        this.name = pName;
        this.startDate = pStartDate;
        this.finishDate = pFinishDate;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
