package sopt.org.MyRealTrip.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.MyRealTrip.domain.Course;
import sopt.org.MyRealTrip.domain.TourCourse;

import java.util.List;

public interface CourseRepository extends Repository<Course, Long> {
}
