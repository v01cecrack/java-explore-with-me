package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.service.dto.HitDto;
import ru.practicum.service.dto.StatisticDto;
import ru.practicum.service.dto.StatsDto;

import javax.validation.ValidationException;
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
        List<StatsDto> viewStatsList;

        if (dto.getStart().isAfter(dto.getEnd())) {
            throw new ValidationException("Время начала не может быть позднее даты конца диапазона!");
        }
        if (dto.getUris() == null || dto.getUris().isEmpty()) {
            if (dto.getUnique()) {
                viewStatsList = statisticRepository.findAllStatsByUniqIp(dto.getStart(), dto.getEnd());
            } else {
                viewStatsList = statisticRepository.findAllByDateBetween(dto.getStart(), dto.getEnd());
            }
        } else {
            if (dto.getUnique()) {
                viewStatsList = statisticRepository.findStatsByUrisByUniqIp(dto.getStart(), dto.getEnd(), dto.getUris());
            } else {
                viewStatsList = statisticRepository.findAllByDateBetween(dto.getStart(), dto.getEnd(), dto.getUris());
            }

        }

        return viewStatsList;

    }
}
