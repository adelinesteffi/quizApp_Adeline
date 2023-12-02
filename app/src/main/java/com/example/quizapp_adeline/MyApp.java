package com.example.quizapp_adeline;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    ArrayList<Question> questionListMyApp=new ArrayList<Question>(10);
    int nextQuestionIndex =0;

    int correctAnswerCount=0;

    public String getStringToFile() {
        return stringToFile;
    }

    public void setStringToFile(String stringToFile) {
        this.stringToFile = stringToFile;
    }

    String stringToFile="";
    FileManager fileManager = new FileManager();
    public ArrayList<Question> getQuestionListMyApp() {
        return questionListMyApp;
    }

    public void setQuestionListMyApp(ArrayList<Question> questionListMyApp) {
        this.questionListMyApp = questionListMyApp;
    }

    public int getNextQuestionIndex() {
        return nextQuestionIndex;
    }

    public void setNextQuestionIndex(int nextQuestionIndex) {
        this.nextQuestionIndex = nextQuestionIndex;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }


}
