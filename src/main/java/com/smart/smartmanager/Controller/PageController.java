package com.smart.smartmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("printed hello");
        // model.addAttribute("name", "Amitosh");
        // model.addAttribute("gitHub", "https://github.com/Amitoshverm/HowReactworks");
        return "home";
    }
}
