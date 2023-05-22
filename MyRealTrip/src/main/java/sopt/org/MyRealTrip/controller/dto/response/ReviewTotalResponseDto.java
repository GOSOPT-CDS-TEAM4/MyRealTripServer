package sopt.org.MyRealTrip.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewTotalResponseDto {
    private Long totalNumber;
    private Double totalRating;
    private List<ReviewResponseDto> reviewResponseDtoList;

    public ReviewTotalResponseDto(Long totalNumber, Double totalRating, List<ReviewResponseDto> reviewResponseDtoList) {
        this.totalNumber = totalNumber;
        this.totalRating = totalRating;
        this.reviewResponseDtoList = reviewResponseDtoList;
    }
}
