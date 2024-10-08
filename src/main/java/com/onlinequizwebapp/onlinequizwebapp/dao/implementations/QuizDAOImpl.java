package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.QuizDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.QuizRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuizRequest;
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
    CategoryDAOImpl categoryDAOImpl;

    @Autowired
    public QuizDAOImpl(JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper,
                       QuestionAnswerDAOImpl questionAnswerDAOImpl, UserDAOImpl userDAOImpl,
                       QuestionsDAOImpl questionsDAOImpl, CategoryDAOImpl categoryDAOImpl) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.questionAnswerDAOImpl=questionAnswerDAOImpl;
        this.userDAOImpl=userDAOImpl;
        this.questionsDAOImpl=questionsDAOImpl;
        this.categoryDAOImpl=categoryDAOImpl;
    }

    @Override
    public Quiz getQuizByQuizId(Integer quizId) {
        String query="SELECT qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
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
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.userId=? \n" +
                "ORDER BY qc.startTime desc;";
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
    public List<Quiz> getQuizByCategoryId(Integer categoryId, Integer offset) {
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.categoryId=? \n" +
                "ORDER BY qc.startTime desc \n" +
                "LIMIT 5 OFFSET ?;";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, categoryId, offset);
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
        String query="SELECT qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.userId=? AND qc.quizId=? \n" +
                "ORDER BY qc.startTime DESC";
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
    public List<Quiz> getQuizByUserIdPagination(Integer userId, Integer offset) {
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.userId=? \n" +
                "ORDER BY qc.startTime DESC\n" +
                "LIMIT 5 OFFSET ?;";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, userId, offset);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList;
    }

    @Override
    public List<Quiz> getAllQuizResult() {
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "ORDER BY qc.startTime desc";
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
    public List<Quiz> getAllQuizResultPagination(Integer offset) {
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "ORDER BY qc.startTime desc \n" +
                "LIMIT 5 OFFSET ?";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, offset);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList;
    }

    @Override
    public List<Quiz> getAllQuizResultPaginationOrderByCategory(Integer offset) {
        String query="SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "ORDER BY qc.categoryName asc \n" +
                "LIMIT 5 OFFSET ?";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, offset);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList;
    }

    @Override
    public List<Quiz> getAllQuizResultPaginationOrderByUser(Integer offset) {
        String query="select distinct qqc.quizId, qqc.userId, qqc.quizName, qqc.startTime, qqc.endTime, qqc.categoryId, qqc.categoryName, qqc.timeDuration, u.firstname, u.lastname\n" +
                "from (SELECT distinct qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN (SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId) as qqc\n" +
                "left join users u\n" +
                "on u.userId=qqc.userId\n" +
                "ORDER BY u.firstname asc, u.lastname asc\n" +
                "LIMIT 5 OFFSET ?;";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, offset);
        for (Quiz quiz:quizList){
            quiz.setQuizTaker(userDAOImpl.getUserById(quiz.getQuizTaker().getId()));
            quiz.setQuestionAnswerList(questionAnswerDAOImpl.getAllQuestionsAndAnswerByQuizIdUserId(quiz.getId(), quiz.getQuizTaker().getId()));
            quiz.setNumberOfCorrectQuestions();
            quiz.setIsPass();
        }
        return quizList;
    }

    @Override
    public Integer getNumberOfQuiz() {
        String query="SELECT count(*) FROM Quiz;";
        Integer numOfQuiz=jdbcTemplate.queryForObject(query, Integer.class);
        return numOfQuiz;
    }

    @Override
    public Quiz createQuiz(CreateQuizRequest createQuizRequest) {
        String query="INSERT INTO Quiz (userId, categoryId, name, startTime, endTime) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, createQuizRequest.getUserId(), createQuizRequest.getCategoryId(), createQuizRequest.getQuizName(),
                createQuizRequest.getStartTime(), createQuizRequest.getEndTime());
        return getQuizByUserIdCategoryIdQuizName(createQuizRequest);
    }

    @Override
    public String generateQuizName(Integer categoryId, Integer userId) {
        String query="SELECT COUNT(*) FROM Quiz q where q.categoryId=? and userId=?;";
        Integer totalQuiz = jdbcTemplate.queryForObject(query, Integer.class, categoryId, userId);
        int numOfQuiz=totalQuiz+1;
        Category category=categoryDAOImpl.getCategoryById(categoryId);
        return "Quiz_"+category.getName()+"_"+numOfQuiz;
    }

    @Override
    public Quiz getQuizByUserIdCategoryIdQuizName(CreateQuizRequest createQuizRequest) {
        String query="SELECT qc.quizId, qc.userId, qc.quizName, qc.startTime, qc.endTime, qc.categoryId, qc.categoryName, TIMESTAMPDIFF(SECOND, qc.startTime, qc.endTime) AS timeDuration\n" +
                "FROM QuizQuestion qq\n" +
                "RIGHT JOIN \n" +
                "(SELECT q.quizId as quizId, q.userId as userId, q.name AS quizName, q.startTime AS startTime, q.endTime AS endTime, c.categoryId AS categoryId, c.name AS categoryName FROM quiz q LEFT JOIN category c on c.categoryID=q.categoryId) as qc\n" +
                "ON qc.quizId=qq.quizId \n" +
                "WHERE qc.quizName=? AND qc.categoryId=? AND qc.userId=?";
        List<Quiz> quizList = jdbcTemplate.query(query, rowMapper, createQuizRequest.getQuizName(), createQuizRequest.getCategoryId(), createQuizRequest.getUserId());
        return quizList.size()==0?null:quizList.get(0);
    }

    public Integer getNumberOfQuizzesBasedOnCategory(Integer categoryId){
        String query="SELECT COUNT(*) FROM Quiz q where q.categoryId=?";
        Integer totalQuiz = jdbcTemplate.queryForObject(query, Integer.class, categoryId);
        return totalQuiz;
    }

    public Integer getNumberOfQuizzesBasedOnUser(Integer userId){
        String query="SELECT COUNT(*) FROM Quiz q where q.userId=?";
        Integer totalQuiz = jdbcTemplate.queryForObject(query, Integer.class, userId);
        return totalQuiz;
    }
}
