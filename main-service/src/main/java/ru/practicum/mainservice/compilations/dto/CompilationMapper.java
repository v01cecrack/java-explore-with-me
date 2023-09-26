package ru.practicum.mainservice.compilations.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.categories.dto.CategoriesMapper;
import ru.practicum.mainservice.compilations.model.Compilation;
import ru.practicum.mainservice.event.dto.EventShortDto;
import ru.practicum.mainservice.event.dto.mapper.EventMapper;
import ru.practicum.mainservice.event.model.Event;
import ru.practicum.mainservice.users.dto.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {
    public static Compilation toCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        return Compilation.builder()
                .pinned(newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
                .events(events)
                .build();

    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(maptoDto(compilation.getEvents()))
                .build();

    }

    private List<EventShortDto> maptoDto(List<Event> events) {
        List<EventShortDto> eventShortDto = events.stream().map(event ->
                EventMapper.toEventShortDto(
                        event,
                        CategoriesMapper.toCategoryDto(event.getCategory()),
                        UserMapper.toUserDto(event.getInitiator())
                )).collect(Collectors.toList());
        return eventShortDto;
    }
}
