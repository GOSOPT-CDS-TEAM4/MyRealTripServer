package sopt.org.MyRealTrip.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapRequestDto {
    private Long userId;

    private Long tourId;
    public ScrapRequestDto(Long userId, Long tourId) {
        this.userId = userId;
        this.tourId = tourId;
    }
}
