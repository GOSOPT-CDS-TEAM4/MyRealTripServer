package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.MyRealTrip.domain.Tour;

import java.util.Optional;

public interface TourRepository extends Repository<Tour, Long> {
    Optional<Tour> findById(Long tourId);
    Long countBy();
}
