package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.friendship.FriendshipDbStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    private final UserDbStorage userDbStorage;
    private final FriendshipDbStorage friendshipDbStorage;

    public User create(User user) {
        return userDbStorage.create(user);
    }


    public List<User> getUsers() {
        return userDbStorage.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDbStorage.findById(id);
    }

    public User updateUser(User user) {
        return userDbStorage.update(user);
    }


    public void addFriend(Long id, Long friendId) {
        if (userDbStorage.findById(id).isEmpty() || userDbStorage.findById(friendId).isEmpty()) {
            throw new NotFoundException("Пользователь не найден.");
        }
        if (id < 0 || friendId < 0) {
            throw new NotFoundException("Пользователь не найден.");
        }
        friendshipDbStorage.addFriend(id, friendId);
    }

    public List<User> getAllFriends(Long id) {
        return friendshipDbStorage.getAllFriends(id);
    }

    public void deleteFriend(long userId, long friendId) {
        Optional<User> user = userDbStorage.findById(userId);
        Optional<User> friend = userDbStorage.findById(friendId);
        if (user.isEmpty() || friend.isEmpty()) {
            throw new NotFoundException("Пользователь не найден.");
        }
        friendshipDbStorage.removeFriend(userId, friendId);
    }

    public List<User> getCommonFriends(long id, long friendId) {
        return friendshipDbStorage.getCommonFriends(id, friendId);
    }
}
