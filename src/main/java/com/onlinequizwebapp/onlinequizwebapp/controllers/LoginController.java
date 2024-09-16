package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController{
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // redirect to /quiz if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            User user=(User) session.getAttribute("user");
            if(user.getAdmin()==true&& user.getActive()==true){
                return "redirect:/admin-management-portal";
            }else{
                if (user.getActive()==false){
                    return "redirect:/suspendedUserError";
                }else{
                    return "redirect:/home";
                }
            }
        }
        session.setAttribute("hasOpenQuiz", false);
        model.addAttribute("session", session);
        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = loginService.validateLogin(username, password);

        if(possibleUser.isPresent()) {
            if (!possibleUser.get().getActive()){
                return "redirect:/suspendedUserError";
            }else{
                // false means get current session;
                // if current session doesn't exist return null;
                // Therefore often follows an if statement
                HttpSession oldSession = request.getSession(false);
                // invalidate old session if it exists
                if (oldSession != null) oldSession.invalidate();

                // get current session if no current session, generate a new session
                HttpSession newSession = request.getSession(true);

                // store user details in session
                newSession.setAttribute("hasOpenQuiz", false);
                newSession.setAttribute("user", possibleUser.get());
                if (possibleUser.get().getAdmin()&&possibleUser.get().getActive()){
                    return "redirect:/admin-management-portal";
                }else {
                    return "redirect:/home";
                }
            }
        } else { // if user details are invalid
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }

    @GetMapping("/suspendedUserError")
    public String error(Model model){
        return "suspend-user-error";
    }

}
