package sopt.org.MyRealTrip.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)public class LocationResponseDto {
    private Double latitude;
    private Double longtitude;
    String locationDescription;

    public LocationResponseDto(Double latitude, Double longtitude, String locationDescription) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.locationDescription = locationDescription;
    }
}
