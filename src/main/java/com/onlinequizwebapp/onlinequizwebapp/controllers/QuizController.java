package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.services.QuestionService;
import com.onlinequizwebapp.onlinequizwebapp.services.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final QuestionService questionService;
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping("/quiz-management")
    public String getAllQuizResults(Model model) {
        List<Quiz> quizList=quizService.getAllQuizResults();
        System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        return "quiz-result-management";
    }

    @GetMapping("/{quizId}")
    public String getQuizResult(@PathVariable("quizId") Integer id, Model model) {
        Quiz quiz=quizService.getQuizResult(id);
        System.out.println(quiz);
        model.addAttribute("quizResult", quiz);
        return "quiz-result";
    }

    @GetMapping("/all/user/{userId}")
    public String getQuizResultForUser(@PathVariable("userId") Integer userId, Model model) {
        List<Quiz> quizList=quizService.getAllQuizResultForUser(userId);
        System.out.println(quizList);
        model.addAttribute("quizList", quizList);
        return "quiz-result";
    }

    @GetMapping("/take-quiz/category/{categoryId}")
    public String takeQuiz(@PathVariable("categoryId") Integer categoryId, Model model) {
        List<Question> questionList=questionService.generateQuestionsByCategory(categoryId);
        model.addAttribute("questions", questionList);
        return "take-quiz";
    }

    @PostMapping("/submit-quiz")
    @ResponseBody
    public String submitQuiz(Quiz quiz, Model model){
        quizService.createNewQuiz(quiz);
        return "quiz-result";
    }
}
