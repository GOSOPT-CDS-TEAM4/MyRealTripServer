package sopt.org.MyRealTrip.controller.dto.response.tour;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.MyRealTrip.controller.dto.response.CourseResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.GuideResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.ReviewTotalResponseDto;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class TourResponseDto {
    private Long id;
    private String title;
    private String image;
    private String country;
    private String city;
    private ReviewTotalResponseDto reviewTotalResponseDto;
    private List<CourseResponseDto> courseResponseDtoList;
    private Long price;
    private Long discountedPrice;
    private String includedOption;
    private String excludedOption;
    private Boolean freeCancle;
    private String itemType;
    private String type;
    private String transfortation;
    private LocalTime requiredTime;
    private String language;
    private String noticeTitle;
    private String notice;
    private String descriptionTitle;
    private String description;
    private Long minPeople;
    private Long maxPeople;
    private GuideResponseDto guideResponseDto;

    public static TourResponseDto of(Long id, String title, String image, String country, String city,
                                     ReviewTotalResponseDto reviewTotalResponseDto, List<CourseResponseDto> courseResponseDtoList,
                                     Long price, Long discountedPrice, String includedOption, String excludedOption,
                                     Boolean freeCancle, String itemType, String type, String transfortation,
                                     LocalTime requiredTime, String language, String noticeTitle, String notice,
                                     String descriptionTitle, String description, Long minPeople, Long maxPeople,
                                     GuideResponseDto guideResponseDto) {

        return new TourResponseDto(id, title, image, country, city,
                reviewTotalResponseDto, courseResponseDtoList, price, discountedPrice,
                includedOption, excludedOption, freeCancle, itemType,
                type, transfortation, requiredTime, language,
                noticeTitle, notice, descriptionTitle, description,
                minPeople, maxPeople, guideResponseDto);
    }
}
