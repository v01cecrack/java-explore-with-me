package ru.practicum.service;

import lombok.experimental.UtilityClass;
import ru.practicum.service.dto.HitDto;

@UtilityClass
public class Mapper {
    public static EndpointHit toEndpointHit(HitDto hitDto) {
        return EndpointHit.builder()
                .app(hitDto.getApp())
                .ip(hitDto.getIp())
                .uri(hitDto.getUri())
                .timestamp(hitDto.getTimestamp())
                .build();
    }
}
