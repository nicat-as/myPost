package az.blogoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import az.blogoot.domain.User;
import az.blogoot.service.UserService;

/**
 * WebController
 */

@Controller
@RequestMapping("")
public class WebController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        if (bindingResult.hasErrors()) {
            System.out.println("errors : " + bindingResult);
        }else{
            try {
                System.out.println("User reg form " + user);
                userService.registeUser(user);
                modelAndView.setViewName("register");
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return modelAndView;
    }

}