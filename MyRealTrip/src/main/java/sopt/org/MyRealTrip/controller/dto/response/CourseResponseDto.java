package sopt.org.MyRealTrip.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import sopt.org.MyRealTrip.domain.Location;

import java.time.LocalTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CourseResponseDto {
    private Long id;
    private String address;
    private LocalTime time;
    private String title;
    private String description;
    private String image;

    public CourseResponseDto(Long id, Location location, LocalTime time, String title, String description, String image) {
        this.id = id;
        this.address = location.getAddress();
        this.time = time;
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
