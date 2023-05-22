package sopt.org.MyRealTrip.controller.dto.response.tour;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomTourResponseDto {
    private Long id;
    private String name;
    private Price price;
    private String location;
    private String itemType;
    private String image;
    private Boolean isScrap;


    public static RandomTourResponseDto of(Long id, String name, Price price, String location,
                                           String itemType, String image, Boolean isScrap){
        return new RandomTourResponseDto(id, name,price,location,itemType,image,isScrap);
    }


}


