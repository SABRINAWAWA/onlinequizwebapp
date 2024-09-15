package com.onlinequizwebapp.onlinequizwebapp.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private Integer timeDuration;

    public Quiz(){}

    public Quiz(int id, Category category, User quizTaker, Date startTime, Date endTime, List<QuestionAnswer> questionAnswerList, String quizName, Integer timeDuration) {
        this.id = id;
        this.category = category;
        this.quizTaker = quizTaker;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionAnswerList = questionAnswerList;
        this.quizName=quizName;
        this.timeDuration = timeDuration;
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

    public void setNumberOfCorrectQuestions(){
        this.numberOfCorrectQuestions=0;
        for (QuestionAnswer questionAnswer : this.questionAnswerList){
            if(questionAnswer.getCorrectAnswer()){
                this.numberOfCorrectQuestions+=1;
            }
        }
    }

    public void setIsPass(){
        if (this.numberOfCorrectQuestions>=3){
            isPass=true;
        }else{
            isPass=false;
        }
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public Integer getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(Integer timeDuration) {
        this.timeDuration = timeDuration;
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
                ", timeDuration=" + timeDuration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id && Objects.equals(category, quiz.category) && Objects.equals(quizTaker, quiz.quizTaker) && Objects.equals(startTime, quiz.startTime) && Objects.equals(endTime, quiz.endTime) && Objects.equals(questionAnswerList, quiz.questionAnswerList) && Objects.equals(isPass, quiz.isPass) && Objects.equals(numberOfCorrectQuestions, quiz.numberOfCorrectQuestions) && Objects.equals(quizName, quiz.quizName) && Objects.equals(timeDuration, quiz.timeDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, quizTaker, startTime, endTime, questionAnswerList, isPass, numberOfCorrectQuestions, quizName, timeDuration);
    }
}
