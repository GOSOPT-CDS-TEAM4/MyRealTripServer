package sopt.org.MyRealTrip.domain;

import sopt.org.MyRealTrip.domain.Tour;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    private String country;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "location")
    private List<Tour> tourList;

    public void addTour(Tour tour){
        tourList.add(tour);
    }

    @Builder
    public Location(String country, String city, String address, Double latitude, Double longitude){
        this.country=country;
        this.city=city;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
        this.tourList=new ArrayList<>();

    }
}
