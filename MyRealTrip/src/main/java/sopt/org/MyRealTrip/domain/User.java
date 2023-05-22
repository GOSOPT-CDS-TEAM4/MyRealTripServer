package sopt.org.MyRealTrip.domain;

import lombok.Builder;
import sopt.org.MyRealTrip.domain.Scrap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Scrap> scrapList;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList;
    public void addScrap(Scrap scrap){
        scrapList.add(scrap);
    }

    public void addReview(Review review){
        reviewList.add(review);
    }

    @Builder
    public User(String nickname){
        this.nickname=nickname;
        this.scrapList=new ArrayList<>();
        this.reviewList=new ArrayList<>();
    }


}
