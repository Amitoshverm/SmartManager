package com.smart.smartmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
        model.addAttribute("login", false);
        System.out.println("About page loading");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading");
        return "services";
    }
     @GetMapping("/login")
    public String login() {
        return "login";
    }
     @GetMapping("/register")
    public String register() {
        return "register";
    }
    // Contact Page
     @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
