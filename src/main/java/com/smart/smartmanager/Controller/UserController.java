package com.smart.smartmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    // user dashborad page
    @RequestMapping("/dashboard")
    public String userDashboard() {
        System.out.println("loading dashboard");
        return "user/dashboard";
    }
    //profile page
    @RequestMapping("/profile")
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
