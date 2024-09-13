package com.onlinequizwebapp.onlinequizwebapp.domain;

import java.util.Date;
import java.util.List;

public class Quiz {
    private int id;
    private Category category;
    private User quizTaker;
    private Date startTime;
    private Date endTime;
    private List<QuestionAnswer> questionAnswerList;
    private Boolean isPass;
    private Integer numberOfCorrectQuestions;
    private String quizName;

    public Quiz(){}

    public Quiz(int id, Category category, User quizTaker, Date startTime, Date endTime, List<QuestionAnswer> questionAnswerList, String quizName) {
        this.id = id;
        this.category = category;
        this.quizTaker = quizTaker;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionAnswerList = questionAnswerList;
        this.quizName=quizName;
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

    public User getQuizTaker() {
        return quizTaker;
    }

    public void setQuizTaker(User quizTaker) {
        this.quizTaker = quizTaker;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public Boolean getPass() {
        return isPass;
    }

    public Integer getNumberOfCorrectQuestions() {
        return numberOfCorrectQuestions;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    private void setNumberOfCorrectQuestions(){
        this.numberOfCorrectQuestions=0;
        for (QuestionAnswer questionAnswer : this.questionAnswerList){
            this.numberOfCorrectQuestions+=questionAnswer.getCorrectAnswer()?0:1;
        }
    }

    private void setIsPass(){
        if ((this.numberOfCorrectQuestions/this.questionAnswerList.size()*100)>50){
            isPass=true;
        }else{
            isPass=false;
        }
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", category=" + category +
                ", quizTaker=" + quizTaker +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", questionAnswerList=" + questionAnswerList +
                ", isPass=" + isPass +
                ", numberOfCorrectQuestions=" + numberOfCorrectQuestions +
                ", quizName='" + quizName + '\'' +
                '}';
    }
}
