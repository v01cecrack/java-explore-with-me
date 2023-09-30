package ru.practicum.mainservice.request;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.event.model.Event;
import ru.practicum.mainservice.request.model.ParticipationRequestStatus;
import ru.practicum.mainservice.request.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequesterId(Long id);

    List<Request> findByEventId(Long eventId);

    Long countAllByEventIdAndStatus(Long eventId, ParticipationRequestStatus state);

    List<Request> findAllByIdIn(List<Long> ids);

    boolean existsRequestByRequester_IdAndEvent_Id(Long requesterId, Long eventId);

    List<Request> findAllByEventInAndStatus(List<Event> events,ParticipationRequestStatus status);

}
