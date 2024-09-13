package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.QuizDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.UserDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.QuizRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.UserRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.domain.QuestionAnswer;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizDAOImpl implements QuizDAO {

    JdbcTemplate jdbcTemplate;
    QuizRowMapper rowMapper;
    QuestionAnswerDAOImpl questionAnswerDAOImpl;
    UserDAOImpl userDAOImpl;
    QuestionsDAOImpl questionsDAOImpl;

    @Autowired
    public QuizDAOImpl(JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper, QuestionAnswerDAOImpl questionAnswerDAOImpl, UserDAOImpl userDAOImpl, QuestionsDAOImpl questionsDAOImpl) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.questionAnswerDAOImpl=questionAnswerDAOImpl;
        this.userDAOImpl=userDAOImpl;
        this.questionsDAOImpl=questionsDAOImpl;
    }

    @Override
    public Quiz getQuizByQuizId(Integer quizId) {
        String query="SELECT qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.quizId=?;";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, quizId);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList.get(0);
    }

    @Override
    public List<Quiz> getQuizByUserId(Integer userId) {
        String query="SELECT qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.userId=?;";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, userId);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList;
    }

    @Override
    public Quiz getQuizByQuizIdUserId(Integer quizId, Integer userId) {
        String query="SELECT qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.userId=? AND qc.quizId=?;";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, userId, quizId);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList.size()==0?null:quizList.get(0);
    }

    @Override
    public List<Quiz> getAllQuizResult() {
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList;
    }


    @Override
    public void submitQuiz(Quiz quiz) {

    }

    @Override
    public void generateNewQuiz(Integer userId) {

    }
}
