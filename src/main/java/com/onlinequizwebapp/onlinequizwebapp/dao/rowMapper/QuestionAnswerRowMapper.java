package com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.Question;
import com.onlinequizwebapp.onlinequizwebapp.domain.QuestionAnswer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionAnswerRowMapper implements RowMapper<QuestionAnswer> {
    @Override
    public QuestionAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        Question question=new Question();
        question.setId(rs.getInt("questionId"));
        Choice userChoice=new Choice();
        userChoice.setId(rs.getInt("userChoiceId"));
        questionAnswer.setQuestion(question);
        questionAnswer.setUserChoice(userChoice);
        return questionAnswer;
    }
}

/*
select qq.quizId as quizId, qq.questionId as questionId, q.userId, qq.userChoiceId as userChoiceId, q.categoryId from QuizQuestion qq
LEFT JOIN Quiz q
ON qq.quizId=q.quizId
WHERE q.userId=1 and q.quizId=4
 */