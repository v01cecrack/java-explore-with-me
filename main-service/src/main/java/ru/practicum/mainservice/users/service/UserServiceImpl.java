package ru.practicum.mainservice.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainservice.exception.ConflictException;
import ru.practicum.mainservice.exception.ObjectNotFoundException;
import ru.practicum.mainservice.users.dto.NewUserRequestDto;
import ru.practicum.mainservice.users.dto.UserDto;
import ru.practicum.mainservice.users.dto.UserMapper;
import ru.practicum.mainservice.users.model.User;
import ru.practicum.mainservice.users.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        int offset = from > 0 ? from / size : 0;
        PageRequest page = PageRequest.of(offset, size);
        List<User> users;
        if (ids == null || ids.isEmpty()) {
            users = userRepository.findAll(page).getContent();
        } else {
            users = userRepository.findByIdIn(ids, page);
        }
        log.info("Запрос GET на поиск пользователей, с ids: {}", ids);

        return users.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(NewUserRequestDto userRequestDto) {
        if (userRepository.existsUserByName(userRequestDto.getName())) {
            throw new ConflictException("Такой пользователь уже есть");
        }
        User user = UserMapper.toUser(userRequestDto);
        log.info("Запрос POST на сохранение пользователя: {}", user.getName());
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        if (!isUserExists(userId)) {
            throw new ObjectNotFoundException("Пользователя не существует!");
        }
        log.info("Запрос DELETE на удаление пользователя: c id: {}", userId);
        userRepository.deleteById(userId);
    }

    public boolean isUserExists(Long userId) {
        var userOptional = userRepository.findById(userId);
        return !userOptional.isEmpty();
    }

}
