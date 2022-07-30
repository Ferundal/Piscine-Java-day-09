package edu.school21.sockets.services;


import org.springframework.stereotype.Component;

@Component
public interface UsersService {
    void signUp(String login, String password);
    void logIn(String login, String password);
    void sendMessage(String message);
}
