package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.Contact;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ContactController{
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact-us-management")
    public String getAllContacts(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Contact> listContacts=contactService.getAllContact();
        System.out.println(listContacts);
        model.addAttribute("contacts", listContacts);
        return "contact-management";
    }

    @GetMapping("/view-contact-us/{id}")
    public String getContactById(@PathVariable Integer id, HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        System.out.println(id);
        Contact contact=contactService.getContactById(id).orElse(null);
        System.out.println(contact);
        model.addAttribute("contact", contact);
        return "view-contact";
    }

    @GetMapping("/create-contact")
    public String createNewContact(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        Boolean hasOpenQuiz=(Boolean) session.getAttribute("hasOpenQuiz");
        if (hasOpenQuiz==true){
            Integer categoryId=(Integer) session.getAttribute("categoryId");
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("hasOpenQuiz", hasOpenQuiz);
        }
        model.addAttribute("session", session);
        return "create-contact";
    }

    @PostMapping("/create-contact")
    public String createNewContact(@RequestParam String subject,
                                @RequestParam String email,
                                @RequestParam String message,
                                Model model) {
        contactService.createNewContact(new Contact(0, email, subject, message, null));
        return "redirect:/home";
    }

}
