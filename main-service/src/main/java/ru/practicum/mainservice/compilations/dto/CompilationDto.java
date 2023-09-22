package ru.practicum.mainservice.compilations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.mainservice.event.dto.EventShortDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    private Long id;
    //Закреплена ли подборка на главной странице сайта
    private Boolean pinned;
    private String title;
    private List<EventShortDto> events;


}
