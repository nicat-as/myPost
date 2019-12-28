package az.blogoot.utility;

import az.blogoot.domain.RegistrationForm;
import az.blogoot.domain.User;

/**
 * FormUtilit
 */
public class FormUtilit {

    public static User getUser(RegistrationForm form){
        User user = new User();

        user.setName(form.getName());
        user.setLastname(form.getLastname());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        
        return user;
    }
}