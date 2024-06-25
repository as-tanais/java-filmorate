package ru.yandex.practicum.filmorate.storage.friendship;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserRowMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendshipDbStorage {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;


    public List<User> getAllFriends(Long id) {
        String sql = "SELECT u.id, u.email, u.login, u.name, u.birthday " +
                "FROM FRIENDS AS f " +
                "INNER JOIN users AS u ON u.id = f.friend_id " +
                "WHERE f.user_id = ? " +
                "ORDER BY u.id";
        return jdbcTemplate.query(sql, userRowMapper, id);
    }

    public void removeFriend(long id, long friendId) {
        String sql = "DELETE FROM FRIENDS WHERE user_id = ? and FRIEND_ID = ?";
        jdbcTemplate.update(sql, id, friendId);
    }

    public void addFriend(Long id, Long friendId) {
        String sql = "INSERT INTO friends(user_id, friend_id) VALUES (?,?)";
        jdbcTemplate.update(sql, id, friendId);
    }

    public List<User> getCommonFriends(long id, long otherId) {
        String sql = "SELECT u.id, u.email, u.login, u.name, u.birthday " +
                "FROM friends AS f " +
                "INNER JOIN friends fr on fr.friend_id = f.friend_id " +
                "INNER JOIN users u on u.id = fr.friend_id " +
                "WHERE f.user_id = ? and fr.user_id = ? " +
                "AND f.friend_id <> fr.user_id AND fr.friend_id <> f.user_id";
        return jdbcTemplate.query(sql, userRowMapper, id, otherId);
    }

}
