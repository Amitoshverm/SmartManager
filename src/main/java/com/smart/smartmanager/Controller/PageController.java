package com.smart.smartmanager.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.smartmanager.Entity.User;
import com.smart.smartmanager.Services.UserService;
import com.smart.smartmanager.helper.Message;
import com.smart.smartmanager.helper.MessageType;
import com.smart.smartmanager.helper.UserForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    private UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("printed hello");
        model.addAttribute("name", "Amitosh");
        model.addAttribute("gitHub", "https://github.com/Amitoshverm/HowReactworks");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading");
        return "services";
    }

    //login page 
     @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    //registration page
     @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }
    // Contact Page
     @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    //processing register 
    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
        System.out.println("Registration in process");
        System.out.println(userForm);

        if (bindingResult.hasErrors()) {
            return "register";
        }
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .phoneNumber(userForm.getPhoneNumber())
        // .about(userForm.getAbout())
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePicture("");
        User saved = userService.saveUser(user);

        System.out.println("save user ");
        // fetch the form data 
        // and validate form data 
        // save to db
        // message = registration sucessfull
        //add message
        Message message = Message.builder()
        .content("Registration Successfull")
        .type(MessageType.green)
        .build();

        session.setAttribute("message", message);
        return "redirect:/register";
    }
}
