package ru.yandex.practicum.filmorate.storage.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage{

    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> findAll() {
        log.info("Get all users");
        return users.values();
    }

    public User create(@Valid @RequestBody User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(getNextId());
        users.put(user.getId(), user);
        log.info("Create USER: " + user);
        return user;
    }

    public User update(@Valid @RequestBody User newUser) {
        if (newUser.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (users.containsKey(newUser.getId())) {
            User oldUser = users.get(newUser.getId());
            log.info("Update User: " + oldUser);
            oldUser.setEmail(newUser.getEmail());
            if (!(newUser.getLogin() == null)) {
                oldUser.setLogin(newUser.getLogin());
            }
            if (!(newUser.getName() == null)) {
                oldUser.setName(newUser.getName());
            }
            if (!(newUser.getBirthday() == null)) {
                oldUser.setBirthday(newUser.getBirthday());
            }
            log.info("Update User: " + oldUser);
            return oldUser;
        }
        throw new NotFoundException("User с ID: " + newUser.getId() + " не найден.");
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

}
