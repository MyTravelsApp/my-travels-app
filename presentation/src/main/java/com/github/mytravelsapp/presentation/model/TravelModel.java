package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class that represents a travel in the presentation layer.
 *
 * @author fjtorres
 */
public class TravelModel implements Parcelable {
    public static final long DEFAULT_ID = -1;

    private long id = DEFAULT_ID;

    private String name;

    private Date startDate;

    private Date finishDate;

    private Map<Date, List<TravelDayPlanningModel>> daysPlanningMap;

    private TravelDestinationModel destination;

    public TravelModel(final long pId) {
        this(pId, null, new TravelDestinationModel());
    }

    public TravelModel(final long pId, final String pName, final TravelDestinationModel pDestination) {
        this(pId, pName, pDestination, null, null);
    }

    public TravelModel(final long pId, final String pName, final TravelDestinationModel pDestination, final Date pStartDate, final Date pFinishDate) {
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

    public TravelDestinationModel getDestination() {
        return destination;
    }

    public void setDestination(TravelDestinationModel destination) {
        this.destination = destination;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Date, List<TravelDayPlanningModel>> getDaysPlanningMap() {
        return daysPlanningMap;
    }

    public void setDaysPlanningMap(Map<Date, List<TravelDayPlanningModel>> daysPlanningMap) {
        this.daysPlanningMap = daysPlanningMap;
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
        out.writeParcelable(getDestination(), flags);
        if (getFinishDate() != null) {
            out.writeLong(getFinishDate().getTime());
        } else {
            out.writeLong(0);
        }
        if (getStartDate() != null) {
            out.writeLong(getStartDate().getTime());
        } else {
            out.writeLong(0);
        }
        out.writeMap(getDaysPlanningMap());
    }

    /**
     * Read from {@link android.os.Parcel} object to this object.
     *
     * @param in Input data.
     */
    private void readFromParcel(Parcel in) {
        this.setId(in.readLong());
        this.setName(in.readString());
        this.setDestination((TravelDestinationModel) in.readParcelable(TravelDestinationModel.class.getClassLoader()));
        long date = in.readLong();
        if (date != 0) {
            this.setFinishDate(new Date(date));
        }
        date = in.readLong();
        if (date != 0) {
            this.setStartDate(new Date(date));
        }
        setDaysPlanningMap(in.readHashMap(TravelDayPlanningModel.class.getClassLoader()));
    }
}
