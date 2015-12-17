package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.mytravelsapp.persistence.entity.Travel;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesModel implements Parcelable{

    public static final long DEFAULT_ID = -1;
    private long id = DEFAULT_ID;
    private String name;
    private String category;
    private String observations;
    private TravelModel travelModel;

    public TravelPlacesModel() {
    }

    public TravelPlacesModel(TravelModel pTravelModel) {
        this.travelModel = pTravelModel;
    }

    public TravelPlacesModel(long id, String name, TravelModel pTravelModel, String category) {
        this.id = id;
        this.name = name;
        this.travelModel = pTravelModel;
        this.category = category;
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

    public TravelModel getTravelModel() {
        return travelModel;
    }

    public void setTravelModel(TravelModel travelModel) {
        this.travelModel = travelModel;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    public TravelPlacesModel(final Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<TravelPlacesModel> CREATOR = new Creator<TravelPlacesModel>() {
        public TravelPlacesModel createFromParcel(Parcel in) {
            return new TravelPlacesModel(in);
        }

        public TravelPlacesModel[] newArray(int size) {
            return new TravelPlacesModel[size];
        }
    };

    /**
     * Write this object to {@link android.os.Parcel} destination object.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeString(getName());
        out.writeParcelable(getTravelModel(), flags);
    }

    /**
     * Read from {@link android.os.Parcel} object to this object.
     *
     * @param in
     *            Input data.
     */
    private void readFromParcel(Parcel in) {
        this.setName(in.readString());
        this.setTravelModel((TravelModel) readParcelable(in));
    }


    private TravelModel readParcelable(final Parcel in) {
        return (TravelModel) in.readParcelable(getClass().getClassLoader());
    }
}
