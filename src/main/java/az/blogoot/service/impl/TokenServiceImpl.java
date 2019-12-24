package az.blogoot.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import az.blogoot.domain.Token;
import az.blogoot.domain.User;
import az.blogoot.repository.TokenRepository;
import az.blogoot.service.TokenService;
import az.blogoot.util.TokenGenerator;

/**
 * TokenServiceImpl
 */

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${user.token.expiration.day}")
    private long days;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token generateToken(User user) {
        Token token = new Token();
        token.setToken(TokenGenerator.generateToken());
        token.setUser(user);
        token.setGenerationDate(LocalDateTime.now());
        token.setExpirationDate(LocalDateTime.now().plusDays(days));
        return token;

    }

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.saveToken(token);
    }

}