package com.smart.smartmanager.Controller;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smart.smartmanager.Entity.User;
import com.smart.smartmanager.Services.UserService;
import com.smart.smartmanager.helper.Helper;

@ControllerAdvice
public class RootController {

    private UserService userService;

    public RootController(UserService userService) {
        this.userService = userService;
    }
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    public void addLoggedInUser(Model model, Authentication authentication){
        if (authentication == null) {
            return;
        }
        System.out.println("adding logged in user information to model");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}", username);
        User user = userService.getUserByEmail(username);

        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);
        
    }
}
//har ek method to add krne k liye root controller hai 
