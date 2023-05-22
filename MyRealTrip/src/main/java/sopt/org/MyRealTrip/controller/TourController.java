package sopt.org.MyRealTrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.MyRealTrip.common.dto.ApiResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourResponseDto;
import sopt.org.MyRealTrip.exception.Error;
import sopt.org.MyRealTrip.exception.Success;
import sopt.org.MyRealTrip.exception.model.BusinessException;
import sopt.org.MyRealTrip.service.TourService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tour")
public class TourController {
    private final TourService tourService;

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<RandomTourResponseDto>> getRandomTourList(@RequestHeader("Location") final String Location) {

        // header Location에 관한 에러처리
        if (!"paris".equals(Location) && !Location.equals("global")){
            throw new BusinessException(Error.REQUEST_VALIDATION_EXCEPTION);
        }

        List<RandomTourResponseDto> randomTourListResponseDto= tourService.getRandomTourList(Location);

        System.out.println(randomTourListResponseDto.toString());
        return ApiResponseDto.success(Success.GET_RANDOM_TOURLIST_SUCCESS,randomTourListResponseDto);

    }
}
