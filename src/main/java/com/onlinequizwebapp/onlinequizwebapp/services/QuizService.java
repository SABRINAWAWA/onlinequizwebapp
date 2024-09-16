package com.onlinequizwebapp.onlinequizwebapp.services;

import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.QuestionAnswerDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.QuizDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuestionAnswerRequest;
import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuizRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizDAOImpl quizDAOImpl;
    private final QuestionAnswerDAOImpl questionAnswerDAOImpl;

    @Autowired
    public QuizService(QuizDAOImpl quizDAOImpl, QuestionAnswerDAOImpl questionAnswerDAOImpl) {
        this.quizDAOImpl = quizDAOImpl;
        this.questionAnswerDAOImpl = questionAnswerDAOImpl;
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

    public String generateQuizName(Integer categoryId, Integer userId) {
        return quizDAOImpl.generateQuizName(categoryId, userId);
    }

    public List<Quiz> getAllQuizResults() {
        return quizDAOImpl.getAllQuizResult();
    }

    public List<Quiz> getAllQuizResultsPagination(Integer offset) {
        return quizDAOImpl.getAllQuizResultPagination(offset);
    }

    public Integer getNumberOfQuizzes() {
        return quizDAOImpl.getNumberOfQuiz();
    }

    public Quiz createNewQuiz(Integer userId, Integer categoryId, Timestamp startTime, Timestamp endTime, String quizName, HashMap<Integer, Integer> questionAnswers) {
        CreateQuizRequest createQuizRequest = new CreateQuizRequest(userId, categoryId, startTime, endTime, quizName);
        Quiz newQuiz = quizDAOImpl.createQuiz(createQuizRequest);
        for (Map.Entry<Integer, Integer> questionAnswerPair : questionAnswers.entrySet()) {
            CreateQuestionAnswerRequest createQuestionAnswerRequest = new CreateQuestionAnswerRequest(questionAnswerPair.getKey(),
                    newQuiz.getId(), questionAnswerPair.getValue());
            questionAnswerDAOImpl.createQuizQuestion(createQuestionAnswerRequest);
        }
        newQuiz = quizDAOImpl.getQuizByQuizId(newQuiz.getId());
        return newQuiz;
    }

    public List<Quiz> getAllQuizByCategoryId(Integer categoryId, Integer offset) {
        return quizDAOImpl.getQuizByCategoryId(categoryId, offset);
    }

    public List<Quiz> getQuizByUserIdPagination(Integer userId, Integer offset) {
        return quizDAOImpl.getQuizByUserIdPagination(userId, offset);
    }

    public List<Quiz> getAllQuizSortedByUserPagination(Integer offset) {
        return quizDAOImpl.getAllQuizResultPaginationOrderByUser(offset);
    }
    public List<Quiz> getAllQuizResultPaginationOrderByCategory(Integer offset) {
        return quizDAOImpl.getAllQuizResultPaginationOrderByCategory(offset);
    }

    public Integer getNumberOfQuizzesBasedOnCategory(Integer categoryId) {
        return quizDAOImpl.getNumberOfQuizzesBasedOnCategory(categoryId);
    }

    public Integer getNumberOfQuizzesBasedOnUser(Integer userId) {
        return quizDAOImpl.getNumberOfQuizzesBasedOnUser(userId);
    }
}

