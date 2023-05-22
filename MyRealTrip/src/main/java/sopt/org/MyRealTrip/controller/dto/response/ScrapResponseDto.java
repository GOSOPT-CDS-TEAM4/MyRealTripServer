package sopt.org.MyRealTrip.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapResponseDto {
    private Long id;
    private Long userId;
    private Long tourId;

    public static ScrapResponseDto of(Long id, Long userId, Long tourId) {
        return new ScrapResponseDto(id, userId, tourId);
    }
}
