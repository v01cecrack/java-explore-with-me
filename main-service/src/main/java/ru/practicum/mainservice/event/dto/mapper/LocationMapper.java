package ru.practicum.mainservice.event.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.event.dto.LocationDto;
import ru.practicum.mainservice.event.model.Location;

@UtilityClass
public class LocationMapper {
    public static final Location toLocation(LocationDto locationDto) {
        return Location.builder()
                .lat(locationDto.getLat())
                .lon(locationDto.getLon())
                .build();
    }

    public static final LocationDto toLocationDto(Location location) {
        return LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}
