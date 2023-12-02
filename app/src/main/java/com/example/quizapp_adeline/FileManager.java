package com.example.quizapp_adeline;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    String fileName = "QuizFile.txt";
    void writeToDoToFile(Context context,String statement){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            Log.d("testing", statement+"statement  fileName "+fileName);
            fos.write(statement.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("SaveScore", "Error saving score to file: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("SaveScore", "Error saving score to file: " + e.getMessage());
            e.printStackTrace();
        }
        // byte level array - stream of characters
        // MODE_APPEND = continue writing
        // MODE.PRIVATE = overwright

        //openFileOutput 1- the file is not exist ==> create a new file and open it
        //              2- the file is exist == > open it.

        // I have to write a byte array only
        // we convert the todo to a byte array
    }

    void deleteScores(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ArrayList<String> getAllScoreStringSentence(Context context) {
        ArrayList<String> list = new ArrayList<>(0);
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();

                while (line != null) {
                    list.add(line);
                    line = reader.readLine();
                }
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Integer> extractScores(ArrayList<String> inputList) {
        ArrayList<Integer> scores = new ArrayList<>();
        Pattern pattern = Pattern.compile("-\\d+");

        for (String input : inputList) {
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String match = matcher.group();
                int score = Integer.parseInt(match.substring(1)); // Remove the hyphen
                scores.add(score);
            }
        }

        return scores;
    }

    public static double calculateAverage(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0.0; // Handle the case where the list is empty or null
        }

        int sum = 0;
        for (int score : list) {
            sum += score;
        }

        return (double) sum / list.size();
    }

}
