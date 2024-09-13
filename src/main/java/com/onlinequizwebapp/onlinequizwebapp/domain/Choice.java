package com.onlinequizwebapp.onlinequizwebapp.domain;

public class Choice {
    private int id;
    private String description;
    private Boolean isCorrect;
    private int questionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Choice(){};

    public Choice(int id, String description, Boolean isCorrect, int questionId) {
        this.id = id;
        this.description = description;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                '}';
    }
}
