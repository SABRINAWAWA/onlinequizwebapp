package com.onlinequizwebapp.onlinequizwebapp.domain.requestDomain;

public class CreateQuestionAnswerRequest {
    private int questionId;
    private int quizId;
    private int userChoiceId;


    public CreateQuestionAnswerRequest(int questionId, int quizId, int userChoiceId) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.userChoiceId = userChoiceId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserChoiceId() {
        return userChoiceId;
    }

    public void setUserChoiceId(int userChoiceId) {
        this.userChoiceId = userChoiceId;
    }

    @Override
    public String toString() {
        return "CreateQuestionAnswerRequest{" +
                "questionId=" + questionId +
                ", quizId=" + quizId +
                ", userChoiceId=" + userChoiceId +
                '}';
    }
}
