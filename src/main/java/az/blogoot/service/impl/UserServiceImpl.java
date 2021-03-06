package az.blogoot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import az.blogoot.domain.Email;
import az.blogoot.domain.Token;
import az.blogoot.domain.User;
import az.blogoot.domain.UserRole;
import az.blogoot.repository.UserRepository;
import az.blogoot.service.EmailService;
import az.blogoot.service.PasswordService;
import az.blogoot.service.TokenService;
import az.blogoot.service.UserService;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public User registerUser(User user) {
        /**
         * 1. password encryption 2. add user to db 3.generate token 4. add to token 5.
         * add token to db 6. generate email
         */

        // 1. password encryption
        String encryptedPassword = passwordService.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        System.out.println(user.getPassword());

        // 2. add user to db
        user = userRepository.addUser(user);

        userRepository.addRole(user.getId(), UserRole.USER.getValue());

        // 3. generate token
        Token token = tokenService.generateToken(user);

        // 4.save token
        tokenService.saveToken(token);

        Email email = emailService.generateEmail(token);

        emailService.saveEmail(email);

        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        user = Optional.of(userRepository.checkUserByEmail(email));
        return user;
    }

    @Override
    public boolean activateUser(String token) {
        return userRepository.activateUser(token);
    }

    @Override
    public List<UserRole> getRoles(long userId) {
        return userRepository.getRoles(userId);
    }

    
}