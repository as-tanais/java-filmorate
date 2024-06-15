package ru.yandex.practicum.filmorate.storage.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;

public interface UserStorage {
    Collection<User> findAll();

    User update(@Valid @RequestBody User newUser);

    User create(@Valid @RequestBody User user);

    User findUserById(long id);

    void addFriend(long userId, long friendId);

    List<User> getAllFriends(long userId);

    void deleteFriend(long userId, long friendId);

}
