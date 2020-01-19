package az.blogoot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import az.blogoot.domain.User;
import az.blogoot.security.UserPrincipal;
import az.blogoot.service.AuthenticationFacade;


/**
 * UserController
 */

 @Controller
 @RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationFacade authentication;
    
    @GetMapping(value="")
    public ModelAndView getDash() {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.setViewName("user/index");
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getAuthentication().getPrincipal();
       
        Map<String, String> user = new HashMap<>();
       
        user.put("id", String.valueOf(userPrincipal.getUser().getId()));
        user.put("name", userPrincipal.getUser().getName());
        user.put("lastname", userPrincipal.getUser().getLastname());
        user.put("email", userPrincipal.getUser().getEmail());


        modelAndView.addObject("user",user);
        
        return modelAndView;
    }
    
}