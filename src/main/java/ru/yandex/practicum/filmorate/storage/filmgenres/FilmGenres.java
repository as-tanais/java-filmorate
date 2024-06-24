package ru.yandex.practicum.filmorate.storage.filmgenres;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

public interface FilmGenres {

    void addFilmGenre(Long filmId, Long genreId);

    void deleteFilmGenre(Long filmId, Long genreId);

    List<Genre> getAllFilmGenre(Long filmId);

}
