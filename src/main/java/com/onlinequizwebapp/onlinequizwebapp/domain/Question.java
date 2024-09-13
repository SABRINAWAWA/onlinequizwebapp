package com.onlinequizwebapp.onlinequizwebapp.domain;

import java.util.List;

public class Question {
    private int id;
    private Category category;
    private String description;
    private boolean isActive;
    private List<Choice> choices;
    public Question(){}

    public Question(int id, Category category, String description, boolean isActive, List<Choice> choices) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.isActive = isActive;
        this.choices = choices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", choices=" + choices +
                '}';
    }
}
