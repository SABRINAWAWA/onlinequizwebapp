package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.QuestionAnswerDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.QuestionAnswerRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionAnswerDAOImpl implements QuestionAnswerDAO {

    JdbcTemplate jdbcTemplate;
    QuestionAnswerRowMapper rowMapper;
    ChoiceDAOImpl choiceDAOImpl;
    QuestionsDAOImpl questionsDAOImpl;

    @Autowired
    public QuestionAnswerDAOImpl(JdbcTemplate jdbcTemplate, QuestionAnswerRowMapper rowMapper, ChoiceDAOImpl choiceDAOImpl, QuestionsDAOImpl questionsDAOImpl) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.choiceDAOImpl=choiceDAOImpl;
        this.questionsDAOImpl=questionsDAOImpl;
    }

    @Override
    public List<QuestionAnswer> getAllQuestionsAndAnswerByQuizIdUserId(Integer quizId, Integer userId) {
        String query="Select qq.quizId as quizId, qq.questionId as questionId, q.userId, qq.userChoiceId as userChoiceId, q.categoryId from QuizQuestion qq LEFT JOIN Quiz q ON qq.quizId=q.quizId WHERE q.userId=? and q.quizId=?;";
        List<QuestionAnswer> questionAnswers = jdbcTemplate.query(query, rowMapper, userId, quizId);
        for (int i=0; i< questionAnswers.size();i++){
            questionAnswers.set(i, getQuestionsAndAnswerByQuizIdUserIdQuestionId(quizId, userId, questionAnswers.get(i).getQuestion().getId()));
        }
        return questionAnswers;
    }

    @Override
    public QuestionAnswer getQuestionsAndAnswerByQuizIdUserIdQuestionId(Integer quizId, Integer userId, Integer questionId) {
        String query="Select qq.quizId as quizId, qq.questionId as questionId, q.userId, qq.userChoiceId as userChoiceId, q.categoryId from QuizQuestion qq LEFT JOIN Quiz q ON qq.quizId=q.quizId WHERE q.userId=? and q.quizId=? and questionId=?;";
        List<QuestionAnswer> questionAnswers = jdbcTemplate.query(query, rowMapper, userId, quizId, questionId);
        QuestionAnswer questionAnswer=questionAnswers.size()==0?null:questionAnswers.get(0);
        try{
            if (questionAnswer!=null){
                Question question=questionsDAOImpl.getQuestionByQuestionId(questionAnswer.getQuestion().getId());
                questionAnswer.setQuestion(question);
                Choice userChoice=choiceDAOImpl.getChoiceByChoiceId(questionAnswer.getUserChoice().getId());
                questionAnswer.setUserChoice(userChoice);
                if (questionAnswer.getUserChoice().getCorrect()){
                    questionAnswer.setIsCorrectAnswer(true);
                }else{
                    questionAnswer.setIsCorrectAnswer(false);
                }
            }
        }catch (Exception e){
            System.out.println("Cannot retrieve question information and its answer.");
        }
        return questionAnswer;
    }
}
