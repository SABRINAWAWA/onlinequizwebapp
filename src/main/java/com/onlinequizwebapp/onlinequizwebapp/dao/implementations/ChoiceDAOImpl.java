package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.ChoiceDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.UserDAO;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.ChoiceRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.UserRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDAOImpl implements ChoiceDAO {

    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper rowMapper;

    @Autowired
    public ChoiceDAOImpl(JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Choice> getAllChoiceByQuestionId(Integer id) {
        String query = "SELECT * FROM choices WHERE questionId=?";
        List<Choice> choices = jdbcTemplate.query(query, rowMapper, id);
        return choices;
    }

    @Override
    public List<Choice> getAllChoices() {
        String query = "SELECT * FROM choices";
        List<Choice> choices = jdbcTemplate.query(query, rowMapper);
        return choices;
    }

    @Override
    public void createNewChoice(Choice choice) {
        String query = "INSERT INTO choices (questionId, description, isCorrect) values (?, ?, ?)";
        jdbcTemplate.update(query, choice.getQuestionId(), choice.getDescription(), choice.getCorrect());
    }

    @Override
    public void updateChoiceByChoiceId(Choice choice) {
        String query = "Update choices SET description=?, isCorrect=? where choiceId=?";
        jdbcTemplate.update(query, choice.getDescription(), choice.getCorrect(), choice.getId());
    }

    @Override
    public Choice getChoiceByChoiceId(Integer id) {
        String query = "SELECT * FROM choices WHERE choiceId=?";
        List<Choice> choices = jdbcTemplate.query(query, rowMapper);
        return choices.size()==0?null:choices.get(0);
    }
}
