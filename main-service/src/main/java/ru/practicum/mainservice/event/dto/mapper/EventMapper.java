package ru.practicum.mainservice.event.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.categories.dto.CategoriesMapper;
import ru.practicum.mainservice.categories.dto.CategoryDto;
import ru.practicum.mainservice.categories.model.Categories;
import ru.practicum.mainservice.event.dto.EventFullDto;
import ru.practicum.mainservice.event.dto.EventShortDto;
import ru.practicum.mainservice.event.dto.NewEventDto;
import ru.practicum.mainservice.event.model.Event;
import ru.practicum.mainservice.event.model.Location;
import ru.practicum.mainservice.event.model.State;
import ru.practicum.mainservice.users.dto.UserDto;
import ru.practicum.mainservice.users.dto.UserMapper;
import ru.practicum.mainservice.users.model.User;

@UtilityClass
public class EventMapper {

    public static EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoriesMapper.toCategoryDto(event.getCategory()))
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.toUserDto(event.getInitiator()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .location(LocationMapper.toLocationDto(event.getLocation()))
                .build();
    }

    public static EventShortDto toEventShortDto(Event event, CategoryDto categoryDto, UserDto userDto) {
        return EventShortDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(categoryDto)
                .confirmedRequests(event.getParticipantLimit())
                .eventDate(event.getEventDate())
                .initiator(userDto)
                .paid(event.getPaid())
                .title(event.getTitle())
                .build();

    }

    public static EventShortDto mapToShortDto(Event event) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoriesMapper.toCategoryDto(event.getCategory()))
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.toUserDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .build();
    }

    public static Event toEvent(NewEventDto newEventDto, Categories categories, Location location, User user) {
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .category(categories)
                .description(newEventDto.getDescription())
                .eventDate(newEventDto.getEventDate())
                .location(location)
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration())
                .title(newEventDto.getTitle())
                .initiator(user)
                .state(State.PENDING)
                .build();
    }

}

