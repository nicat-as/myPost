package az.blogoot.repository;

import java.util.List;

import az.blogoot.domain.Email;

/**
 * EmailRepository
 */
public interface EmailRepository {

    Email saveEmail(Email email);

    List<Email> getEmail (int limit);
    
}