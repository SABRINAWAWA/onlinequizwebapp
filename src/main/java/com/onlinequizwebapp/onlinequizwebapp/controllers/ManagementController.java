package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController{
    @GetMapping("/admin-management-portal")
    public String getAdminManagement(HttpServletRequest request, Model model){
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        if (user.getAdmin()){
            return "admin-management-portal";
        }else{
            return "user-access-denied";
        }
    }
}
