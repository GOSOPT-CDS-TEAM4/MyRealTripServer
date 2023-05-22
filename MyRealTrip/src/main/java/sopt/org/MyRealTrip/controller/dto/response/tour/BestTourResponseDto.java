package sopt.org.MyRealTrip.controller.dto.response.tour;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BestTourResponseDto {
    private Long id;
    private String name;
    private String image;
    private String city;
    private String itemType;
    private Price price;

    public static BestTourResponseDto of(Long id, String name,String image,
                                         String city, String itemType, Price price){
        return new BestTourResponseDto(id, name,image,city,itemType,price);
    }

}







