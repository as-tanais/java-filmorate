package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class FilmDbStorage extends BaseRepository<Film> {

    private static final String FIND_ALL_QUERY = "select f.*, m.name mpa_name from films f join mpa m on f.mpa_id = m.id order by f.id";
    private static final String FIND_BY_ID_QUERY = "select f.*, mr.name mpa_name from films f join mpa mr on f.mpa_id = mr.id " +
            "where f.id = ?";
    private static final String INSERT_QUERY = "INSERT INTO FILMS(NAME, DESCRIPTION, RELEASEDATE, DURATION, MPA_ID)"
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String FILM_UPDATE_QUERY = "update films set name = ?, releasedate = ?, description = ?, duration = ? " +
            "where id = ?";

    private static final String FILM_DELETE_QUERY = "DELETE FROM FILMS WHERE id = ?";

    public FilmDbStorage(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper);
    }

    public Film create(Film film) {
        long id = insert(INSERT_QUERY,
                film.getName(),
                film.getDescription(),
                java.sql.Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getMpa().getId()
        );
        film.setId(id);

        return film;
    }


    public Film updateFilm(Film film) {
        if (isFilmExist(film.getId())) {
            update(FILM_UPDATE_QUERY,
                    film.getName(),
                    java.sql.Date.valueOf(film.getReleaseDate()),
                    film.getDescription(),
                    film.getDuration(),
                    film.getId()
            );
            return film;
        }
        throw new NotFoundException("Пользователь с id = " + film.getId() + " не найден");
    }

    public boolean isFilmExist(long id) {
        return findFilmById(id).isPresent();
    }


    public List<Film> getAllFilm() {
        return findMany(FIND_ALL_QUERY, mapper);
    }


    public Optional<Film> findFilmById(long id) {
        return Optional.of(findOne(FIND_BY_ID_QUERY, id).orElseThrow());
    }


    public void deleteFilm(long filmId) {
        update(FILM_DELETE_QUERY, filmId);
    }


}
