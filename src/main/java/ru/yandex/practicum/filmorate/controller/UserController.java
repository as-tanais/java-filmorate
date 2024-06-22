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
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserStorage userStorage;

    @GetMapping
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable long id) {
        return userService.findById(id);
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
