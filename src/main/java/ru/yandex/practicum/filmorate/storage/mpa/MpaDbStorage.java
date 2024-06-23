package ru.yandex.practicum.filmorate.storage.mpa;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class MpaDbStorage extends BaseRepository<Mpa> implements MpaStorage {

    private static final String FIND_ALL_QUERY = "SELECT * FROM MPA";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM MPA WHERE id = ?";

    public MpaDbStorage(JdbcTemplate jdbc, RowMapper<Mpa> mapper) {
        super(jdbc, mapper);
    }

    @Override
    public Optional<Mpa> getById(Long id) {
        return findOne(FIND_BY_ID_QUERY, id);
    }

    @Override
    public List<Mpa> getAll() {
        return findMany(FIND_ALL_QUERY, mapper);
    }

    @Override
    public Mpa getFromFilm(Long filmId) {
        return null;
    }
}
