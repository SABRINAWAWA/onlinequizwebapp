package com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain;

import java.sql.Timestamp;

public class CreateQuizRequest {
    private int userId;
    private int categoryId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String quizName;

    public CreateQuizRequest(){}

    public CreateQuizRequest(int userId, int categoryId, Timestamp startTime, Timestamp endTime, String quizName) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.quizName = quizName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    @Override
    public String toString() {
        return "CreateQuizRequest{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", quizName='" + quizName + '\'' +
                '}';
    }
}
