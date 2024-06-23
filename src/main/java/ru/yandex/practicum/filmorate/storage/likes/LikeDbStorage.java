package ru.yandex.practicum.filmorate.storage.likes;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmRowMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeDbStorage implements LikeStorage {
    private final JdbcTemplate jdbcTemplate;

    private static final String ADD_LIKE_QUERY = "INSERT INTO LIKES VALUES (?, ?)";
    private static final String DELETE_LIKE_QUERY = "DELETE FROM LIKES WHERE film_id = ? and user_id = ?";
    private static final String GET_MOST_POPULAR_DELETE_LIKE_QUERY = "SELECT " +
            "F.ID, " +
            "F.NAME," +
            "F.DESCRIPTION, " +
            "F.RELEASEDATE, " +
            "F.DURATION, " +
            "F.MPA_ID, " +
            "COUNT(*) as total " +
            "FROM FILMS F " +
            "JOIN LIKES L ON L.FILM_ID = F.ID " +
            "GROUP BY F.ID " +
            "ORDER BY total DESC, F.ID " +
            "LIMIT ?;";

    @Override
    public void addLike(Long filmId, Long userId) {
        jdbcTemplate.update(ADD_LIKE_QUERY, filmId, userId);
    }

    @Override
    public void deleteLike(Long filmId, Long userId) {
        jdbcTemplate.update(DELETE_LIKE_QUERY, filmId, userId);
    }

    @Override
    public List<Film> getPopular(Integer count) {
        return jdbcTemplate.query(GET_MOST_POPULAR_DELETE_LIKE_QUERY, new FilmRowMapper(), count);
    }
}
