package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.MyRealTrip.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //read
    Optional<User> findById(Long id);
}
