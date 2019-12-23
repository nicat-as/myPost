package az.blogoot.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import az.blogoot.service.PasswordService;

/**
 * PasswordServiceImpl
 */
@Service
public class PasswordServiceImpl implements PasswordService{

    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String stored) {
        return BCrypt.checkpw(password, stored);
    }


}