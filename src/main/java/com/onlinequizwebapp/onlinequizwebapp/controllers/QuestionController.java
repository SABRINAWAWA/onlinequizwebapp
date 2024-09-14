package com.onlinequizwebapp.onlinequizwebapp.controllers;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import com.onlinequizwebapp.onlinequizwebapp.services.CategoryService;
import com.onlinequizwebapp.onlinequizwebapp.services.QuestionService;
import com.onlinequizwebapp.onlinequizwebapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/questions-management")
public class QuestionController extends MainController{
    private final QuestionService questionService;
    private final CategoryService categoryService;

    public QuestionController(QuestionService questionService, CategoryService categoryService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAllQuestions(HttpServletRequest request, Model model) {
        insertSessionUser(request, model);
        List<Question> questions=questionService.getAllQuestions();
        System.out.println(questions);
        model.addAttribute("questions", questions);
        return "questions-management";
    }

    @PostMapping("/question/active/{id}")
    public String activeQuestionById(@PathVariable Integer id, Model model) {
        Question question=questionService.activeQuestionById(id);
        System.out.println(question);
        return "redirect:/questions-management";
    }

    @PostMapping("/question/suspend/{id}")
    public String suspendUserById(@PathVariable Integer id, Model model) {
        Question question=questionService.suspendedQuestionById(id);
        System.out.println(question);
        return "redirect:/questions-management";
    }

    @GetMapping("/question/new")
    public String createNewQuestion(Model model) {
        List<Category> categoryList=categoryService.getAllCategory();
        System.out.println(categoryList);
        model.addAttribute("categories", categoryList);
        return "create-new-question";
    }

    @PostMapping("/question/new")
    public String createNewQuestion(@RequestParam Integer categoryId, @RequestParam String description,
                                @RequestParam String choiceDescription1,@RequestParam boolean choiceIsCorrect1,
                                @RequestParam String choiceDescription2,@RequestParam boolean choiceIsCorrect2,
                                @RequestParam String choiceDescription3,@RequestParam boolean choiceIsCorrect3,
                                @RequestParam String choiceDescription4,@RequestParam boolean choiceIsCorrect4,
                                  Model model) {
        Question newQuestion=new Question();
        newQuestion.setDescription(description);
        newQuestion.setCategory(new Category(categoryId, null));
        List<Choice> choices=new ArrayList<>();
        choices.add(new Choice(0, choiceDescription1,choiceIsCorrect1, 0));
        choices.add(new Choice(0, choiceDescription2,choiceIsCorrect2, 0));
        choices.add(new Choice(0, choiceDescription3,choiceIsCorrect3, 0));
        choices.add(new Choice(0, choiceDescription4,choiceIsCorrect4, 0));
        newQuestion.setChoices(choices);
        questionService.createNewQuestion(newQuestion);
        return "redirect:/questions-management";
    }

    @GetMapping("/question/edit/{id}")
    public String updateQuestion(@PathVariable Integer id, Model model){
        Question question=questionService.getQuestionById(id);
        System.out.println(question);
        List<Category> categoryList=categoryService.getAllCategory();
        System.out.println(categoryList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("question", question);
        return "edit-question";
    }

    @PostMapping("/question/edit/{id}")
    public String updateQuestion(@PathVariable Integer id,
                                 @RequestParam Integer categoryId, @RequestParam String description,
                                 @RequestParam Integer choiceId1,@RequestParam Integer choiceId2,
                                 @RequestParam Integer choiceId3,@RequestParam Integer choiceId4,
                                @RequestParam String choiceDescription1,@RequestParam boolean choiceIsCorrect1,
                                @RequestParam String choiceDescription2,@RequestParam boolean choiceIsCorrect2,
                                @RequestParam String choiceDescription3,@RequestParam boolean choiceIsCorrect3,
                                @RequestParam String choiceDescription4,@RequestParam boolean choiceIsCorrect4,
                                Model model) {
        Question question=questionService.getQuestionById(id);
        question.setDescription(description);
        question.setCategory(new Category(categoryId, null));
        List<Choice> choices=question.getChoices();
        updateChoiceInfo(choices, choiceId1, choiceDescription1, choiceIsCorrect1);
        updateChoiceInfo(choices, choiceId2, choiceDescription2, choiceIsCorrect2);
        updateChoiceInfo(choices, choiceId3, choiceDescription3, choiceIsCorrect3);
        updateChoiceInfo(choices, choiceId4, choiceDescription4, choiceIsCorrect4);
        questionService.updateQuestion(question);
        return "redirect:/questions-management";
    }

    private void updateChoiceInfo( List<Choice> choices, Integer choiceId, String choiceDescription, boolean choiceIsCorrect){
        choices.forEach(choice -> {if (choice.getId()==choiceId){choice.setDescription(choiceDescription); choice.setCorrect(choiceIsCorrect);}});
    }

}
