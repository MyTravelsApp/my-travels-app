package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author kisco
 */
public class TravelDayPlanningModel implements Parcelable {

    private Long travelPlaceId;

    private Date day;

    private int order;

    public TravelDayPlanningModel() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelDayPlanningModel)) return false;

        TravelDayPlanningModel that = (TravelDayPlanningModel) o;

        if (getOrder() != that.getOrder()) return false;
        if (!getTravelPlaceId().equals(that.getTravelPlaceId())) return false;
        return getDay().equals(that.getDay());

    }

    @Override
    public int hashCode() {
        int result = getTravelPlaceId().hashCode();
        result = 31 * result + getDay().hashCode();
        result = 31 * result + getOrder();
        return result;
    }

    public Long getTravelPlaceId() {
        return travelPlaceId;
    }

    public void setTravelPlaceId(Long travelPlaceId) {
        this.travelPlaceId = travelPlaceId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Class constructor. Read from {@link android.os.Parcel} object.
     *
     * @param in Input data object.
     */
    public TravelDayPlanningModel(final Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<TravelDayPlanningModel> CREATOR = new Creator<TravelDayPlanningModel>() {
        public TravelDayPlanningModel createFromParcel(Parcel in) {
            return new TravelDayPlanningModel(in);
        }

        public TravelDayPlanningModel[] newArray(int size) {
            return new TravelDayPlanningModel[size];
        }
    };

    /**
     * Write this object to {@link android.os.Parcel} destination object.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeInt(getOrder());
        out.writeLong(getDay().getTime());
        out.writeLong(getTravelPlaceId());
    }

    /**
     * Read from {@link android.os.Parcel} object to this object.
     *
     * @param in Input data.
     */
    private void readFromParcel(Parcel in) {
        this.setOrder(in.readInt());
        this.setDay(new Date(in.readLong()));
        this.setTravelPlaceId(in.readLong());
    }
}
