package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Class that represents a travel in the presentation layer.
 *
 * @author fjtorres
 */
public class TravelModel implements Parcelable{
    public static final long DEFAULT_ID = -1;

    private long id = DEFAULT_ID;

    private String name;

    private String destination;

    private Date startDate;

    private Date finishDate;

    public TravelModel(final long pId) {

        this(pId, null, null);
    }

    public TravelModel(final long pId, final String pName, final String pDestination) {
        this(pId, pName, pDestination, null, null);
    }

    public TravelModel(final long pId, final String pName, final String pDestination, final Date pStartDate, final Date pFinishDate) {
        this.id = pId;
        this.destination = pDestination;
        this.name = pName;
        this.startDate = pStartDate;
        this.finishDate = pFinishDate;
    }

    public long getId() {
        return id;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Class constructor. Read from {@link android.os.Parcel} object.
     *
     * @param in
     *            Input data object.
     */
    public TravelModel(final Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<TravelModel> CREATOR = new Creator<TravelModel>() {
        public TravelModel createFromParcel(Parcel in) {
            return new TravelModel(in);
        }

        public TravelModel[] newArray(int size) {
            return new TravelModel[size];
        }
    };

    /**
     * Write this object to {@link android.os.Parcel} destination object.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeLong(getId());
        out.writeString(getName());
        out.writeLong(getFinishDate().getTime());
        out.writeLong(getStartDate().getTime());
    }

    /**
     * Read from {@link android.os.Parcel} object to this object.
     *
     * @param in
     *            Input data.
     */
    private void readFromParcel(Parcel in) {
        this.setId(in.readLong());
        this.setName(in.readString());
        this.setFinishDate(new Date(in.readLong()));
        this.setStartDate(new Date(in.readLong()));
    }
}
