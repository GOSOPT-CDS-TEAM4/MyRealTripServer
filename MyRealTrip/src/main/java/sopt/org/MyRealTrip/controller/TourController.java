package sopt.org.MyRealTrip.controller;

import org.springframework.http.HttpStatus;
import sopt.org.MyRealTrip.controller.dto.response.TourResponseDto;
import static sopt.org.MyRealTrip.common.dto.ApiResponseDto.success;
import static sopt.org.MyRealTrip.exception.Success.GET_TOUR_DETAIL_SUCCESS;
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

    private final TourService tourService;
    @GetMapping("/detail/{tourId}")
    public ApiResponseDto<TourResponseDto> getTourDetail(@PathVariable final Long tourId) {
        return success(GET_TOUR_DETAIL_SUCCESS, tourService.getTourDetail(tourId));
    }

}
