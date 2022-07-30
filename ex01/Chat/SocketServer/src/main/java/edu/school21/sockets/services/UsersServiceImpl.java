package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    User currentUser = null;
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcTemplate") UsersRepository usersRepository, @Qualifier("getPasswordEncoder") PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void signUp(String login, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        this.currentUser = new User(null, login, encodedPassword);
        usersRepository.save(new User(null, login, encodedPassword));
    }

    @Override
    public void logIn(String login, String password) {
        Optional<User> optionalUser = usersRepository.findByLogin(login);
        if (!optionalUser.isPresent()) {
            throw new WrongUserDataException();
        } else {
            this.currentUser = usersRepository.findByLogin(login).get();
        }
    }

    @Override
    public void sendMessage(String message) {
        if (currentUser == null) {
            throw new RuntimeException("Unknown user can't send messages!");
        }
    }
}
