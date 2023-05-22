package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.TourCourse;

import java.util.List;

public interface TourCourseRepository extends JpaRepository<TourCourse, Long> {
    List<TourCourse> findByTour(Tour tour);
}
