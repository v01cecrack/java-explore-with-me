package ru.practicum.mainservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.client.HitDto;
import ru.practicum.client.StatsClient;
import ru.practicum.client.StatsDto;
import ru.practicum.mainservice.event.dto.EventFullDto;
import ru.practicum.mainservice.event.dto.EventShortDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatisticClient {
    private static final String APP = "main-service";
    private final StatsClient statsClient;

    public void saveHit(String uri, String ip) {
        HitDto hitRequestDto = HitDto.builder()
                .app(APP)
                .uri(uri)
                .ip(ip)
                .timestamp(LocalDateTime.now())
                .build();
        statsClient.createHit(hitRequestDto);
    }


}
