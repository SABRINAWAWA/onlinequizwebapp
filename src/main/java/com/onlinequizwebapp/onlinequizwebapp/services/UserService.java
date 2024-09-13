package com.onlinequizwebapp.onlinequizwebapp.services;

import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.CategoryDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.UserDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAOImpl userDAOImpl;

    @Autowired
    public UserService(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }
    public List<User> getAllUsers() {
        return userDAOImpl.getAllUsers();
    }
    public void createNewUser(User user){ userDAOImpl.createNewUser(user);}
    public User activeUserById(Integer id){return userDAOImpl.activeUserById(id);}
    public User suspendedUserById(Integer id){return userDAOImpl.suspendedUserById(id);}
    public User getUserById(Integer id) {
        return userDAOImpl.getUserById(id);
    }
}
