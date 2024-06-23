package ru.yandex.practicum.filmorate.storage.genres;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreDbStorage extends BaseRepository<Genre> implements GenreStorage {

    private static final String FIND_ALL_QUERY = "SELECT * FROM GENRES";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM GENRES WHERE id = ?";

    public GenreDbStorage(JdbcTemplate jdbc, RowMapper<Genre> mapper) {
        super(jdbc, mapper);
    }

    @Override
    public Optional<Genre> getById(Long id) {
        return findOne(FIND_BY_ID_QUERY, id);
    }

    @Override
    public List<Genre> getAll() {
        return findMany(FIND_ALL_QUERY, mapper);
    }

}
