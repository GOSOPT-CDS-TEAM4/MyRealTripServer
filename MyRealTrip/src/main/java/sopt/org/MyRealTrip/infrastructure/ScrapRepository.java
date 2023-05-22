package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.MyRealTrip.domain.Scrap;

import java.util.Optional;

public interface ScrapRepository extends Repository<Scrap, Long> {
    Scrap save(Scrap scrap);

    Optional<Long> findById(Long scrapId);

    Long deleteById(Long scrapId);
}
