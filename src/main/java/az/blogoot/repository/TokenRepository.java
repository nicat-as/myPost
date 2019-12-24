package az.blogoot.repository;

import az.blogoot.domain.Token;

/**
 * TokenRepository
 */
public interface TokenRepository {
    
    Token saveToken(Token token);    
}