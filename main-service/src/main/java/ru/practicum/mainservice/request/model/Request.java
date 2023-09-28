package ru.practicum.mainservice.request.model;

import lombok.*;
import ru.practicum.mainservice.event.model.Event;
import ru.practicum.mainservice.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Дата и время создания заявки
     */
    @Column(nullable = false)
    private LocalDateTime created;
    /**
     * Дата и время создания заявки
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParticipationRequestStatus status;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
