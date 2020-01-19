package az.blogoot.service;

import java.util.List;
import java.util.Optional;

import az.blogoot.domain.User;
import az.blogoot.domain.UserRole;

/**
 * UserService
 */
public interface UserService {

    User registerUser(User user);

    List<UserRole> getRoles(long userId);

    Optional<User> getUserByEmail(String email);

    boolean activateUser(String token);

}