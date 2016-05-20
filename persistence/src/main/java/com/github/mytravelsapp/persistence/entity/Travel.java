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
    public static final String FIELD_START_DATE = "START_DATE";
    public static final String FIELD_FINISH_DATE = "FINISH_DATE";
    public static final String FIELD_DAYS_PLANNING = "DAYS_PLANNING";
    public static final String FIELD_DESTINATION_PLACE_ID = "DESTINATION_PLACE_ID";
    public static final String FIELD_DESTINATION_PLACE_NAME = "DESTINATION_PLACE_NAME";
    public static final String FIELD_DESTINATION_PLACE_LATITUDE = "DESTINATION_PLACE_LATITUDE";
    public static final String FIELD_DESTINATION_PLACE_LONGITUDE = "DESTINATION_PLACE_LONGITUDE";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private long id;

    @DatabaseField(canBeNull = false, columnName = FIELD_NAME)
    private String name;

    @DatabaseField(canBeNull = false, columnName = FIELD_START_DATE)
    private Date startDate;

    @DatabaseField(canBeNull = false, columnName = FIELD_FINISH_DATE)
    private Date finishDate;

    @DatabaseField(columnName = FIELD_DAYS_PLANNING)
    private String daysPlanning;

    @DatabaseField(canBeNull = false, columnName = FIELD_DESTINATION_PLACE_ID)
    private String destinationPlaceId;

    @DatabaseField(canBeNull = false, columnName = FIELD_DESTINATION_PLACE_NAME)
    private String destinationPlaceName;

    @DatabaseField(canBeNull = false, columnName = FIELD_DESTINATION_PLACE_LATITUDE)
    private Double destinationPlaceLatitude;

    @DatabaseField(canBeNull = false, columnName = FIELD_DESTINATION_PLACE_LONGITUDE)
    private Double destinationPlaceLongitude;

    public Travel() {

    }

    public Travel(long id) {
        this.id = id;
    }

    public Travel(final long pId, final String pName, final Date pStartDate, final Date pFinishDate, final String pDestinationPlaceId, final String pDestinationPlaceName, final Double pDestinationPlaceLatitude, final Double pDestinationPlaceLongitude) {
        this.id = pId;
        this.name = pName;
        this.startDate = pStartDate;
        this.finishDate = pFinishDate;
        this.destinationPlaceId = pDestinationPlaceId;
        this.destinationPlaceName = pDestinationPlaceName;
        this.destinationPlaceLatitude = pDestinationPlaceLatitude;
        this.destinationPlaceLongitude = pDestinationPlaceLongitude;
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

    public String getDaysPlanning() {
        return daysPlanning;
    }

    public void setDaysPlanning(String daysPlanning) {
        this.daysPlanning = daysPlanning;
    }

    public String getDestinationPlaceId() {
        return destinationPlaceId;
    }

    public void setDestinationPlaceId(String destinationPlaceId) {
        this.destinationPlaceId = destinationPlaceId;
    }

    public String getDestinationPlaceName() {
        return destinationPlaceName;
    }

    public void setDestinationPlaceName(String destinationPlaceName) {
        this.destinationPlaceName = destinationPlaceName;
    }

    public Double getDestinationPlaceLatitude() {
        return destinationPlaceLatitude;
    }

    public void setDestinationPlaceLatitude(Double destinationPlaceLatitude) {
        this.destinationPlaceLatitude = destinationPlaceLatitude;
    }

    public Double getDestinationPlaceLongitude() {
        return destinationPlaceLongitude;
    }

    public void setDestinationPlaceLongitude(Double destinationPlaceLongitude) {
        this.destinationPlaceLongitude = destinationPlaceLongitude;
    }
}
