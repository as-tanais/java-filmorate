package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {

    Film create(Film film);

    Film update(Film film);

    Collection<Film> findAll();

    Film findFilmById(long id);

    void addLike(long filmId, long userId);

    void deleteLike(long filmId, long userId);

    //Film remove(Film film);
}
