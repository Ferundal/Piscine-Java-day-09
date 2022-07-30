package edu.sclool21.sockets.repositories;


import edu.sclool21.sockets.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
