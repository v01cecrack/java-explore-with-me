package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.dto.HitDto;
import ru.practicum.service.dto.StatsDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class StatisticController {
    private final static String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final StatisticService statisticService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEndpoint(@Valid @RequestBody HitDto hitDto) {
        log.info("POST hit ={},", hitDto);
        statisticService.saveEndpoint(hitDto);
    }

    @GetMapping("/stats")
    public List<StatsDto> getStats(
            @RequestParam("start") @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(pattern = TIME_PATTERN) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("GET stats");
        return statisticService.getStats(start, end, uris, unique);
    }
}
