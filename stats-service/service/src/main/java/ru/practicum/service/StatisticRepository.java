package ru.practicum.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.service.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRepository extends JpaRepository<EndpointHit, Long> {
    @Query("select new ru.practicum.service.dto.StatsDto(e.app, e.uri, count(e.ip)) " +
            "from EndpointHit e " +
            "where e.timestamp between :start and :end " +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc"
    )
    List<StatsDto> findAllByDateBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "SELECT new ru.practicum.service.dto.StatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM EndpointHit AS h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h.ip) DESC")
    List<StatsDto> findAllStatsByUniqIp(@Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end);

    @Query("select new ru.practicum.service.dto.StatsDto(e.app, e.uri, count(e.ip)) " +
            "from EndpointHit e " +
            "where e.timestamp between :start and :end " +
            "and e.uri in :uris " +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc"
    )
    List<StatsDto> findAllByDateBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uri
    );


    @Query(value = "SELECT new ru.practicum.service.dto.StatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM EndpointHit AS h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "AND h.uri IN :uris " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h.ip) DESC")
    List<StatsDto> findStatsByUrisByUniqIp(@Param("start") LocalDateTime start,
                                            @Param("end") LocalDateTime end,
                                            @Param("uris") List<String> uris);

}
