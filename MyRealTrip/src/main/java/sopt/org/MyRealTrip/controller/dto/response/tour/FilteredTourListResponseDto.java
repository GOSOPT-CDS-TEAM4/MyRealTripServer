package sopt.org.MyRealTrip.controller.dto.response.tour;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FilteredTourListResponseDto {
    private Long totalTourNumber;
    private Long averageTourPrice;
    private List<FilteredTourResponseDto> tourList;

    public static FilteredTourListResponseDto of(Long totalTourNumber, Long averageTourPrice,
                                                 List<FilteredTourResponseDto> tourList){
        return new FilteredTourListResponseDto(totalTourNumber, averageTourPrice, tourList);
    }
}

