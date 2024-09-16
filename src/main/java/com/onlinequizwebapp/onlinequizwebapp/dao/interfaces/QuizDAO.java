package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuizRequest;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface QuizDAO {
    List<Quiz> getAllQuizResult();
    Quiz getQuizByQuizIdUserId(Integer quizId, Integer userId);
    List<Quiz> getQuizByUserId(Integer userId);
    List<Quiz> getQuizByCategoryId(Integer categoryId, Integer offset);
    Quiz getQuizByQuizId(Integer quizId);
    Quiz createQuiz(CreateQuizRequest createQuizRequest);
    String generateQuizName(Integer categoryId, Integer userId);
    Quiz getQuizByUserIdCategoryIdQuizName(CreateQuizRequest createQuizRequest);
    List<Quiz> getAllQuizResultPagination(Integer offset);
    Integer getNumberOfQuiz();
    List<Quiz> getQuizByUserIdPagination(Integer userId, Integer offset);
    List<Quiz> getAllQuizResultPaginationOrderByUser(Integer offset);
    List<Quiz> getAllQuizResultPaginationOrderByCategory(Integer offset);
    Integer getNumberOfQuizzesBasedOnCategory(Integer categoryId);
    Integer getNumberOfQuizzesBasedOnUser(Integer userId);
}
