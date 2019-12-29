package az.blogoot.service;

import az.blogoot.domain.Token;
import az.blogoot.domain.User;

/**
 * TokenService
 */
public interface TokenService {

    Token generateToken(User user);
    Token saveToken (Token token);
    
}