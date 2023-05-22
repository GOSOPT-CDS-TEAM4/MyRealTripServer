package sopt.org.MyRealTrip.infrastructure;
import org.springframework.data.repository.Repository;
import sopt.org.MyRealTrip.domain.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    Optional<User> findById(Long userId);
}
