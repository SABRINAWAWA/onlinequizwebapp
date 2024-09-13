package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.User;

import java.util.List;

public interface UserDAO {
    void createNewUser(User user);
    User activeUserById(Integer id);
    User suspendedUserById(Integer id);
    List<User> getAllUsers();
    User getUserById(Integer id);
}
