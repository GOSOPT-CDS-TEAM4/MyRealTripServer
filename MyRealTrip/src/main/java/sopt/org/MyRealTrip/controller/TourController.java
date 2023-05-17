package sopt.org.MyRealTrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.MyRealTrip.common.dto.ApiResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourListResponseDto;
import sopt.org.MyRealTrip.exception.Success;
import sopt.org.MyRealTrip.service.TourService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tour")
public class TourController {
    private final TourService tourService;

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<RandomTourListResponseDto> getRandomTourList(@RequestHeader("Location") final String location){
        RandomTourListResponseDto randomTourListResponseDto= tourService.getRandomTourList(location);

        return new ApiResponseDto.success(Success.GET_RANDOM_TOURLIST_SUCCESS,randomTourListResponseDto);


    }
}
