package ru.practicum.mainservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.practicum.client.HitDto;
import ru.practicum.client.StatsClient;
import ru.practicum.mainservice.event.dto.EventFullDto;
import ru.practicum.mainservice.event.dto.EventShortDto;
import ru.practicum.service.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatisticClient {
    private static final String APP = "main-service";
    private static final int YEARS_OFFSET = 100;
    private final StatsClient statsClient;

    public ResponseEntity<Object> saveHit(String uri, String ip) {
        HitDto hitRequestDto = HitDto.builder()
                .app(APP)
                .uri(uri)
                .ip(ip)
                .timestamp(LocalDateTime.now())
                .build();
        return statsClient.createHit(hitRequestDto);
    }

    public EventFullDto setViewsNumber(EventFullDto event) {
        List<StatsDto> hits = statsClient.getStats(event.getCreatedOn(), LocalDateTime.now(),
                List.of("/events/" + event.getId()), true);
        if (!hits.isEmpty()) {
            event.setViews(hits.get(0).getHits());
        } else {
            event.setViews(0L);
        }
        return event;
    }

    public List<EventShortDto> setViewsNumber(List<EventShortDto> events) {
        List<String> uris = new ArrayList<>();
        for (EventShortDto eventShortDto : events) {
            uris.add("/events/" + eventShortDto.getId());
        }

        List<StatsDto> hits = statsClient.getStats(LocalDateTime.now().minusYears(YEARS_OFFSET),
                LocalDateTime.now(), uris, true);
        if (!hits.isEmpty()) {
            Map<Long, Integer> hitMap = mapHits(hits);
            for (EventShortDto event : events) {
                event.setViews(hitMap.getOrDefault(event.getId(), 0));
            }
        } else {
            for (EventShortDto event : events) {
                event.setViews(0);
            }
        }
        return events;
    }

    private Map<Long, Integer> mapHits(List<StatsDto> hits) {
        Map<Long, Integer> hitMap = new HashMap<>();
        for (var hit : hits) {
            String hitUri = hit.getUri();
            Long id = Long.valueOf(hitUri.substring(8));
            hitMap.put(id, (int) hit.getHits());
        }
        return hitMap;
    }
}
