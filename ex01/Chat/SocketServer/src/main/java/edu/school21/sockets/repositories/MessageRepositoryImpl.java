package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("jdbcTemplate")
public class MessageRepositoryImpl implements  MessageRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Message message) {
        jdbcTemplate.update("INSERT INTO chat.messages (sender, message, time) VALUES (? , ?, ?)", message.getSender().getIdentifier(), message.getMessage(), message.getSendingTime());
    }
}
