package ru.practicum.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatsDto {
    private String app;
    private String uri;
    private long hits;
}
