package sopt.org.MyRealTrip.controller.dto.response.tour;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {
    private Boolean discountFlag;
    private Long discountedPrice;
    private Long originalPrice;

    public static Price of(Boolean discountFlag, Long discountedPrice, Long originalPrice){
        return new Price(discountFlag, discountedPrice, originalPrice);

    }
}
