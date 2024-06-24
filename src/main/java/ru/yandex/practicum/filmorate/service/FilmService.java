package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.filmgenres.FilmGenresDbStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {

    private final FilmDbStorage filmDbStorage;
    private final FilmGenresDbStorage filmGenresDbStorage;


    public List<Film> getAllFilm() {
        return filmDbStorage.getAllFilm();
    }

    public Film findFilmById(Long id) {

        if (filmDbStorage.findFilmById(id).isEmpty()) {
            throw new NotFoundException("Фильм не найден");
        }

        Film film = filmDbStorage.findFilmById(id).get();

        film.setGenres(filmGenresDbStorage.getAllFilmGenre(film.getId()));

        return film;
    }

    public Film create(Film film) {

        Film newFilm;


        if (film.getMpa().getId() >= 1 && film.getMpa().getId() <= 5) {
            final List<Genre> genreList = new ArrayList<>(new HashSet<>(film.getGenres()));
            newFilm = filmDbStorage.create(film);
            for (Genre genre : genreList) {
                if (genre.getId() >= 1 && genre.getId() <= 6) {
                    filmGenresDbStorage.addFilmGenre(film.getId(), genre.getId());
                } else {
                    throw new ConditionsNotMetException("Такого жанра не сущетсвует");
                }
            }
        } else {
            throw new ConditionsNotMetException("MPA не сущетсвует");
        }

        return newFilm;
    }

    public void deleteFilm(Long id) {
        filmDbStorage.deleteFilm(id);
    }

    public Film updateFilm(Film film) {
        return filmDbStorage.updateFilm(film);
    }

}
