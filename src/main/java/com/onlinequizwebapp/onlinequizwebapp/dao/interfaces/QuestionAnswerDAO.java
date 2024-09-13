package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerDAO {
    List<QuestionAnswer> getAllQuestionsAndAnswerByQuizIdUserId(Integer quizId, Integer userId);
    QuestionAnswer getQuestionsAndAnswerByQuizIdUserIdQuestionId(Integer quizId, Integer userId, Integer questionId);
}
