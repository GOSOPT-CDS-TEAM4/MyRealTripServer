package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.response.*;
import sopt.org.MyRealTrip.controller.dto.response.tour.Price;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourResponseDto;
import sopt.org.MyRealTrip.domain.Review;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.TourCourse;
import sopt.org.MyRealTrip.domain.User;
import sopt.org.MyRealTrip.exception.Error;
import sopt.org.MyRealTrip.exception.model.BusinessException;
import sopt.org.MyRealTrip.infrastructure.ReviewRepository;
import sopt.org.MyRealTrip.infrastructure.TourRepository;
import sopt.org.MyRealTrip.infrastructure.UserRepository;
import sopt.org.MyRealTrip.infrastructure.TourCourseRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final TourCourseRepository tourCourseRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<RandomTourResponseDto> getRandomTourList(String location) {
        //1. 투어리스트 가져오기

        List<Tour> tourList;

        if(location.equals("paris")){
            location = "파리";
            tourList = tourRepository.findAllByLocationCity(location);
        } else {
            tourList = tourRepository.findAll();
        }
        //2. 투어리스트 랜덤으로 빼서 자르기
        Collections.shuffle(tourList);
        List<Tour> randomTourList=tourList;
        if(tourList.size()>5){
            randomTourList = tourList.subList(0,5);
        }


        //3. 유저의 스크랩한 코스가져오기
        User nowUser = userRepository.findById(1L).get(); //현재 로직에서 유저는 유저아이디 1로 고정되어있음.

        ArrayList<RandomTourResponseDto> randomTourResponseDtos = new ArrayList<RandomTourResponseDto>();
        randomTourList.forEach(rt->{
            //4. 투어리스트와 스크랩한 코스를 비교해 현재 유저가 스크랩했는지 여부 생성
            nowUser.getScrapList().forEach(scrap -> {
                if(scrap.getUser().equals(nowUser) && scrap.getTour().equals(rt)){
                    rt.setIsScrap(true);
                }
            });

            //5. responseDto 형식에 맞추어 보내기
            RandomTourResponseDto tempRandomResponse = RandomTourResponseDto
                    .of(rt.getId(), rt.getTitle(),Price.of(rt.getDiscountedPrice(),rt.getPrice())
                            ,rt.getLocation().getCity(),rt.getItemType(),rt.getImage(),rt.getIsScrap());
            randomTourResponseDtos.add(tempRandomResponse);

        });
        //5. responseDto 형식에 맞추어 보내기
        return randomTourResponseDtos;


    }


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



