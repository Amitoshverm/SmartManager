package com.smart.smartmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.smartmanager.Services.UserService;




@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // user dashborad page
    @RequestMapping( value ="/dashboard")
    public String userDashboard() {
        System.out.println("loading dashboard");
        return "user/dashboard";
    }
    //profile page
    @RequestMapping(value = "/profile")
    public String userProfile() {
        System.out.println("loading profile");
        return "user/profile";
    }


    

    // user add contact page

    // user view contact page

    // edit contact 

    // user delete 

    // search user
}
