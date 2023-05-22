package sopt.org.MyRealTrip.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GuideResponseDto {
    private String time;
    private LocationResponseDto locationResponseDto;

    public GuideResponseDto(String time, LocationResponseDto locationResponseDto) {
        this.time = time;
        this.locationResponseDto = locationResponseDto;
    }
}
