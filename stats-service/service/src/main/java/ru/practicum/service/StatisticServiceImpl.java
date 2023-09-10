package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.service.dto.HitDto;
import ru.practicum.service.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Override
    public void saveEndpoint(HitDto hitDto) {
        statisticRepository.save(Mapper.toEndpointHit(hitDto));
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (unique) {
            return statisticRepository.findStatsUnique(start, end, uris);
        } else {
            return statisticRepository.findStats(start, end, uris);
        }
    }
}
