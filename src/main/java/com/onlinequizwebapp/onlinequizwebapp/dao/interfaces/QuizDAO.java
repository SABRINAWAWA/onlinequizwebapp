package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.QuestionAnswer;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;

import java.util.List;

public interface QuizDAO {
    List<Quiz> getAllQuizResult();
    Quiz getQuizByQuizIdUserId(Integer quizId, Integer userId);
    List<Quiz> getQuizByUserId(Integer userId);
    Quiz getQuizByQuizId(Integer quizId);
    void submitQuiz(Quiz quiz);
    void generateNewQuiz(Integer userId);
}
