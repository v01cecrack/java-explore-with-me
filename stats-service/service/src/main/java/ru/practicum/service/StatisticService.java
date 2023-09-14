package ru.practicum.service;

import ru.practicum.service.dto.HitDto;
import ru.practicum.service.dto.StatisticDto;
import ru.practicum.service.dto.StatsDto;

import java.util.List;

public interface StatisticService {

    void saveEndpoint(HitDto hitDto);

    List<StatsDto> getStats(StatisticDto dto);
}
