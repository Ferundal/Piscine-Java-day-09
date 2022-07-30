package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.stereotype.Component;

public interface MessageRepository {
    public void save(Message message);
}
