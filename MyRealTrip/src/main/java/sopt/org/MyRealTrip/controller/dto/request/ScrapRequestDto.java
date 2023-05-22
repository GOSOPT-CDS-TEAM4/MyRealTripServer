package sopt.org.MyRealTrip.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapRequestDto {

    private Long tourId;
    public ScrapRequestDto(Long tourId) {
        this.tourId = tourId;
    }
}
