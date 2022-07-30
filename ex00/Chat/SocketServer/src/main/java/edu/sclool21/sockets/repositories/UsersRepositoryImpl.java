package edu.sclool21.sockets.repositories;

import edu.sclool21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("jdbcTemplate")
public class UsersRepositoryImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM service.users WHERE identifier = ?", (rs, rowNum) -> new User(rs.getLong(1), rs.getString(2)) , new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM service.users",
                (resultSet, rowNum) -> new User(resultSet.getLong(1), resultSet.getString(2)));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO service.users (email) VALUES (?)", entity.getLogin());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE service.users SET email = ? WHERE identifier = ?", entity.getLogin(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM service.users WHERE identifier = ?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = jdbcTemplate.query("SELECT * FROM service.users WHERE email = ?",
                        (resultSet, rowNum) -> new User(resultSet.getLong(1), resultSet.getString(2)),  new Object[]{email})
                .stream().findAny().orElse(null);
        return Optional.of(user);
    }
}
