package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("jdbcTemplate")
public class UsersRepositoryImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM chat.users WHERE identifier = ?", (resultSet, rowNum) -> new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3)) , new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat.users",
                (resultSet, rowNum) -> new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3)));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO chat.users (login, password) VALUES (? , ?)", entity.getLogin(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE chat.users SET login = ?, password = ? WHERE identifier = ?", entity.getLogin(), entity.getPassword(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM chat.users WHERE identifier = ?", id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = jdbcTemplate.query("SELECT * FROM chat.users WHERE login = ?",
                        (resultSet, rowNum) -> new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3)),  new Object[]{login})
                .stream().findAny().orElse(null);
        return Optional.of(user);
    }
}
