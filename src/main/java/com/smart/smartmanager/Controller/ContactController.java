package com.smart.smartmanager.Controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.smartmanager.Entity.Contact;
import com.smart.smartmanager.Entity.User;
import com.smart.smartmanager.Forms.ContactForm;
import com.smart.smartmanager.Services.ContactService;
import com.smart.smartmanager.Services.UserService;
import com.smart.smartmanager.helper.Helper;
import com.smart.smartmanager.helper.Message;
import com.smart.smartmanager.helper.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private ContactService contactService;

    private UserService userService;

    public ContactController(ContactService contactService, UserService userService){
        this.contactService = contactService;
        this.userService = userService;
    }
    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        // contactForm.setName("Amitosh");
        
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String savedContact(@ModelAttribute @Valid ContactForm contactForm, Authentication authentication, BindingResult bindingResult, HttpSession session) {

        // TODO: validation on add contact is not working 

        //get user by email(here we use authentication to get username & userService to find user)
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(userName);

        if (bindingResult.hasErrors()) {
            return "user/add_contact";
        }

        //first we process our form data
        System.out.println(contactForm);
        //contactform -> contact
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setLinkedIn(contactForm.getLinkedInLink());
        contact.setUser(user);
        contactService.saveContact(contact);

        Message message = Message.builder()
        .content("contact added successfully")
        .type(MessageType.green)
        .build();

        session.setAttribute("message", message);
        
        return "redirect:/user/contacts/add";

        
    }
    
    // view contacts of an user
    @GetMapping()
    public String viewContacts(Model model, Authentication authentication){

        //loading contacts by logged in user
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        List<Contact> contacts = contactService.getByUser(user);
        model.addAttribute("contacts", contacts);

        return "user/contacts";
    }
}
