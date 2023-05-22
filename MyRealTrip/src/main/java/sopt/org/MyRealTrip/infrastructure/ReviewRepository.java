package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.MyRealTrip.domain.Review;
import sopt.org.MyRealTrip.domain.Tour;

import java.util.List;

public interface ReviewRepository extends Repository<Review, Long> {
    List<Review> findByTour(Tour tour);
}
