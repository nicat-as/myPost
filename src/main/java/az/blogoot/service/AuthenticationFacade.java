package az.blogoot.service;

import org.springframework.security.core.Authentication;

/**
 * AuthenticationFacade
 */
public interface AuthenticationFacade {
    
    Authentication getAuthentication();
}