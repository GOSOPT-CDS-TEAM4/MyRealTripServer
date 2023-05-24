package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.request.ScrapRequestDto;
import sopt.org.MyRealTrip.controller.dto.response.ScrapResponseDto;
import sopt.org.MyRealTrip.domain.Scrap;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.User;
import sopt.org.MyRealTrip.exception.Error;
import sopt.org.MyRealTrip.exception.model.BusinessException;
import sopt.org.MyRealTrip.infrastructure.ScrapRepository;
import sopt.org.MyRealTrip.infrastructure.TourRepository;
import sopt.org.MyRealTrip.infrastructure.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScrapResponseDto createScrap(ScrapRequestDto scrapRequestDto) {

        Tour tour = tourRepository.findById(scrapRequestDto.getTourId()).orElse(null);
        User user = userRepository.findById(1L).get();
        user.getScrapList().forEach(userScrap->{
            if(userScrap.getTour().equals(tour)){
                throw new BusinessException(Error.ALREADY_SCRAP_EXCEPTION);
            }
        });
        Scrap scrap = Scrap.builder()
                .user(user)
                .tour(tour)
                .build();

        scrapRepository.save(scrap);
        return ScrapResponseDto.of(scrap.getId(), 1L, tour.getId());
    }

    public Optional<Long> deleteScrap(Long tourId) {

        Scrap scrap = scrapRepository.findByUserIdAndTourId(1L,tourId);

        if(scrap==null){
            return Optional.empty();
        }
        else{
            scrapRepository.deleteById(scrap.getId());
            return Optional.of(scrap.getId());

        }

    }
}
