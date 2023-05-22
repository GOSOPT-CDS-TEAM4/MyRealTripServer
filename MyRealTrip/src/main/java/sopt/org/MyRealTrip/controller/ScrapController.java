package sopt.org.MyRealTrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.MyRealTrip.common.dto.ApiResponseDto;
import sopt.org.MyRealTrip.controller.dto.request.ScrapRequestDto;
import sopt.org.MyRealTrip.controller.dto.response.ScrapResponseDto;
import sopt.org.MyRealTrip.infrastructure.ScrapRepository;
import sopt.org.MyRealTrip.infrastructure.TourRepository;
import sopt.org.MyRealTrip.service.ScrapService;

import javax.validation.Valid;

import java.util.Optional;

import static sopt.org.MyRealTrip.common.dto.ApiResponseDto.error;
import static sopt.org.MyRealTrip.common.dto.ApiResponseDto.success;
import static sopt.org.MyRealTrip.exception.Error.*;
import static sopt.org.MyRealTrip.exception.Success.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scrap")
public class ScrapController {
    private final ScrapService scrapService;
    private final TourRepository tourRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<ScrapResponseDto> createScrap(@RequestBody @Valid final ScrapRequestDto request) {
        Long userId = 1L;
        Long tourId = request.getTourId();

        if (userId > 1) {
            return error(NOT_FOUND_USER_EXCEPTION);
        }
        if (tourId > tourRepository.countBy()) {
            return error(NOT_FOUND_TOUR_EXCEPTION);
        }

        return success(CREATE_SCRAP_SUCCESS, scrapService.createScrap(request));
    }

    @DeleteMapping("{scrapId}")
    public ApiResponseDto deleteScrap(@PathVariable("scrapId") Long scrapId){
        Optional<Long> id = scrapService.deleteScrap(scrapId);
        if (id.isPresent()){
            return success(DELETE_SCRAP_SUCCESS, id);
        }
        return error(NOT_FOUND_SCRAP_EXCEPTION);
    }
}
