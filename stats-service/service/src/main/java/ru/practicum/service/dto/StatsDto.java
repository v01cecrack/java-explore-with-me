package ru.practicum.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsDto {
    private String app;
    private String uri;
    private long hits;
}
