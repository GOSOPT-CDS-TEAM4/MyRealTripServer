package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.response.*;
import sopt.org.MyRealTrip.domain.Review;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.TourCourse;
import sopt.org.MyRealTrip.exception.Error;
import sopt.org.MyRealTrip.exception.model.BusinessException;
import sopt.org.MyRealTrip.infrastructure.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor()
public class TourService {
    private final TourCourseRepository tourCourseRepository;
    private final TourRepository tourRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public TourResponseDto getTourDetail(Long tourId) {
        Tour tour = getTour(tourId);

        List<TourCourse> tourCourseList = tourCourseRepository.findByTour(tour);
        List<Review> reviewList = reviewRepository.findByTour(tour);

        String locationDescription = "";
        String locationTime = "";

        long totalNumber = getTotalNumber(reviewList);
        double totalRating = getTotalRating(reviewList);


        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();


        for (TourCourse tourCourse : tourCourseList){
            courseResponseDtoList.add(new CourseResponseDto(
                    tourCourse.getCourse().getId(), tourCourse.getCourse().getLocation(),
                    tourCourse.getCourse().getTime(), tourCourse.getCourse().getTitle(),
                    tourCourse.getCourse().getDescription(), tourCourse.getCourse().getImage()));

            if (tourCourse.getIndex() == 1) {
                locationDescription = String.valueOf(tourCourse.getCourse().getDescription());
            }
            if (tourCourse.getIndex() == 2) {
                locationTime = String.valueOf(tourCourse.getCourse().getTime());
            }
        }

        for (Review review : reviewList) {
            reviewResponseDtoList.add(new ReviewResponseDto(
                    review.getUser().getNickname(), review.getRating(), review.getKeyword1(),
                    review.getKeyword2(), review.getCreatedAt(), review.getContent()));
        }


        GuideResponseDto guideResponseDto = new GuideResponseDto(locationTime, new LocationResponseDto(tour.getLocation().getLatitude(), tour.getLocation().getLongitude(), locationDescription));
        ReviewTotalResponseDto reviewTotalResponseDto = new ReviewTotalResponseDto(totalNumber, totalRating, reviewResponseDtoList);

        return TourResponseDto.of(tour.getId(), tour.getTitle(), tour.getImage(), tour.getLocation().getCountry(), tour.getLocation().getCity(),
                reviewTotalResponseDto, courseResponseDtoList, tour.getPrice(), tour.getDiscountedPrice(),
                tour.getIncludedOption(), tour.getExcludedOption(), tour.getFreeCancel(), tour.getItemType(),
                tour.getType(), tour.getTransfortation(), tour.getRequiredTime(), tour.getLanguage(),
                tour.getNoticeTitle(), tour.getNotice(), tour.getDescriptionTitle(), tour.getDescription(),
                tour.getMinPeople(), tour.getMaxPeople(), guideResponseDto);
    }

    private Tour getTour(Long tourId) {
        return tourRepository.findById(tourId)
                .orElseThrow(() -> new BusinessException(Error.NOT_FOUND_TOUR_EXCEPTION));
    }

    private long getTotalNumber(List<Review> reviewList) {
        return reviewList.stream()
                .collect(Collectors.toList())
                .size();
    }
    private double getTotalRating(List<Review> reviewList) {
        return reviewList.stream()
                .mapToDouble(review -> review.getRating())
                .average()
                .getAsDouble();
    }

}



