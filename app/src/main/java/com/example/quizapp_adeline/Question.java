package com.example.quizapp_adeline;

public class Question
{

    public Question(String questionText, int bckGrndColor, boolean answer) {
        this.questionText = questionText;
        this.bckGrndColor = bckGrndColor;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getBckGrndColor() {
        return bckGrndColor;
    }

    public void setBckGrndColor(int bckGrndColor) {
        this.bckGrndColor = bckGrndColor;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    String questionText;
    int bckGrndColor;
    boolean answer;

}
