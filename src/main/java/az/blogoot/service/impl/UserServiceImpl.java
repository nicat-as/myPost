package az.blogoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import az.blogoot.domain.User;
import az.blogoot.repository.UserRepository;
import az.blogoot.service.PasswordService;
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

    @Transactional
    @Override
    public User registeUser(User user) {
        /**
         * 1. password encryption
         * 2. add user to db 
         * 3.generate token
         * 4. add to token 
         * 5. add token to db
         * 6. generate email
         */

         //1. password encryption
        String encryptedPassword = passwordService.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        
        //2. add user to db
        userRepository.addUser(user);

        return user;
    }

    
}