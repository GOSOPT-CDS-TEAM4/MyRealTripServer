package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.MyRealTrip.domain.Tour;

import java.util.List;


public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAll();


}
