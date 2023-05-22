package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.response.tour.FilteredTourListResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.Price;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourResponseDto;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.User;
import sopt.org.MyRealTrip.infrastructure.TourRepository;
import sopt.org.MyRealTrip.infrastructure.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<RandomTourResponseDto> getRandomTourList(String location) {
        //1. 투어리스트 가져오기

        List<Tour> tourList;

        if(location.equals("paris")){
            location = "파리";
            tourList = tourRepository.findAllByLocationCity(location);
        } else {
            tourList = tourRepository.findAll();
        }
        //2. 투어리스트 랜덤으로 빼서 자르기
        Collections.shuffle(tourList);
        if(tourList.size()>5){
            List<Tour> randomTourList = tourList.subList(0,5);

        }
        List<Tour> randomTourList = tourList;



        //3. 유저의 스크랩한 코스가져오기
        User nowUser = userRepository.findById(1L).get(); //현재 로직에서 유저는 유저아이디 1로 고정되어있음.

        ArrayList<RandomTourResponseDto> randomTourResponseDtos = new ArrayList<RandomTourResponseDto>();
        randomTourList.forEach(rt->{
            //4. 투어리스트와 스크랩한 코스를 비교해 현재 유저가 스크랩했는지 여부 생성
            nowUser.getScrapList().forEach(scrap -> {
                if(scrap.getUser().equals(nowUser) && scrap.getTour().equals(rt)){
                    rt.setIsScrap(true);
                }
            });

            //5. responseDto 형식에 맞추어 보내기
            RandomTourResponseDto tempRandomResponse = RandomTourResponseDto
                    .of(rt.getId(), rt.getTitle(),Price.of(rt.getDiscountedPrice(),rt.getPrice())
                            ,rt.getLocation().getCity(),rt.getItemType(),rt.getImage(),rt.getIsScrap());
            randomTourResponseDtos.add(tempRandomResponse);

        });
        //5. responseDto 형식에 맞추어 보내기
        return randomTourResponseDtos;


    }

    @Transactional(readOnly = true)
    public FilteredTourListResponseDto getFilterdTourList(String city, String order,
                                                          Long minimumPrice, Long maximumPrice,
                                                          String tourType, Long page){

    }
}
