package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.service.dto.HitDto;
import ru.practicum.service.dto.StatisticDto;
import ru.practicum.service.dto.StatsDto;

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
    public List<StatsDto> getStats(StatisticDto dto) {
        if (dto.getUnique()) {
            return statisticRepository.findStatsUnique(dto.getStart(), dto.getEnd(), dto.getUris());
        } else {
            return statisticRepository.findStats(dto.getStart(), dto.getEnd(), dto.getUris());
        }
    }
}
