package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Choice;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;

import java.util.List;

public interface ChoiceDAO {
    List<Choice> getAllChoiceByQuestionId(Integer id);
    List<Choice> getAllChoices();
    void createNewChoice(Choice choice);
    void updateChoiceByChoiceId(Choice choice);
    Choice getChoiceByChoiceId(Integer id);
}
