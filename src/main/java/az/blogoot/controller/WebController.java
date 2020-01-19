package az.blogoot.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import az.blogoot.domain.RegistrationForm;
import az.blogoot.domain.User;
import az.blogoot.domain.UserStatus;
import az.blogoot.service.PasswordService;
import az.blogoot.service.UserService;
import az.blogoot.utility.FormUtilit;
import az.blogoot.validator.UserRegistrationValidator;

/**
 * WebController
 */

@Controller
@RequestMapping("")
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        Object target = binder.getTarget();
        if (target != null) {
            if (target.getClass().equals(RegistrationForm.class)) {
                binder.setValidator(userRegistrationValidator);
            }
        }
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        RegistrationForm registrationForm = new RegistrationForm();
        modelAndView.addObject("registrationForm", registrationForm);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("registrationForm") @Validated RegistrationForm registrationForm,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        if (bindingResult.hasErrors()) {
            System.out.println("errors : " + bindingResult);

        } else {
            try {
                System.out.println("User reg form " + registrationForm);
                userService.registerUser(FormUtilit.getUser(registrationForm));
                modelAndView.setViewName("register_result");
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /*
    @PostMapping("/login")
    public ModelAndView postLogin(@RequestParam("email") String email, @RequestParam("password") String password,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        Optional<User> user = userService.getUserByEmail(email);
        System.out.println("User finded: " + user.get());
        String message = "";

        if (user.isPresent()) {

            if (passwordService.checkPassword(password, user.get().getPassword())) {
                if (user.get().getUserStatus() == UserStatus.ACTIVE) {
                    modelAndView.setViewName("index");
                    request.getSession().setAttribute("user", user.get());
                } else {
                    message = "User status is " + user.get().getUserStatus().toString().toLowerCase();
                }
            } else {
                message = "Password is not correct!";
            }

        } else {
            message = "There is not such a user";
        }

        if (!message.isEmpty()) {
            modelAndView.addObject("error", message);
        }

        return modelAndView;

    }
    */

    /*
    @GetMapping(value = "/logout")
    public String getLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }
    */
    @GetMapping("/activate")
    public ModelAndView activate(@RequestParam("token") String token) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/resend");

        boolean isActive = userService.activateUser(token);
        if (isActive) {
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    @GetMapping("/resend")
    public ModelAndView getResend() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resend");
        return modelAndView;
    }

  /*  @PostMapping("/resend")
    public ModelAndView postResend(@RequestParam("email") String email){
        
    }*/

}