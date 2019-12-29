package az.blogoot.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import az.blogoot.domain.Email;
import az.blogoot.service.EmailService;

/**
 * EmailScheduler
 */
@Component
public class EmailScheduler {

    @Value("${activation.email.size}")
    private int limit;

    @Autowired
    private EmailService emailService;


    @Scheduled(fixedRate = 5000 )
    public void sendEmailNotification(){
        List<Email> emailList = emailService.getEmailList(limit);

        if(!emailList.isEmpty()){
            emailList.forEach(e->emailService.sendEmail(e));
        }
        System.out.println(emailList);

    }
    
}