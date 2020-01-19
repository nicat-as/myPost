package az.blogoot.repository;

import java.util.List;

import az.blogoot.domain.User;
import az.blogoot.domain.UserRole;

/**
 * UserRepository
 */
public interface UserRepository {

    User addUser(User user);

    void addRole(long id, int role);

    List<UserRole> getRoles(long userId);

    User checkUserByEmail(String email);

    boolean activateUser(String token);
}