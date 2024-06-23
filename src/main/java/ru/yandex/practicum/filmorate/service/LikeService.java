package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikeStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeStorage likeStorage;
    private final FilmDbStorage filmDbStorage;
    private final UserDbStorage userDbStorage;

    public void addLike(Long filmId, Long userId) {
        if (filmDbStorage.findFilmById(filmId).isEmpty() || userDbStorage.findById(userId).isEmpty()) {
            throw new NotFoundException("Фильм или Пользователь не найден");
        }
        likeStorage.addLike(filmId, userId);
    }

    public void deleteLike(Long filmId, Long userId) {
        if (filmDbStorage.findFilmById(filmId).isEmpty() || userDbStorage.findById(userId).isEmpty()) {
            throw new NotFoundException("Фильм или Пользователь не найден");
        }
        likeStorage.deleteLike(filmId, userId);
    }

    public List<Film> getPopular (Integer count) {
        return likeStorage.getPopular(count);
    }

}
