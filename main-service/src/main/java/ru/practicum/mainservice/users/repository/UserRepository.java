package ru.practicum.mainservice.users.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.users.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByIdIn(List<Long> userIds, Pageable pageable);

    boolean existsUserByName(String name);
}
