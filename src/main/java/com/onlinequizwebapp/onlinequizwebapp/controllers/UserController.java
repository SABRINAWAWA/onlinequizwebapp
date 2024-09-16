package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-management/page/{pageNum}")
    public String getAllUsers(@PathVariable Integer pageNum, HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<User> listUsers=userService.getAllUsers();
        //System.out.println(listUsers);
        Integer numOfPage=(int)Math.ceil((double) listUsers.size()/5);
        model.addAttribute("numOfPage", numOfPage);
        List<User> selectedUserList;
        if (pageNum<numOfPage){
            selectedUserList= listUsers.subList((pageNum - 1)*5, pageNum*5);
        }else{
            selectedUserList= listUsers.subList((pageNum - 1)*5, listUsers.size());
        }
        model.addAttribute("users", selectedUserList);
        return "user-management";
    }

    @PostMapping("/user-management/user/active/{id}")
    public String activeUserById(@PathVariable Integer id, Model model) {
        User user=userService.activeUserById(id);
        System.out.println(user);
        return "redirect:/user-management/page/1";
    }

    @PostMapping("/user-management/user/suspend/{id}")
    public String suspendUserById(@PathVariable Integer id, Model model) {
        User user=userService.suspendedUserById(id);
        System.out.println(user);
        return "redirect:/user-management/page/1";
    }

    @GetMapping("/user-register")
    public String createNewUser(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(true);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("hasOpenQuiz", false);
        return "user-register";
    }

    @PostMapping("/user-register")
    public String createNewUser(@RequestParam String firstName,@RequestParam String lastName,
                                @RequestParam String email,@RequestParam String password,
                                  Model model) {
        userService.createNewUser(new User(0, firstName,  lastName,  email,  password, true, false));
        return "redirect:/user-management";
    }
}
