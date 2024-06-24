package ru.yandex.practicum.filmorate.storage.filmgenres;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genres.GenreRowMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilmGenresDbStorage implements FilmGenres {

    private final JdbcTemplate jdbcTemplate;

    private static final String ADD_FILM_GENRE_QUERY= "INSERT INTO FILM_GENRES VALUES (?, ?)";
    private static final String DELETE_FILM_GENRE_QUERY= "DELETE FROM FILM_GENRES WHERE FILM_ID = ? and GENRE_ID = ?";
    final String sql = "select distinct g.id as id, g.name from film_genres fg left join genres g on " +
            "fg.genre_id = g.id where film_id = ?";


    @Override
    public void addFilmGenre(Long filmId, Long genreId) {
        jdbcTemplate.update(ADD_FILM_GENRE_QUERY, filmId, genreId);
    }

    @Override
    public void deleteFilmGenre(Long filmId, Long genreId) {
        jdbcTemplate.update(DELETE_FILM_GENRE_QUERY, filmId, genreId);
    }

    @Override
    public List<Genre> getAllFilmGenre(Long filmId) {
        return jdbcTemplate.query(sql, new GenreRowMapper(), filmId);
    }
}
