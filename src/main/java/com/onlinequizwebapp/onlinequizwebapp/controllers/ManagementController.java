package com.onlinequizwebapp.onlinequizwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {
    @GetMapping("/admin-management-portal")
    public String getAdminManagement(Model model){
        return "admin-management-portal";
    }
}
