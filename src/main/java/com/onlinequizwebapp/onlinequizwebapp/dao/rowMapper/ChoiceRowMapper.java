package com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper;

import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();

        choice.setId(rs.getInt("choiceId"));
        choice.setQuestionId(rs.getInt("questionId"));
        choice.setCorrect(rs.getBoolean("isCorrect"));
        choice.setDescription(rs.getString("description"));
        return choice;
    }
}
