package com.onlinequizwebapp.onlinequizwebapp.dao.interfaces;

import com.onlinequizwebapp.onlinequizwebapp.domain.Category;

import java.util.List;

public interface CategoryDAO {
    void createNewCategory(String name);
    List<Category> getAllCategory();
    Category getCategoryById(Integer id);
    Category getCategoryByName(String name);
    List<Category> getAllCategoryInQuiz();
}
