package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.request.ScrapRequestDto;
import sopt.org.MyRealTrip.controller.dto.response.ScrapResponseDto;
import sopt.org.MyRealTrip.domain.Scrap;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.domain.User;
import sopt.org.MyRealTrip.infrastructure.ScrapRepository;
import sopt.org.MyRealTrip.infrastructure.TourRepository;
import sopt.org.MyRealTrip.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    @Transactional
    public ScrapResponseDto createScrap(ScrapRequestDto scrapRequestDto) {
        User user = userRepository.findById(scrapRequestDto.getUserId()).orElse(null);

        Tour tour = tourRepository.findById(scrapRequestDto.getTourId()).orElse(null);

        Scrap scrap = Scrap.builder()
                .user(user)
                .tour(tour)
                .build();

        user.addScrap(scrap);
        scrapRepository.save(scrap);
        return ScrapResponseDto.of(scrap.getId(), user.getId(), tour.getId());
    }
}
