package az.blogoot.repository;

import az.blogoot.domain.User;

/**
 * UserRepository
 */
public interface UserRepository {

    User addUser(User user);

    User checkUserByEmail(String email);

    boolean activateUser(String token);
}