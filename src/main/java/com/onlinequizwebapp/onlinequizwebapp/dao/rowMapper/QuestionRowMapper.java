package com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        Category category=new Category();
        category.setId(rs.getInt("categoryId"));
        category.setName(rs.getString("name"));
        question.setId(rs.getInt("questionId"));
        question.setCategory(category);
        question.setDescription(rs.getString("description"));
        question.setActive(rs.getBoolean("isActive"));
        return question;
    }
}
