package az.blogoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import az.blogoot.domain.Email;
import az.blogoot.domain.Token;
import az.blogoot.domain.User;
import az.blogoot.repository.EmailRepository;
import az.blogoot.service.EmailService;
import az.blogoot.utility.EmailUtilit;

/**
 * EmailServiceimpl
 */
@Service
public class EmailServiceimpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Email generateEmail(Token token) {
        Email email = new Email();
        User user = token.getUser();
        String url = "http://localhost:8080/activate?token=" + token.getToken();

        email.setFrom(from);
        email.setTo(user.getEmail());
        email.setBody(EmailUtilit.body(user.getName(), user.getLastname(), url));
        email.setSubject(EmailUtilit.subject());
        email.setUser(user);
        return email;
    }

    @Override
    public Email saveEmail(Email email) {
        return emailRepository.saveEmail(email);
    }

    @Async
    @Override
    public void sendEmail(Email email) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email.getFrom());
        mailMessage.setTo(email.getTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getBody());

        System.out.println(mailMessage);
        mailSender.send(mailMessage);

    }

    @Override
    public List<Email> getEmailList(int limit) {
        return emailRepository.getEmail(limit);
    }

}