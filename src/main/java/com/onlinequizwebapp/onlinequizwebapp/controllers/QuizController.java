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

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class QuizController{
    private final QuizService quizService;
    private final QuestionService questionService;
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping("/quiz-result-management")
    public String getAllQuizResults(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Quiz> quizList=quizService.getAllQuizResults();
        System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        return "quiz-result-management";
    }

    @GetMapping("/quiz/{quizId}")
    public String getQuizResult(@PathVariable("quizId") Integer id, HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
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
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Question> questionList=questionService.generateQuestionsByCategory(categoryId);
        List<Integer> questionIdList=questionList.stream().map(e->e.getId()).collect(Collectors.toList());
        System.out.println(questionList);
        String quizName=quizService.generateQuizName(categoryId, user.getId());
        System.out.println(quizName);
        Timestamp quizStartTime=Timestamp.from(Instant.now());
        System.out.println(quizStartTime);
        session.setAttribute("quizStartTime", quizStartTime);
        session.setAttribute("questionIdList", questionIdList);
        session.setAttribute("categoryId", categoryId);
        session.setAttribute("quizName", quizName);
        model.addAttribute("questions", questionList);
        model.addAttribute("quizName", quizName);
        return "take-quiz";
    }

    @PostMapping("/submit-quiz")
    public String submitQuiz(@RequestParam(name = "selectedChoiceId1") Integer selectedChoiceId1,
                             @RequestParam(name = "selectedChoiceId2") Integer selectedChoiceId2,
                             @RequestParam(name = "selectedChoiceId3") Integer selectedChoiceId3,
                             @RequestParam(name = "selectedChoiceId4") Integer selectedChoiceId4,
                             @RequestParam(name = "selectedChoiceId5") Integer selectedChoiceId5,
                             Model model, HttpServletRequest request) {
        // Get user info from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        // Get start time from session
        Timestamp quizStartTime = (Timestamp) session.getAttribute("quizStartTime");
        Timestamp quizEndTime = Timestamp.from(Instant.now());
        session = request.getSession();
        session.setAttribute("quizEndTime", quizEndTime);

        // Get category id from session
        Integer categoryId=(Integer) session.getAttribute("categoryId");

        // Get quiz name form session
        String quizName=(String) session.getAttribute("quizName");

        // Get question list from session and pair question with answer.
        List<Integer> answerIdList = Arrays.asList(selectedChoiceId1, selectedChoiceId2, selectedChoiceId3,
                selectedChoiceId4, selectedChoiceId5);
        List<Integer> questionIdList = (List<Integer>) session.getAttribute("questionIdList");
        HashMap<Integer, Integer> questionAnswerPair = new HashMap<>();
        for (int i = 0; i < questionIdList.size(); i++) {
            questionAnswerPair.put(questionIdList.get(i), answerIdList.get(i));
        }
        System.out.println(questionAnswerPair);

        // create quiz and add quiz to the model
        Quiz quiz=quizService.createNewQuiz(user.getId(), categoryId, quizStartTime, quizEndTime, quizName, questionAnswerPair);
        return "redirect:/quiz/"+quiz.getId();
    }
}
