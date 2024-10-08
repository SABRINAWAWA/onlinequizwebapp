package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.CategoryService;
import com.onlinequizwebapp.onlinequizwebapp.services.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final QuizService quizService;
    private final CategoryService categoryService;
    public HomeController(QuizService quizService, CategoryService categoryService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String initialization(HttpServletRequest request, Model model) {
        User user=(User) request.getSession().getAttribute("user");
        List<Quiz> quizList=quizService.getAllQuizResultForUser(user.getId());
        List<Category> categoryList=categoryService.getAllCategory();
        HttpSession session= request.getSession(false);
        Boolean hasOpenQuiz=(Boolean) session.getAttribute("hasOpenQuiz");
        if (hasOpenQuiz==true){
            Integer categoryId=(Integer) session.getAttribute("categoryId");
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("hasOpenQuiz", hasOpenQuiz);
        }
        model.addAttribute("session", session);
        model.addAttribute("quizzes", quizList);
        model.addAttribute("categories", categoryList);
        return "home";
    }
}
