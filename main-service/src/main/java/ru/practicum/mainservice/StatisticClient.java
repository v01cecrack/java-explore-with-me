package ru.practicum.mainservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.client.HitDto;
import ru.practicum.client.StatsClient;

import java.time.LocalDateTime;

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
