package ru.practicum.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.service.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRepository extends JpaRepository<EndpointHit, Long> {
    @Query(value =
            "select new ru.practicum.service.dto.StatsDto(eh.app, eh.uri, count(eh.ip)) " +
                    "from EndpointHit eh " +
                    "where eh.timestamp between :start and :end " +
                    "and (coalesce(:uris, null) is null or eh.uri IN :uris) " +
                    "group by eh.app, eh.uri " +
                    "order by count(eh.ip) desc"
    )
    List<StatsDto> findStats(@Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end,
                             @Param("uris") List<String> uris);

    @Query(value =
            "select new ru.practicum.service.dto.StatsDto(eh.app, eh.uri, count(distinct eh.ip)) " +
                    "from EndpointHit eh " +
                    "where eh.timestamp between :start and :end " +
                    "and (coalesce(:uris, null) is null or eh.uri in :uris) " +
                    "group by eh.app, eh.uri " +
                    "order by count(eh.ip) desc"
    )
    List<StatsDto> findStatsUnique(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   @Param("uris") List<String> uris);
}
