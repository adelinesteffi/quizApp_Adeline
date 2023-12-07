package com.example.quizapp_adeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ChooseQuestionsDialogFragment.chooseQuestionsDialogFragment.ChooseQuestionsDialogListener  {
    private FrameLayout frameLayout;
    private Button myButtonTrue, myButtonFalse;
    private ProgressBar progressBar;
    private ArrayList<Question> questionsList;  // Add your questions herebool
    FileManager fm;
    boolean buttonChoosed=false;
    boolean correctAnswer=false;
int nextIndex=0;
int correctAnswers=0;
    QuestionBank bankObj = new QuestionBank();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.framelayout);
        myButtonTrue = findViewById(R.id.myButtonTrue);
        myButtonFalse = findViewById(R.id.myButtonFalse);
        progressBar = findViewById(R.id.progressBar);
        questionsList = bankObj.getQuestionList(this);  // Add your questions here
        Collections.shuffle(questionsList);
        ((MyApp)getApplication()).questionListMyApp=questionsList;
        fm =  ((MyApp)getApplication()).fileManager;

     QustionFragment check_fragment = (QustionFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout);
//
        if (check_fragment != null) {
            Log.d("testing", "oncreate  if");
            getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();

            questionsList=((MyApp)getApplication()).getQuestionListMyApp();
          nextIndex=((MyApp) getApplication()).nextQuestionIndex;
           correctAnswers=((MyApp)getApplication()).correctAnswerCount;
        }
        else {//1st time
            Log.d("testing", "On create else ");
            questionsList= bankObj.getQuestionList(this);
            Collections.shuffle(questionsList);

            ((MyApp)getApplication()).setQuestionListMyApp(questionsList);
          ((MyApp)getApplication()).setNextQuestionIndex(nextIndex);
          ((MyApp)getApplication()).setCorrectAnswerCount(correctAnswers);

         //   getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();

        }
        check_fragment = check_fragment.newInstance(questionsList.get(nextIndex));
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, check_fragment).commit();


        myButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nextIndex++;
                // Handle True button click
                Log.d("testing", "in true btn");
                buttonChoosed= true;
                showNextQuestion();

            }
        });

        myButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChoosed =false;
                Log.d("testing", "in true btn");
                // Handle False button click
               showNextQuestion();
            }
        });

       // showNextQuestion();
    }

    private void showNextQuestion() {
        Log.d("testing", "nextIndex in showNextQuestion "+nextIndex);
        Log.d("testing", " questionsList.size() in showNextQuestion "+questionsList.size());

        if (nextIndex < questionsList.size()) {
            // Create a new instance of the QuestionFragment
            QustionFragment questionFragment = new QustionFragment();

            // Set the question for the fragment
            String currentQuestion = String.valueOf(questionsList.get(nextIndex));
          //  questionFragment.setQuestion(currentQuestion);
            Log.d("testing", "currentQuestion "+currentQuestion);
//            // Replace the fragment in the FrameLayout
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.framelayout, questionFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
            questionsList=((MyApp)getApplication()).getQuestionListMyApp();
            nextIndex=((MyApp) getApplication()).nextQuestionIndex;
            correctAnswers=((MyApp)getApplication()).correctAnswerCount;
           QustionFragment check_fragment = (QustionFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout);
            if (check_fragment == null) {
               // ThirdFragment tf = new ThirdFragment();
                //questionFragment.listener = MainActivity.this;
                Log.d("testing", "show nxt qstn if");
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, questionFragment).commit();
            }
            else {

               nextIndex++;
                Log.d("testing", "currentQuestionIndex "+nextIndex);
               ((MyApp)getApplication()).nextQuestionIndex =nextIndex;
               Log.d("testing", "show nxt qstn else");

               // questionFragment = check_fragment.newInstance(questionsList.get(nextIndex));
                //getSupportFragmentManager().beginTransaction().add(R.id.framelayout, check_fragment).commit();
                if (nextIndex < questionsList.size()) {
//                    nextIndex++;
//                    Log.d("testing", "currentQuestionIndex "+nextIndex);
//                    ((MyApp)getApplication()).nextQuestionIndex =nextIndex;
//                    Log.d("testing", "show nxt qstn else");

                    getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
                    check_fragment = check_fragment.newInstance(questionsList.get(nextIndex));
                    //  check_fragment.getView().setBackgroundColor(questionsList.get(nextIndex).getBckGrndColor());
                    getSupportFragmentManager().beginTransaction().add(R.id.framelayout, check_fragment).commit();
                    correctAnswer=questionsList.get(nextIndex).isAnswer();
                    Log.d("testing", "correctAnswer in array   "+correctAnswer);
                    if(buttonChoosed==correctAnswer)
                    {
                        correctAnswers++;
                        ((MyApp)getApplication()).setCorrectAnswerCount(correctAnswers);
                        Log.d("testing", "correctAnswers count  "+correctAnswers);
                        Toast.makeText(this ,"correct" , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(this ,"wrong" , Toast.LENGTH_LONG).show();
                    }
                }
            }

            // Increment the question index
            Log.d("testing", "correctAnswers count  outside if"+correctAnswers);
            Log.d("testing", "((MyApp)getApplication()).getCorrectAnswerCount() count  outside if"+((MyApp)getApplication()).getCorrectAnswerCount());

        } else {
            // Handle the end of questions

          //  Toast.makeText(this ,"You have answered "+correctAnswers+" question correctly out of "+questionsList.size() , Toast.LENGTH_LONG).show();
            askThenSave();

        }
        updateProgressBar();
    }

    private void updateProgressBar() {
        // Calculate the progress percentage
        Log.d("testing", "currentQuestionIndex in progress bar "+nextIndex);
        int progress = (int) ((double) nextIndex / 10 * 100);
        Log.d("testing", "progress "+progress);
        // Set the progress to the ProgressBar
        progressBar.setProgress(progress);
    }
    void askThenSave(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
String resultMessage="Your score is-"+correctAnswers+" out of-"+questionsList.size();
        String resultMessageToWritInFile= "Your score is-"+correctAnswers+"\n";
        ((MyApp)getApplication()).setStringToFile(resultMessageToWritInFile);
        // Set the title for the AlertDialog
        builder.setTitle("Result");
        builder.setMessage(resultMessage);
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

              //  saveScoreToFile(correctAnswers);
                Log.d("testing", "currentQuestionIndex in progress bar "+nextIndex);
                Log.d("testing", "resultMessageToWritInFile in save "+resultMessageToWritInFile);
              fm.writeToDoToFile(MainActivity.this, resultMessageToWritInFile);

                ArrayList<String> stringArray=fm.getAllScoreStringSentence(MainActivity.this);
                ArrayList<Integer> getScores=  fm.extractScores(stringArray);
                for (int score : getScores) {
                    Log.d("testing", "Extracted score:  score "+score);
                }
                double average = fm.calculateAverage(getScores);
                // getAllToDOFromFile();
                int length = getScores.size();
                Log.d("testing", "average in save "+average+" out of "+length+" attempts");
            }
        });
        builder.setNegativeButton("IGNORE ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // getAllToDoFromDB();
            }
        });
        builder.create().show();

        progressBar.setProgress(0);
        nextIndex=0;
        correctAnswers=0;
        Collections.shuffle(questionsList);
        ((MyApp)getApplication()).questionListMyApp=questionsList;
        ((MyApp)getApplication()).nextQuestionIndex =nextIndex;
   ((MyApp)getApplication()).correctAnswerCount=correctAnswers;

        showNextQuestion();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.get_average) {
           ArrayList<String> stringArray = fm.getAllScoreStringSentence(MainActivity.this);
           ArrayList<Integer> getScores = fm.extractScores(stringArray);
           for (int score : getScores) {
               Log.d("testing", "Extracted score:  score " + score);
           }
           double average = fm.calculateAverage(getScores);
           int length = getScores.size();
           Log.d("testing", "average in save " + average + " out of " + length + " attempts");

           Toast.makeText(this, "average is  " + average + " out of " + length + " attempts" + questionsList.size(), Toast.LENGTH_LONG).show();

       }
       else  if(item.getItemId() == R.id.select_questions) {
           ChooseQuestionsDialogFragment dialogFragment = new ChooseQuestionsDialogFragment();
        //   dialogFragment.show(getSupportFragmentManager(), "ChooseQuestionsDialog");

       }


       else  if(item.getItemId() == R.id.reset_results) {
           fm.deleteScores(MainActivity.this);
       }
        return true;
    }

    // Implement the interface method
    @Override
    public void onQuestionsChosen(int numberOfQuestions) {
        // Handle the chosen number of questions
        Toast.makeText(this, "Number of Questions: " + numberOfQuestions, Toast.LENGTH_SHORT).show();
    }

}