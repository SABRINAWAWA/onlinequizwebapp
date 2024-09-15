package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuizRequest;

import java.util.List;

public interface QuizDAO {
    List<Quiz> getAllQuizResult();
    Quiz getQuizByQuizIdUserId(Integer quizId, Integer userId);
    List<Quiz> getQuizByUserId(Integer userId);
    List<Quiz> getQuizByCategoryId(Integer categoryId);
    Quiz getQuizByQuizId(Integer quizId);
    Quiz createQuiz(CreateQuizRequest createQuizRequest);
    String generateQuizName(Integer categoryId, Integer userId);
    Quiz getQuizByUserIdCategoryIdQuizName(CreateQuizRequest createQuizRequest);
}
