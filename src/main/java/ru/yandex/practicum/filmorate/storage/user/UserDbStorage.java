package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDbStorage extends BaseRepository<User> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO users(name, email, login, birthday)" +
            "VALUES (?, ?, ?, ?)";
    private static final String USERS_UPDATE_QUERY = "UPDATE users SET name = ?, email = ?, login = ?, birthday = ? WHERE id = ?";

    public UserDbStorage(JdbcTemplate jdbc, RowMapper<User> mapper) {
        super(jdbc, mapper);
    }

    public List<User> findAll() {
        return findMany(FIND_ALL_QUERY, mapper);
    }

    public Optional<User> findById(long userId) {
        return findOne(FIND_BY_ID_QUERY, userId);
    }

    public User create(User user) {
        long id = insert(
                INSERT_QUERY,
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                java.sql.Date.valueOf(user.getBirthday())
        );
        user.setId(id);
        return user;
    }

    public User update(User user) {
        if (isUserExist(user.getId())) {
            update(USERS_UPDATE_QUERY,
                    user.getEmail(),
                    user.getLogin(),
                    user.getName(),
                    java.sql.Date.valueOf(user.getBirthday()),
                    user.getId()
            );
            return user;
        }
        throw new NotFoundException("Пользователь с id = " + user.getId() + " не найден");
    }

    public Optional<User> findByEmail(String email) {
        return findOne(FIND_BY_EMAIL_QUERY, email);
    }



    public boolean isUserExist(long id) {
        return findById(id).isPresent();
    }
}
