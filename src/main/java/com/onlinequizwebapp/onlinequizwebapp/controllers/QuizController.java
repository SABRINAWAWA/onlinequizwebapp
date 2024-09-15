package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.CategoryService;
import com.onlinequizwebapp.onlinequizwebapp.services.QuestionService;
import com.onlinequizwebapp.onlinequizwebapp.services.QuizService;
import com.onlinequizwebapp.onlinequizwebapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class QuizController{
    private final QuizService quizService;
    private final QuestionService questionService;
    private final CategoryService categoryService;
    private final UserService userService;
    public QuizController(QuizService quizService, QuestionService questionService, CategoryService categoryService, UserService userService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/quiz-result-management/page/{pageNum}")
    public String getAllQuizResults(@PathVariable Integer pageNum, HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        session.setAttribute("savedUserId", 0);
        session.setAttribute("savedCategoryId", 0);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Quiz> quizList=quizService.getAllQuizResults();
        //System.out.println(quizList);
        List<Category> categoryList=quizList.stream().map(q->q.getCategory()).collect(Collectors.toList());
        List<Category> uniqueCategoryList=categoryList.stream().distinct().collect(Collectors.toList());
        //System.out.println(uniqueCategoryList);
        model.addAttribute("categories", uniqueCategoryList);
        List<User> userList=quizList.stream().map(q->q.getQuizTaker()).collect(Collectors.toList());
        List<User> uniqueUserList=userList.stream().distinct().collect(Collectors.toList());
        //System.out.println(uniqueUserList);
        model.addAttribute("users", uniqueUserList);

        selectQuizList(quizList,model,pageNum);
        return "quiz-result-management";
    }

    private void selectQuizList(List<Quiz> quizList, Model model, Integer pageNum){
        Integer numOfPage=(int)Math.ceil((double) quizList.size()/5);
        model.addAttribute("numOfPage", numOfPage);
        List<Quiz> selectedQuizList;
        if (pageNum<numOfPage){
            selectedQuizList= quizList.subList((pageNum - 1)*5, pageNum*5);
        }else{
            selectedQuizList= quizList.subList((pageNum - 1)*5, quizList.size());
        }
        model.addAttribute("quizzes", selectedQuizList);
    }

    @GetMapping("/quiz-result-management/category/{categoryId}")
    public String getAllQuizResultsByCategory(@PathVariable("categoryId") Integer categoryId,
                                              HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        session.setAttribute("savedCategoryId", categoryId);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Quiz> quizList=quizService.getAllQuizByCategoryId(categoryId);
        //System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        List<Category> categoryList=quizList.stream().map(q->q.getCategory()).collect(Collectors.toList());
        List<Category> uniqueCategoryList=categoryList.stream().distinct().collect(Collectors.toList());
        //System.out.println(uniqueCategoryList);
        model.addAttribute("categories", uniqueCategoryList);
        List<User> userList=quizList.stream().map(q->q.getQuizTaker()).collect(Collectors.toList());
        List<User> uniqueUserList=userList.stream().distinct().collect(Collectors.toList());
        //System.out.println(uniqueUserList);
        model.addAttribute("users", uniqueUserList);
        return "quiz-result-management";
    }

    @GetMapping("/quiz-result-management/category/orderBy")
    public String getAllSortedQuizResultsByCategory(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        Integer savedCategoryId=(Integer)session.getAttribute("savedCategoryId");
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Quiz> quizList;
        if (savedCategoryId!=0){
            quizList=quizService.getAllQuizByCategoryId(savedCategoryId);
        }else{
            quizList=quizService.getAllQuizResults();
        }
        Collections.sort(quizList, Comparator.comparing(q -> q.getCategory().getName()));
        //System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        List<Category> categoryList=quizList.stream().map(q->q.getCategory()).collect(Collectors.toList());
        List<Category> uniqueCategoryList=categoryList.stream().distinct().collect(Collectors.toList());
        //System.out.println(uniqueCategoryList);
        model.addAttribute("categories", uniqueCategoryList);
        List<User> userList=quizList.stream().map(q->q.getQuizTaker()).collect(Collectors.toList());
        List<User> uniqueUserList=userList.stream().distinct().collect(Collectors.toList());
        //System.out.println(uniqueUserList);
        model.addAttribute("users", uniqueUserList);
        return "quiz-result-management";
    }

    @GetMapping("/quiz-result-management/user/{userId}")
    public String getAllQuizResultsByUser(@PathVariable("userId") Integer userId,
                                          HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        session.setAttribute("savedUserId", userId);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Quiz> quizList=quizService.getAllQuizResultForUser(userId);
        System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        List<Category> categoryList=quizList.stream().map(q->q.getCategory()).collect(Collectors.toList());
        List<Category> uniqueCategoryList=categoryList.stream().distinct().collect(Collectors.toList());
        System.out.println(uniqueCategoryList);
        model.addAttribute("categories", uniqueCategoryList);
        List<User> userList=quizList.stream().map(q->q.getQuizTaker()).collect(Collectors.toList());
        List<User> uniqueUserList=userList.stream().distinct().collect(Collectors.toList());
        System.out.println(uniqueUserList);
        model.addAttribute("users", uniqueUserList);
        return "quiz-result-management";
    }

    @GetMapping("/quiz-result-management/user/orderBy")
    public String getAllSortedQuizResultsByUser(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        Integer savedUserId=(Integer)session.getAttribute("savedUserId");
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Quiz> quizList;
        if (savedUserId!=0){
            quizList=quizService.getAllQuizResultForUser(savedUserId);
        }else{
            quizList=quizService.getAllQuizResults();
        }
        Collections.sort(quizList, Comparator.comparing(q -> q.getQuizTaker().getFirstName()));
        System.out.println(quizList);
        model.addAttribute("quizzes", quizList);
        List<Category> categoryList=quizList.stream().map(q->q.getCategory()).collect(Collectors.toList());
        List<Category> uniqueCategoryList=categoryList.stream().distinct().collect(Collectors.toList());
        System.out.println(uniqueCategoryList);
        model.addAttribute("categories", uniqueCategoryList);
        List<User> userList=quizList.stream().map(q->q.getQuizTaker()).collect(Collectors.toList());
        List<User> uniqueUserList=userList.stream().distinct().collect(Collectors.toList());
        System.out.println(uniqueUserList);
        model.addAttribute("users", uniqueUserList);
        return "quiz-result-management";
    }

    @GetMapping("/quiz/{quizId}")
    public String getQuizResult(@PathVariable("quizId") Integer id,
                                HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        Boolean hasOpenQuiz=(Boolean) session.getAttribute("hasOpenQuiz");
        if (hasOpenQuiz==true){
            Integer categoryId=(Integer) session.getAttribute("categoryId");
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("hasOpenQuiz", hasOpenQuiz);
        }
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
        Boolean hasOpenQuiz=(Boolean) session.getAttribute("hasOpenQuiz");
        List<Question> questionList=new ArrayList<>();
        if(hasOpenQuiz==false){
            session.setAttribute("hasOpenQuiz", true);
            questionList=questionService.generateQuestionsByCategory(categoryId);
            List<Integer> questionIdList=questionList.stream().map(e->e.getId()).collect(Collectors.toList());
            session.setAttribute("questionIdList", questionIdList);
        }else{
            List<Integer> questionIdList = (List<Integer>) session.getAttribute("questionIdList");
            for(Integer questionId: questionIdList){
                questionList.add(questionService.getQuestionById(questionId));
            }
            model.addAttribute("questions", questionList);
        }
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        System.out.println(questionList);
        String quizName=quizService.generateQuizName(categoryId, user.getId());
        System.out.println(quizName);
        Timestamp quizStartTime=Timestamp.from(Instant.now());
        System.out.println(quizStartTime);
        session.setAttribute("quizStartTime", quizStartTime);
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
        session.setAttribute("hasOpenQuiz", false);
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
        //System.out.println(questionAnswerPair);

        // create quiz and add quiz to the model
        Quiz quiz=quizService.createNewQuiz(user.getId(), categoryId, quizStartTime, quizEndTime, quizName, questionAnswerPair);
        return "redirect:/quiz/"+quiz.getId();
    }
}
