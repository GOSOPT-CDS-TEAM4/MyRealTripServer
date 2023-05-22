package sopt.org.MyRealTrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.MyRealTrip.common.dto.ApiResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.TourResponseDto;
import sopt.org.MyRealTrip.service.TourService;

import static sopt.org.MyRealTrip.common.dto.ApiResponseDto.success;
import static sopt.org.MyRealTrip.exception.Success.GET_TOUR_DETAIL_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tour")
public class TourController {

    private final TourService tourService;
    @GetMapping("/detail/{tourId}")
    public ApiResponseDto<TourResponseDto> getTourDetail(@PathVariable final Long tourId) {
        return success(GET_TOUR_DETAIL_SUCCESS, tourService.getTourDetail(tourId));
    }

}
