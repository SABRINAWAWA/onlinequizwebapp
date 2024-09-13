package com.onlinequizwebapp.onlinequizwebapp.dao.implementations;

import com.onlinequizwebapp.onlinequizwebapp.dao.rowMapper.CategoryRowMapper;
import com.onlinequizwebapp.onlinequizwebapp.dao.interfaces.CategoryDAO;
import com.onlinequizwebapp.onlinequizwebapp.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    JdbcTemplate jdbcTemplate;
    CategoryRowMapper rowMapper;

    @Autowired
    public CategoryDAOImpl(JdbcTemplate jdbcTemplate, CategoryRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    // 1. CREATE a Category
    @Override
    public void createNewCategory(String name){
        String query = "INSERT INTO category (name) values (?)";
        jdbcTemplate.update(query, name);
    }

    // 2. READ all Categories
    @Override
    public List<Category> getAllCategory(){
        String query = "SELECT * FROM category";

        List<Category> categories = jdbcTemplate.query(query, rowMapper);

        return categories;
    }

    // 3. READ a Category by id
    @Override
    public Category getCategoryById(Integer id){
        String query = "SELECT * FROM category WHERE id=?";

        List<Category> categories = jdbcTemplate.query(query, rowMapper, id);

        return categories.size() == 0 ? null : categories.get(0);
    }

    // 4. READ a Category by name
    @Override
    public Category getCategoryByName(String name){
        String query = "SELECT * FROM category WHERE name=?";

        List<Category> categories = jdbcTemplate.query(query, rowMapper, name.toUpperCase());

        return categories.size() == 0 ? null : categories.get(0);
    }
}
