package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserStorage userStorage;

    @GetMapping
    public Collection<User> getUsers() {
        return userStorage.findAll();
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userStorage.update(user);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userStorage.create(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable long id) {
        return userStorage.findUserById(id);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable long id) {
        userStorage.deleteUser(id);
    }

    @PutMapping(value = "/{id}/friends/{friendId}")
    public void addFriend(@NotNull @PathVariable Long id, @NotNull @PathVariable Long friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping(value = "/{userId}/friends/{friendId}")
    public void removeFromFriends(@NotNull @PathVariable Long userId, @NotNull @PathVariable Long friendId) {
        userService.deleteFriend(userId, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getAllFriends(@NotNull @PathVariable Long id) {
        return userService.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<User> getMutualFriends(@NotNull @PathVariable Long id, @NotNull @PathVariable Long otherId) {
        return userService.getCommonFriends(id, otherId);
    }

}
