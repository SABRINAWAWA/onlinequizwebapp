package com.onlinequizwebapp.onlinequizwebapp.services;

import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.CategoryDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.dao.implementations.ContactDAOImpl;
import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import com.onlinequizwebapp.onlinequizwebapp.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryDAOImpl categoryDAOImpl;

    @Autowired
    public CategoryService(CategoryDAOImpl categoryDAOImpl) {
        this.categoryDAOImpl = categoryDAOImpl;
    }

    public List<Category> getAllCategory() {
        return categoryDAOImpl.getAllCategory();
    }

    public List<Category> getAllCategoriesInQuiz(){return categoryDAOImpl.getAllCategoryInQuiz();}
}