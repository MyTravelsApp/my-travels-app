package com.github.mytravelsapp.business.dto;

/**
 * @author fjtorres
 */
public class TravelDestinationDto implements Dto {

    private String destinationPlaceId;

    private String destinationPlaceName;

    private Double destinationPlaceLatitude;

    private Double destinationPlaceLongitude;

    public TravelDestinationDto() {
    }

    public TravelDestinationDto(String destinationPlaceId, String destinationPlaceName, Double destinationPlaceLatitude, Double destinationPlaceLongitude) {
        this.destinationPlaceId = destinationPlaceId;
        this.destinationPlaceName = destinationPlaceName;
        this.destinationPlaceLatitude = destinationPlaceLatitude;
        this.destinationPlaceLongitude = destinationPlaceLongitude;
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
