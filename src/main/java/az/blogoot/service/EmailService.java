package az.blogoot.service;

import java.util.List;

import az.blogoot.domain.Email;
import az.blogoot.domain.Token;

/**
 * EmailService
 */
public interface EmailService {
    Email generateEmail(Token token);

    Email saveEmail(Email email);

    void sendEmail(Email email);

    List<Email> getEmailList(int limit);
}