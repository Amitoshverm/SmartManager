package com.smart.smartmanager.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.smartmanager.Services.UserService;




@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    Logger logger = LoggerFactory.getLogger(UserController.class);
    // user dashborad page
    @RequestMapping( value ="/dashboard")
    public String userDashboard() {
        System.out.println("loading dashboard");
        return "user/dashboard";
    }
    //profile page
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {
        
        return "user/profile";
    }


    

    // user add contact page

    // user view contact page

    // edit contact 

    // user delete 

    // search user
}
