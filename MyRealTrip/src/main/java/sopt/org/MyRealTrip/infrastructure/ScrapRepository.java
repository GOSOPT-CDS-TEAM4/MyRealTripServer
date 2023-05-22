package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import sopt.org.MyRealTrip.domain.Scrap;

import java.util.Optional;

public interface ScrapRepository extends Repository<Scrap, Long> {
    Scrap save(Scrap scrap);

    Optional<Long> findById(Long scrapId);
    @Query("select s from Scrap s where s.user.id = :userId and s.tour.id = :tourId")
    Scrap findByUserIdAndTourId(Long userId, Long tourId);

    Long deleteById(Long scrapId);
}
