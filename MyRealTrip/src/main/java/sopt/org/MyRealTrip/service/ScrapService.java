package sopt.org.MyRealTrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.MyRealTrip.controller.dto.request.ScrapRequestDto;
import sopt.org.MyRealTrip.controller.dto.response.ScrapResponseDto;
import sopt.org.MyRealTrip.domain.Scrap;
import sopt.org.MyRealTrip.domain.Tour;
import sopt.org.MyRealTrip.infrastructure.ScrapRepository;
import sopt.org.MyRealTrip.infrastructure.TourRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final TourRepository tourRepository;

    @Transactional
    public ScrapResponseDto createScrap(ScrapRequestDto scrapRequestDto) {

        Tour tour = tourRepository.findById(scrapRequestDto.getTourId()).orElse(null);

        Scrap scrap = Scrap.builder()
                .tour(tour)
                .build();

        scrapRepository.save(scrap);
        return ScrapResponseDto.of(scrap.getId(), 1L, tour.getId());
    }

    public Optional<Long> deleteScrap(Long scrapId) {
        if (scrapRepository.findById(scrapId).isPresent()) {
            scrapRepository.deleteById(scrapId);
            return Optional.of(scrapId);
        } else
            return Optional.empty();
    }
}
