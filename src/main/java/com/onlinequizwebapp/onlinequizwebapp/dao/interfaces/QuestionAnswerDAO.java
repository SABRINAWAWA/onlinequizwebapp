package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.QuestionAnswer;
import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuestionAnswerRequest;

import java.util.List;

public interface QuestionAnswerDAO {
    List<QuestionAnswer> getAllQuestionsAndAnswerByQuizIdUserId(Integer quizId, Integer userId);
    QuestionAnswer getQuestionsAndAnswerByQuizIdUserIdQuestionId(Integer quizId, Integer userId, Integer questionId);
    void createQuizQuestion(CreateQuestionAnswerRequest createQuestionAnswerRequest);
}
