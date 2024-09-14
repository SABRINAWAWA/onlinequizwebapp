package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.QuestionDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.QuestionRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class QuestionsDAOImpl implements QuestionDAO {

    JdbcTemplate jdbcTemplate;
    QuestionRowMapper rowMapper;
    ChoiceDAOImpl choiceDAOImpl;

    @Autowired
    public QuestionsDAOImpl(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper, ChoiceDAOImpl choiceDAOImpl) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.choiceDAOImpl=choiceDAOImpl;
    }

    @Override
    public List<Question> getAllQuestionByCategory(Integer categoryId) {
        String query = "SELECT * FROM questions q LEFT JOIN category c ON c.categoryId=q.categoryId WHERE q.categoryId=?;";
        List<Question> questionBank = jdbcTemplate.query(query, rowMapper, categoryId);
        for (Question q: questionBank){
            List<Choice> choices=choiceDAOImpl.getAllChoiceByQuestionId(q.getId());
            q.setChoices(choices);
        }
        return questionBank;
    }

    @Override
    public List<Question> randomPickQuestionByCategory(Integer categoryId) {
        List<Question> questionBank = getAllQuestionByCategory(categoryId);
        List<Question> validQuestionBack=questionBank.stream().filter(q->q.isActive()).collect(Collectors.toList());
        Set set  = new Random().ints(0,validQuestionBack.size()).distinct().limit(5).boxed().collect(Collectors.toSet());
        List<Question> finalTestSuite=new ArrayList<>();
        set.forEach(e->finalTestSuite.add(validQuestionBack.get((int)e)));
        return finalTestSuite;
    }

    @Override
    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM questions q LEFT JOIN category c ON c.categoryId=q.categoryId;";
        List<Question> allQuestionBank = jdbcTemplate.query(query, rowMapper);
        for (Question q: allQuestionBank){
            List<Choice> choices=choiceDAOImpl.getAllChoiceByQuestionId(q.getId());
            q.setChoices(choices);
        }
        return allQuestionBank;
    }

    @Override
    public List<Question> getQuestionsByQuizId(Integer quizId) {
        String query = "SELECT * FROM quizquestion where quizId=?";
        List<Question> allQuestionBank = jdbcTemplate.query(query, rowMapper, quizId);
        return allQuestionBank;
    }

    @Override
    public Question getQuestionByQuestionId(Integer questionId) {
        String selectQuery = "SELECT * FROM questions q LEFT JOIN category c ON c.categoryId=q.categoryId WHERE questionId=?;";
        List<Question> questions = jdbcTemplate.query(selectQuery, rowMapper, questionId);
        Question question=questions.size()==0?null:questions.get(0);
        try{
            if(question!=null) {
                List<Choice> choices = choiceDAOImpl.getAllChoiceByQuestionId(questionId);
                question.setChoices(choices);
            }
        }catch (Exception e){
            System.out.println("Cannot assign choices to selected question");
        }
        return question;
    }

    @Override
    public void createNewQuestions(Question question) {
        String query = "INSERT INTO questions (categoryId, description, isActive) values (?, ?, true)";
        jdbcTemplate.update(query, question.getCategory().getId(), question.getDescription());
        String selectQuery = "SELECT * FROM questions q LEFT JOIN category c ON c.categoryId=q.categoryId WHERE description=?;";
        Integer selectedQuestionId = jdbcTemplate.query(selectQuery, rowMapper, question.getDescription()).get(0).getId();
        for (Choice c : question.getChoices()){
            c.setQuestionId(selectedQuestionId);
            choiceDAOImpl.createNewChoice(c);
        }
    }

    @Override
    public void updateQuestionByQuestionId(Question question) {
        String query = "UPDATE questions SET description=?, categoryId=? WHERE questionId=?";
        jdbcTemplate.update(query, question.getDescription(), question.getCategory().getId(), question.getId());
        for (Choice c : question.getChoices()){
            choiceDAOImpl.updateChoiceByChoiceId(c);
        }
    }

    @Override
    public Question activateQuestion(Integer questionId) {
        String query = "UPDATE questions SET isActive=true WHERE questionId=?";
        jdbcTemplate.update(query, questionId);
        return getQuestionByQuestionId(questionId);
    }

    @Override
    public Question suspendQuestion(Integer questionId) {
        String query = "UPDATE questions SET isActive=false WHERE questionId=?";
        jdbcTemplate.update(query, questionId);
        return getQuestionByQuestionId(questionId);
    }
}
