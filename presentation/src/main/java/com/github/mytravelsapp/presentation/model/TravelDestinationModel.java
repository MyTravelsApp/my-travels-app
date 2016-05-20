package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author fjtorres
 */
public class TravelDestinationModel implements Parcelable {

    public static final Creator<TravelDestinationModel> CREATOR = new Creator<TravelDestinationModel>() {
        public TravelDestinationModel createFromParcel(Parcel in) {
            return new TravelDestinationModel(in);
        }

        public TravelDestinationModel[] newArray(int size) {
            return new TravelDestinationModel[size];
        }
    };

    private String destinationPlaceId;

    private String destinationPlaceName;

    private Double destinationPlaceLatitude;

    private Double destinationPlaceLongitude;

    public TravelDestinationModel() {
    }

    public TravelDestinationModel(String destinationPlaceId, String destinationPlaceName, Double destinationPlaceLatitude, Double destinationPlaceLongitude) {
        this.destinationPlaceId = destinationPlaceId;
        this.destinationPlaceName = destinationPlaceName;
        this.destinationPlaceLatitude = destinationPlaceLatitude;
        this.destinationPlaceLongitude = destinationPlaceLongitude;
    }

    public TravelDestinationModel(Parcel in) {
        this.setDestinationPlaceId(in.readString());
        this.setDestinationPlaceName(in.readString());
        this.setDestinationPlaceLatitude(in.readDouble());
        this.setDestinationPlaceLongitude(in.readDouble());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDestinationPlaceId());
        dest.writeString(getDestinationPlaceName());
        if (getDestinationPlaceLatitude() != null) {
            dest.writeDouble(getDestinationPlaceLatitude());
        } else {
            dest.writeDouble(0);
        }
        if (getDestinationPlaceLongitude() != null) {
            dest.writeDouble(getDestinationPlaceLongitude());
        } else {
            dest.writeDouble(0);
        }
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
