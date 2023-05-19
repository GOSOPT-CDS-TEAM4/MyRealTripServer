package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.response.tour.RandomTourResponseDto;
import sopt.org.MyRealTrip.infrastructure.TourRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;

    @Transactional(readOnly = true)
    public List<RandomTourResponseDto> getRandomTourList(String location) {

        //RandomTourResponseDto randomTourResponseDto
        return  tourRepository.findAll().stream().map(tour -> RandomTourResponseDto.of(tour.getId(),tour.getTitle()
                ,tour.getPrice(),tour.getLocation().getCity(),tour.getItemType(),tour.getImage(),tour.))


                /*

                 private String location;
    private String itemType;
    private String image;
    private Boolean isScrap;


        @Transactional(readOnly = true)
        public List<PostsListResponseDto> findAllDesc() {
            return postsRepository.findAllDesc().stream()
                    .map(PostsListResponseDto::new)
                    .collect(Collectors.toList());
        }
*/
        return tourList.forEach((tour)->RandomTourListResponseDto.of(tour.));
    }
}
