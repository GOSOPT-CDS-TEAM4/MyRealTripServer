package sopt.org.MyRealTrip.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private Double rating;

    private String content;

    private String keyword1;
    private String keyword2;

    @Builder
    public Review(User user, Tour tour, Double rating, String content, String keyword1, String keyword2){
        this.user=user;
        this.tour=tour;
        this.rating=rating;
        this.content=content;
        this.keyword1=keyword1;
        this.keyword2=keyword2;
    }


}
