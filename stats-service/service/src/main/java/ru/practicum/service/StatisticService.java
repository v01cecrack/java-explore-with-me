package ru.practicum.service;

import ru.practicum.service.dto.HitDto;
import ru.practicum.service.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticService {

    void saveEndpoint(HitDto hitDto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
