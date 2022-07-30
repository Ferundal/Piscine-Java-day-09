package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
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
        usersRepository.save(new User(null, login, encodedPassword));
    }
}
