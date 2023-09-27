package ru.practicum.mainservice.compilations.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.compilations.model.Compilation;
import ru.practicum.mainservice.event.model.Event;

import java.util.List;

@UtilityClass
public class CompilationMapper {
    public static Compilation toCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        return Compilation.builder()
                .pinned(newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
                .build();

    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();

    }

}
