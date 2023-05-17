package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourListResponseDto;
import sopt.org.MyRealTrip.infrastructure.TourRepository;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;

    public RandomTourListResponseDto getRandomTourList(String location){

    }
}
