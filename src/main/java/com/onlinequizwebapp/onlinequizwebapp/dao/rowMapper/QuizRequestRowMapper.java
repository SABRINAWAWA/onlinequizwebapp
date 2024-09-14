package com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper;

import com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain.CreateQuizRequest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRequestRowMapper implements RowMapper<CreateQuizRequest> {
    @Override
    public CreateQuizRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        CreateQuizRequest createQuizRequest = new CreateQuizRequest();
        createQuizRequest.setQuizName(rs.getString("name"));
        createQuizRequest.setStartTime(rs.getTimestamp("startTime"));
        createQuizRequest.setEndTime(rs.getTimestamp("endTime"));
        createQuizRequest.setCategoryId(rs.getInt("categoryId"));
        createQuizRequest.setUserId(rs.getInt("userId"));
        return createQuizRequest;
    }
}
