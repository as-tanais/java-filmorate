package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {


    private final FilmStorage filmStorage;

    public Film create(Film film){
        return filmStorage.create(film);
    }

    public Collection<Film> findAll() {
        return filmStorage.findAll();
    }

    public Film update(Film film){
        return filmStorage.update(film);
    }

    public void addLike(long filmId, long userId) {
        filmStorage.addLike(filmId, userId);
        log.info("Пользователь с id {} поставил фильму с id {} лайк", userId, filmId);
    }

    public void deleteLike(long filmId, long userId) {
        filmStorage.deleteLike(filmId, userId);
        log.info("Лайк пользователя с id {} фильму с id {} удалён", userId, filmId);
    }

    public List<Film> getMostPopularFilms(int count) {
        return filmStorage.findAll().stream()
                .sorted((f1, f2) -> f2.getLikes().size() - f1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }
}
