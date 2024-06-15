package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserStorage userStorage;

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public Collection<User> findAll() {
        return userStorage.findAll();
    }

    public void addFriend(long userId, long friendId) {
        userStorage.addFriend(userId, friendId);
        log.info("Пользователи с id {} и {} теперь друзья", userId, friendId);
    }

    public void deleteFriend(long userId, long friendId) {
        userStorage.deleteFriend(userId, friendId);
        log.info("Пользователи с id {} и {} теперь не являются друзьями", userId, friendId);
    }

    public List<User> getAllFriends(long userId) {
        return userStorage.getAllFriends(userId);
    }


    public User findUserById(long id) {
        return userStorage.findUserById(id);
    }

    public Collection<User> getCommonFriends(Long id, Long otherId) {
        return userStorage.getAllFriends(id).stream()
                .filter(x -> userStorage.getAllFriends(otherId).contains(x))
                .collect(Collectors.toList());
    }
}
