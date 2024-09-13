package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question> getAllQuestionByCategory(Integer categoryId);
    List<Question> randomPickQuestionByCategory(Integer categoryId);
    List<Question> getAllQuestions();
    List<Question> getQuestionsByQuizId(Integer quizId);
    Question getQuestionByQuestionId(Integer questionId);
    void createNewQuestions(Question question);
    void updateQuestionByQuestionId(Question question);
    Question activateQuestion(Integer questionId);
    Question suspendQuestion(Integer questionId);
}
