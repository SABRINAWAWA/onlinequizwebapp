package com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Quiz;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        Category category=new Category();
        category.setId(rs.getInt("categoryId"));
        category.setName(rs.getString("categoryName"));
        quiz.setId(rs.getInt("quizId"));
        quiz.setStartTime(rs.getTimestamp("startTime"));
        quiz.setEndTime(rs.getTimestamp("endTime"));
        quiz.setQuizName(rs.getString("quizName"));
        quiz.setCategory(category);
        User user=new User();
        user.setId(rs.getInt("userId"));
        quiz.setQuizTaker(user);
        return quiz;
    }
}
