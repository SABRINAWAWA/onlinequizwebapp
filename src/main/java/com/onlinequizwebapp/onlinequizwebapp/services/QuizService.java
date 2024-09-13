package com.onlinequizwebapp.onlinequizwebapp.services;

import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.QuestionsDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.QuizDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final QuizDAOImpl quizDAOImpl;

    @Autowired
    public QuizService(QuizDAOImpl quizDAOImpl) {
        this.quizDAOImpl = quizDAOImpl;
    }
    public List<Quiz> getAllQuizResultForUser(Integer userId) {
        return quizDAOImpl.getQuizByUserId(userId);
    }
    public Quiz getQuizResult(Integer quizId) {
        return quizDAOImpl.getQuizByQuizId(quizId);
    }
    public Quiz getQuizResultForUser(Integer quizId, Integer userId) {
        return quizDAOImpl.getQuizByQuizIdUserId(quizId, userId);
    }
    public List<Quiz> getAllQuizResults(){ return quizDAOImpl.getAllQuizResult();}
    public void createNewQuiz(Quiz quiz){ quizDAOImpl.submitQuiz(quiz);}
}
