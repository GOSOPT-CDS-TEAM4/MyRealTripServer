package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.response.tour.FilteredTourListResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.FilteredTourResponseDto;
import sopt.org.MyRealTrip.controller.dto.response.tour.Price;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourResponseDto;
import sopt.org.MyRealTrip.domain.Review;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.User;
import sopt.org.MyRealTrip.exception.model.BusinessException;
import sopt.org.MyRealTrip.infrastructure.TourRepository;
import sopt.org.MyRealTrip.infrastructure.UserRepository;
import sopt.org.MyRealTrip.exception.Error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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

        //1. city와 투어타입, 가격으로 투어리스트 가져오기
        //! 원래 이런 다중 검색은 specification으로 동적  where절을 구현해줘야하지만......
        // 지금 일단 급하니까 수동으로 하기 ㅠㅜ
        List<Tour> allFilteredTourList;


        if("all".equals(tourType)){
            allFilteredTourList = tourRepository.findAllByLocationCity(city);
        }
        else {
           allFilteredTourList = tourRepository.findAllByLocationCityAndType(city,tourType);
        }

        Long averageTourPrice = allFilteredTourList.stream().mapToLong(p->p.getPrice()).sum()/allFilteredTourList.size();



        if("별점순".equals(order)){
            //각 투어의 별점계산
            allFilteredTourList.stream().forEach(alt->{
                List<Review> altReviewList = alt.getReviewList();
                if(altReviewList.size()==0){return;}
                Double sumRating = altReviewList.stream().mapToDouble(r->r.getRating()).sum();
                Double averageRating = sumRating/alt.getReviewList().size();
                alt.setAverageRating(averageRating);
            });
            //투어 별점순 정렬
            Collections.sort( allFilteredTourList, (o1,o2) ->{
                if((o2.getAverageRating() - o1.getAverageRating())>0){return 1;}
                else{return -1;}
            }  );
        } else if ("추천순".equals(order)) {
            //각 투어 추천많은 순으로 정렬
            Collections.sort(allFilteredTourList,(o1,o2)->{
                if((o2.getReviewList().size() - o1.getReviewList().size())>0){return 1;}
                else{return -1;}
            });
        }




        //가격으로 필터링

        ArrayList<Tour> priceFilteredTourList = new ArrayList<Tour>();
        allFilteredTourList.stream().filter(tour -> (tour.getPrice()>=minimumPrice) && (tour.getPrice()<=maximumPrice))
                .forEach(tour -> {
                            priceFilteredTourList.add(tour);
                        }
                        );


        Long totalTourNumber = new Long(priceFilteredTourList.size());


        //페이지 네이션 ㅎ... 원래는 pageable 객체로 하는게 맞지만.... 진짜 급함 진짜진짜로 이런 말도안되는코드 그만~!
        List<Tour> pagedAllFilteredTourList;
        if(priceFilteredTourList.size()<(int)(page*10-10)){
            throw new BusinessException(Error.NOT_FOUND_TOUR_EXCEPTION);
        } else if (priceFilteredTourList.size()<(int)(page*10)) {
            pagedAllFilteredTourList= priceFilteredTourList.subList((int)(page*10-10),priceFilteredTourList.size());
        }
        else{
            pagedAllFilteredTourList= priceFilteredTourList.subList((int)(page*10-10),(int)(page*10));
        }



        // 2. 현재유저가져오기
        User nowUser = userRepository.findById(1L).get(); //현재 로직에서 유저는 유저아이디 1로 고정되어있음.

        // 3. 해당 리스트들이 현재유저가 스크랩했는지 여부 확인
            pagedAllFilteredTourList.forEach(pft -> {
            nowUser.getScrapList().forEach(scrap -> {
                if(scrap.getUser().equals(nowUser)&&scrap.getTour().equals(pft)){
                    pft.setIsScrap(true);
                }
            });
            });

            //4. 알맞은 DTO로 만들기

        List<FilteredTourResponseDto> filteredTourResponseDtos= new ArrayList<>();
        pagedAllFilteredTourList.stream().forEach(pft->{
            filteredTourResponseDtos.add(FilteredTourResponseDto.of(pft.getId(),pft.getTitle(),pft.getImage(),
                    pft.getIsScrap(),pft.getFreeCancel(),pft.getType(),new Long(pft.getReviewList().size()),
                    pft.getAverageRating(),Price.of(pft.getDiscountedPrice(),pft.getPrice())));
        });

        return FilteredTourListResponseDto.of(totalTourNumber,averageTourPrice, filteredTourResponseDtos);

    }
}
