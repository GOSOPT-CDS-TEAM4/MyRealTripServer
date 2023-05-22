package sopt.org.MyRealTrip.controller.dto.response.tour;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FilteredTourResponseDto {
    private Long id;
    private String name;
    private String image;
    private Boolean isScrap;
    private Boolean freeCancel;
    private String tourType;
    private Long reviewNumber;
    private Double totalRating;
    private Price price;



    public static FilteredTourResponseDto of(Long id, String name, String image, Boolean isScrap,
                                             Boolean freeCancel,String tourType, Long reviewNumber,
                                             Double totalRating, Price price){
        return new FilteredTourResponseDto(id, name,image,isScrap, freeCancel, tourType, reviewNumber, totalRating, price);
    }
}






