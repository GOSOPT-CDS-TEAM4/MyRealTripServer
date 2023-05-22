package sopt.org.MyRealTrip.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private LocalTime time;

    private String title;
    private String description;
    private String image;

    @Builder
    public Course(Location location, LocalTime time, String title, String description, String image){
        this.location=location;
        this.time=time;
        this.title=title;
        this.description=description;
        this.image=image;

    }

}
