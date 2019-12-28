package az.blogoot.service;

import java.util.Optional;

import az.blogoot.domain.User;

/**
 * UserService
 */
public interface UserService {
       
    User registerUser(User user);
    Optional<User> getUserByEmail(String email);
    
}