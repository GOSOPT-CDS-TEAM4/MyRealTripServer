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

    /*
    * "id" : 8,
					"name" : "몽셸미셀 주 야경투어",
					"image" : "https://mblogthumb-phinf.pstatic.net/MjAxODA4MjFfMTEw/MDAxNTM0ODM4OTUwNjI3.enJDiMDXYbjE9KikcZM9FCBEUfhYGrSSxB8fGpd9_XEg.O1sh2G1EoZzSj91g4P7Wux6b7ZU-E5fwLIXShK11Qg4g.JPEG.tiratravel/image_3975768381534827773050.jpg?type=w800",
					"city" : "파리"
					"itemType" : "가이드 투어"
					"price" : {
							"discountFlag" : true,
							"discountedPrice": 219000,
							"originalPrice" : 250000
					}*/

    public static RandomTourResponseDto of(Long id, String name, Price price, String location,
                                           String itemType, String image, Boolean isScrap){
        return new RandomTourResponseDto(id, name,price,location,itemType,image,isScrap);
    }


}


