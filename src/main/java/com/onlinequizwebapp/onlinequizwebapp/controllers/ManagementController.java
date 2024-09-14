package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController extends MainController{
    @GetMapping("/admin-management-portal")
    public String getAdminManagement(HttpServletRequest request, Model model){
        insertSessionUser(request, model);
        return "admin-management-portal";
    }
}
