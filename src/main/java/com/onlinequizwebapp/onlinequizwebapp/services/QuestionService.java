package com.onlinequizwebapp.onlinequizwebapp.services;

import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.QuestionsDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.UserDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionsDAOImpl questionsDAOImpl;

    @Autowired
    public QuestionService(QuestionsDAOImpl questionsDAOImpl) {
        this.questionsDAOImpl = questionsDAOImpl;
    }
    public List<Question> getAllQuestions() {
        return questionsDAOImpl.getAllQuestions();
    }
    public void createNewQuestion(Question question){ questionsDAOImpl.createNewQuestions(question);}
    public void updateQuestion(Question question){ questionsDAOImpl.updateQuestionByQuestionId(question);}

    public Question activeQuestionById(Integer id){return questionsDAOImpl.activateQuestion(id);}
    public Question suspendedQuestionById(Integer id){return questionsDAOImpl.suspendQuestion(id);}
    public Question getQuestionById(Integer id) {
        return questionsDAOImpl.getQuestionByQuestionId(id);
    }
    public List<Question> generateQuestionsByCategory(Integer id){
        return questionsDAOImpl.randomPickQuestionByCategory(id);
    }
}
