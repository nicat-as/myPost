package az.blogoot.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import az.blogoot.domain.User;
import az.blogoot.service.AuthenticationFacade;

/**
 * UserSuccessHandler
 */

 @Component
public class UserSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        String page = user.getUserRole().get(0).getPage();
        System.out.println("EmployeeAuthenticationSuccessHandler redirect user to " + page);
        request.getSession().setAttribute("loginTime", LocalDateTime.now());
        response.sendRedirect(request.getContextPath() + page);
    }

    
}