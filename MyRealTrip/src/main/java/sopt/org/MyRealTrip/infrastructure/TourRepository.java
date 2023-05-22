package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sopt.org.MyRealTrip.domain.Location;
import sopt.org.MyRealTrip.domain.Tour;

import java.util.List;


public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAll();

    @Query("select t from Tour t where t.location.city = :city")
    List<Tour> findAllByLocationCity(final String city);

    @Query(value = "select t from Tour t where t.type = :type and t.location.city = :city")
    List<Tour> findAllByLocationCityAndType(final String city, final String type);


}
