package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.QuestionService;
import com.onlinequizwebapp.onlinequizwebapp.services.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
public class QuizController extends MainController{
    private final QuizService quizService;
    private final QuestionService questionService;
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping("/quiz-result-management")
    public String getAllQuizResults(HttpServletRequest request, Model model) {
        insertSessionUser(request, model);
        List<Quiz> quizList=quizService.getAllQuizResults();
        System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        return "quiz-result-management";
    }

    @GetMapping("/quiz/{quizId}")
    public String getQuizResult(@PathVariable("quizId") Integer id, HttpServletRequest request, Model model) {
        insertSessionUser(request, model);
        Quiz quiz=quizService.getQuizResult(id);
        System.out.println(quiz);
        model.addAttribute("quiz", quiz);
        return "quiz-result";
    }

    @GetMapping("/quiz/all/user/{userId}")
    public String getQuizResultForUser(@PathVariable("userId") Integer userId, Model model) {
        List<Quiz> quizList=quizService.getAllQuizResultForUser(userId);
        System.out.println(quizList);
        model.addAttribute("quizList", quizList);
        return "quiz-result";
    }

    @GetMapping("/take-quiz/category/{categoryId}")
    public String takeQuiz(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId, Model model) {
        insertSessionUser(request, model);
        List<Question> questionList=questionService.generateQuestionsByCategory(categoryId);
        System.out.println(questionList);
        HttpSession session=request.getSession();
        Timestamp quizStartTime=Timestamp.from(Instant.now());
        System.out.println(quizStartTime);
        session.setAttribute("quizStartTime", quizStartTime);
        model.addAttribute("questions", questionList);
        return "take-quiz";
    }

    @PostMapping("/submit-quiz")
    @ResponseBody
    public String submitQuiz(@RequestParam(name = "selectedChoiceId1") Integer selectedChoiceId1,
                             @RequestParam(name = "selectedChoiceId2") Integer selectedChoiceId2,
                             @RequestParam(name = "selectedChoiceId3") Integer selectedChoiceId3,
                             @RequestParam(name = "selectedChoiceId4") Integer selectedChoiceId4,
                             @RequestParam(name = "selectedChoiceId5") Integer selectedChoiceId5,
                             Model model, HttpServletRequest request){
        Timestamp quizEndTime=Timestamp.from(Instant.now());
        HttpSession session=request.getSession();
        session.setAttribute("quizEndTime", quizEndTime);

        return "quiz-result";
    }
}
