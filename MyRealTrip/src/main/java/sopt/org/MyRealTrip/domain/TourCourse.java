package sopt.org.MyRealTrip.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tour_course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourCourse extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_course_id")
    private Long id;

    private Long index;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;


    @Builder
    public TourCourse(Long index, Tour tour, Course course){
        this.index=index;
        this.tour=tour;
        this.course=course;

    }
}
