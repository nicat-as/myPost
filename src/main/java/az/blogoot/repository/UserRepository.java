package az.blogoot.repository;

import java.util.Optional;

import az.blogoot.domain.User;

/**
 * UserRepository
 */
public interface UserRepository {

    User addUser(User user);

    User checkUserByEmail(String email);
}