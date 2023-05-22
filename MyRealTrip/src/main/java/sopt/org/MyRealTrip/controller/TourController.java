package sopt.org.MyRealTrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.MyRealTrip.common.dto.ApiResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.FilteredTourListResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.TourResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourResponseDto;
import sopt.org.MyRealTrip.exception.Success;
import sopt.org.MyRealTrip.exception.Error;
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
        if (!"paris".equals(Location) && !"global".equals(Location)){
            throw new BusinessException(Error.REQUEST_VALIDATION_EXCEPTION);
        }

        List<RandomTourResponseDto> randomTourListResponseDto= tourService.getRandomTourList(Location);

        return ApiResponseDto.success(Success.GET_RANDOM_TOURLIST_SUCCESS,randomTourListResponseDto);

    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<FilteredTourListResponseDto> gerFilteredTourList(@RequestParam(value = "city") final String city,
                                                                           @RequestParam(value = "order") String order,
                                                                           @RequestParam(value = "minimumPrice") final Long minimumPrice,
                                                                           @RequestParam(value = "maximumPrice") final Long maximumPrice,
                                                                           @RequestParam(value = "tourType") final String tourType,
                                                                           @RequestParam(value = "page") final Long page){
        //원래는 validation은 어노테이션 등록해서 해야하지만.... 정말로 시간이 없으므로 나중에 리팩터링 하기로할께용
        if(!"파리".equals(city) || (!"추천순".equals(order) && !"별점순".equals(order))
                || minimumPrice>maximumPrice || (!"private".equals(tourType) && !"group".equals(tourType) && !"all".equals(tourType))
                || page==null){
            throw new BusinessException(Error.REQUEST_VALIDATION_EXCEPTION);
        }


        return ApiResponseDto.success(Success.GET_FILTERED_TOURLIST_SUCCESS, tourService.getFilterdTourList(city, order, minimumPrice, maximumPrice,tourType,page));

    }

    @GetMapping("/detail/{tourId}")
    public ApiResponseDto<TourResponseDto> getTourDetail(@PathVariable final Long tourId) {
        return ApiResponseDto.success(Success.GET_TOUR_DETAIL_SUCCESS, tourService.getTourDetail(tourId));
    }

}
