package com.onlinequizwebapp.onlinequizwebapp.domain;

public class QuestionAnswer {
    private Question question;
    private Choice userChoice;
    private Boolean isCorrectAnswer;

    public QuestionAnswer(){};

    public QuestionAnswer(Question question, Choice userChoice, Boolean isCorrectAnswer) {
        this.question = question;
        this.userChoice = userChoice;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Choice getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Choice userChoice) {
        this.userChoice = userChoice;
    }

    public Boolean getCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(Boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "question=" + question +
                ", userChoice=" + userChoice +
                ", isCorrectAnswer=" + isCorrectAnswer +
                '}';
    }
}
