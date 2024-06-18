package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserStorage userStorage;

    public void addFriend(long userId, long friendId) {
        User user = userStorage.findUserById(userId);
        User friend = userStorage.findUserById(friendId);
        if (user != null && friend != null) {
            user.getFriends().add(friendId);
            friend.getFriends().add(userId);
        } else {
            throw new NotFoundException("User не найден.");
        }
    }

    public void deleteFriend(long userId, long friendId) {
        User user = userStorage.findUserById(userId);
        User friend = userStorage.findUserById(friendId);
        if (user != null && friend != null) {
            user.getFriends().remove(friendId);
            friend.getFriends().remove(userId);
        } else {
            throw new NotFoundException("User не найден.");
        }
    }

    public List<User> getAllFriends(long userId) {
        List<User> friends = new ArrayList<>();
        User user = userStorage.findUserById(userId);
        if (user != null) {
            for (Long id : user.getFriends()) {
                friends.add(userStorage.findUserById(id));
            }
        } else {
            throw new NotFoundException("User не найден.");
        }
        return friends;
    }

    public Collection<User> getCommonFriends(Long id, Long otherId) {
        return getAllFriends(id).stream()
                .filter(x -> getAllFriends(otherId).contains(x))
                .collect(Collectors.toList());
    }
}
