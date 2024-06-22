package ru.yandex.practicum.filmorate.storage.film;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.BaseRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Component
public class FilmDbStorage implements FilmStorage{

    private final FilmRowMapper filmRowMapper;
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_QUERY =  "SELECT * FROM FILMS AS f LEFT JOIN MPA AS m ON  f.MPA_ID = m.id;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM films WHERE id = ?";


    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public Collection<Film> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, filmRowMapper);
    }

    @Override
    public Film findFilmById(long id) {
        return null;
    }

    @Override
    public void deleteFilm(long filmId) {

    }
}
