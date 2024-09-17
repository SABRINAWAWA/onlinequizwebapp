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
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);

        retrieveDropdownValues(model);
        // Calculate offset and retrieve quiz list
        Integer offset=calculateOffset(model,pageNum, false, false, 0, 0);
        List<Quiz> quizList=quizService.getAllQuizResultsPagination(offset);
        model.addAttribute("quizzes", quizList);
        model.addAttribute("urlPrefix", "/quiz-result-management/page/");

        if (user.getAdmin()){
            return "quiz-result-management";
        }else{
            return "user-access-denied";
        }
    }

    private Integer calculateOffset(Model model, Integer pageNum, Boolean getQuizBasedOnCategory, Boolean getQuizBasedOnUser, Integer categoryId, Integer userId){
        Integer numOfQuiz=0;
        if (!getQuizBasedOnCategory&&!getQuizBasedOnUser){
            numOfQuiz=quizService.getNumberOfQuizzes();
        }
        else if(!getQuizBasedOnCategory&&getQuizBasedOnUser){
            numOfQuiz=quizService.getNumberOfQuizzesBasedOnUser(userId);
        }
        else if(getQuizBasedOnCategory&&!getQuizBasedOnUser){
            numOfQuiz=quizService.getNumberOfQuizzesBasedOnCategory(categoryId);
        }
        Integer numOfPage=(int)Math.ceil((double) numOfQuiz/5);
        model.addAttribute("numOfPage", numOfPage);
        Integer offset=(pageNum-1)*5;
        return offset;
    }

    private void retrieveDropdownValues(Model model){
        // Retrieving categories in the quiz
        List<Category> categoryList=categoryService.getAllCategoriesInQuiz();
        model.addAttribute("categories", categoryList);
        // Retrieving users in the quiz
        List<User> userList=userService.getQuizTakers();
        model.addAttribute("users", userList);

    }

    @GetMapping("/quiz-result-management/category/{categoryId}/page/{pageNum}")
    public String getAllQuizResultsByCategory(@PathVariable("categoryId") Integer categoryId,
                                              @PathVariable("pageNum") Integer pageNum,
                                              HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        retrieveDropdownValues(model);
        // Calculate offset and retrieve quiz list
        Integer offset=calculateOffset(model,pageNum, true, false, categoryId, 0);
        List<Quiz> quizList=quizService.getAllQuizByCategoryId(categoryId, offset);
        model.addAttribute("quizzes", quizList);
        model.addAttribute("urlPrefix", "/quiz-result-management/category/"+categoryId+"/page/");
        if (user.getAdmin()){
            return "quiz-result-management";
        }else{
            return "user-access-denied";
        }
    }

    @GetMapping("/quiz-result-management/category/orderBy/page/{pageNum}")
    public String getAllSortedQuizResultsByCategory(@PathVariable("pageNum") Integer pageNum,
                                                    HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        retrieveDropdownValues(model);
        Integer offset=calculateOffset(model,pageNum, false, false, 0, 0);
        List<Quiz>  quizList=quizService.getAllQuizResultPaginationOrderByCategory(offset);
        model.addAttribute("quizzes", quizList);
        model.addAttribute("urlPrefix", "/quiz-result-management/category/orderBy/page/");
        if (user.getAdmin()){
            return "quiz-result-management";
        }else{
            return "user-access-denied";
        }
    }

    @GetMapping("/quiz-result-management/user/{userId}/page/{pageNum}")
    public String getAllQuizResultsByUser(@PathVariable("userId") Integer userId,
                                          @PathVariable("pageNum") Integer pageNum,
                                          HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        Integer offset=calculateOffset(model,pageNum, false,true, 0, userId);
        List<Quiz> quizList=quizService.getQuizByUserIdPagination(userId, offset);
        model.addAttribute("quizzes", quizList);
        model.addAttribute("urlPrefix", "/quiz-result-management/user/"+userId+"/page/");
        retrieveDropdownValues(model);
        if (user.getAdmin()){
            return "quiz-result-management";
        }else{
            return "user-access-denied";
        }
    }

    @GetMapping("/quiz-result-management/user/orderBy/page/{pageNum}")
    public String getAllSortedQuizResultsByUser(@PathVariable("pageNum") Integer pageNum,
                                                HttpServletRequest request, Model model) {
        HttpSession session= request.getSession(false);
        model.addAttribute("session", session);
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        Integer offset= calculateOffset(model, pageNum, false, false, 0, 0);
        List<Quiz> quizList = quizService.getAllQuizSortedByUserPagination(offset);
        model.addAttribute("quizzes", quizList);
        model.addAttribute("urlPrefix", "/quiz-result-management/user/orderBy/page/");
        retrieveDropdownValues(model);
        if (user.getAdmin()){
            return "quiz-result-management";
        }else{
            return "user-access-denied";
        }
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
        String quizName="";
        User user=(User) session.getAttribute("user");
        model.addAttribute("user", user);
        if(!hasOpenQuiz){
            session.setAttribute("hasOpenQuiz", true);
            questionList=questionService.generateQuestionsByCategory(categoryId);
            List<Integer> questionIdList=questionList.stream().map(e->e.getId()).collect(Collectors.toList());
            session.setAttribute("questionIdList", questionIdList);
            quizName=quizService.generateQuizName(categoryId, user.getId());
            //System.out.println(quizName);
        }else if (hasOpenQuiz){
            List<Integer> questionIdList = (List<Integer>) session.getAttribute("questionIdList");
            for(Integer questionId: questionIdList){
                questionList.add(questionService.getQuestionById(questionId));
            }
            quizName=(String) session.getAttribute("quizName");
        }
        Timestamp quizStartTime=Timestamp.from(Instant.now());
        System.out.println(quizStartTime);
        session.setAttribute("quizStartTime", quizStartTime);
        session.setAttribute("categoryId", categoryId);
        session.setAttribute("quizName", quizName);
        model.addAttribute("session", session);
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
