package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class FilmDbStorage extends BaseRepository<Film> {


    private static final String FIND_ALL_QUERY = "SELECT * FROM FILMS";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM films WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO FILMS(NAME, DESCRIPTION, RELEASEDATE, DURATION, MPA_ID)"
            + "VALUES (?, ?, ?, ?, ?)";

    public FilmDbStorage(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper);
    }

    public Film create(Film film) {
        long id = insert(INSERT_QUERY,
                film.getName(),
                film.getDescription(),
                java.sql.Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getMpa_id()
        );
        film.setId(id);
        return film;
    }


    public Film update(Film film) {
        return null;
    }


    public List<Film> getAllFilm() {
        return findMany(FIND_ALL_QUERY, mapper);
    }


    public Optional<Film> findFilmById(long id) {
        return findOne(FIND_BY_ID_QUERY, id);
    }


    public void deleteFilm(long filmId) {

    }
}
