package com.example.quizapp_adeline;

import android.content.Context;

import java.util.ArrayList;

public class QuestionBank {


    public ArrayList<Question> getQuestionList(Context context) {
        if(questionList==null)
        {

            questionList=new ArrayList<Question>(10);

            questionList.add(new Question(context.getResources().getString(R.string.Question1),R.color.color1,false));
            questionList.add(new Question(context.getResources().getString(R.string.Question2),R.color.color2,true));
            questionList.add(new Question(context.getResources().getString(R.string.Question3),R.color.color3,false));
            questionList.add(new Question(context.getResources().getString(R.string.Question4),R.color.color4,false));
            questionList.add(new Question(context.getResources().getString(R.string.Question5),R.color.color5,true));
            questionList.add(new Question(context.getResources().getString(R.string.Question6),R.color.color6,true));
            questionList.add(new Question(context.getResources().getString(R.string.Question7),R.color.color7,true));
            questionList.add(new Question(context.getResources().getString(R.string.Question8),R.color.color8,false));
            questionList.add(new Question(context.getResources().getString(R.string.Question9),R.color.color9,false));
            questionList.add(new Question(context.getResources().getString(R.string.Question10), R.color.color10,false));
        }
        return questionList;
    }

    ArrayList<Question> questionList;



}
