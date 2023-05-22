package sopt.org.MyRealTrip.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import sopt.org.MyRealTrip.domain.Review;
import sopt.org.MyRealTrip.domain.User;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewResponseDto {
    private User user;
    private Review review;
    private String nickname;
    private Double rating;
    private String keyword1;
    private String keyword2;
    private LocalDateTime createdAt;
    private String content;

    public ReviewResponseDto(String nickname, Double rating, String keyword1, String keyword2, LocalDateTime createdAt, String content) {
        this.nickname = nickname;
        this.rating = rating;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.createdAt = createdAt;
        this.content = content;
    }
}
