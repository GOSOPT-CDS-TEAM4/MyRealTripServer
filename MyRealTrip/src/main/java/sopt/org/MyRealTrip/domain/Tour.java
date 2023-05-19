package sopt.org.MyRealTrip.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tour extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long id;

    private String title;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private Boolean freeCancel;
    private String itemType;
    private String type;
    private Long minPeople;
    private Long maxPeople;
    private String transfortation;
    private LocalTime requiredTime;
    private String language;
    private String noticeTitle;
    private String notice;
    private String descriptionTitle;
    private String description;
    private Long price;
    private Long discountedPrice;
    private String includedOption;
    private String excludedOption;
    private String image;

    @OneToMany(mappedBy = "tour")
    private List<TourCourse> tourCourseList;

    @OneToMany(mappedBy = "tour")
    private List<Review> reviewList;

    public void addTourCourse(TourCourse tourCourse){
        tourCourseList.add(tourCourse);

    }

    public void addReview(Review review){
        reviewList.add(review);
    }

    @Transient
    private Boolean isScrap; //현재 사용자가 스크랩한지 아닌지 여부


    @Builder
    public Tour(String title, Location location, Boolean freeCancel, String itemType,
                String type, Long minPeople,Long maxPeople, String transfortation,
                LocalTime requiredTime, String language, String noticeTitle, String notice,
                String descriptionTitle, String description, Long price, Long discountedPrice,
                String includedOption, String excludedOption, String image){

        this.title=title;
        this.location=location;
        this.freeCancel=freeCancel;
        this.itemType=itemType;
        this.type=type;
        this.minPeople=minPeople;
        this.maxPeople=maxPeople;
        this.transfortation=transfortation;
        this.requiredTime=requiredTime;
        this.language=language;
        this.noticeTitle=noticeTitle;
        this.notice=notice;
        this.descriptionTitle=descriptionTitle;
        this.description=description;
        this.price=price;
        this.discountedPrice=discountedPrice;
        this.includedOption=includedOption;
        this.excludedOption=excludedOption;
        this.image=image;
        this.tourCourseList=new ArrayList<>();
        this.reviewList=new ArrayList<>();

    }


}
