package com.example.quizapp_adeline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QustionFragment extends Fragment {

    private TextView questionTextView;


    static   String questionText ;
    static int color;
    public static QustionFragment newInstance(Question qtn) {
        QustionFragment ff = new QustionFragment();
        questionText = qtn.getQuestionText();
        Log.d("testing", "questionText"+questionText);

        color    =qtn.getBckGrndColor();

        Log.d("testing", "icolor"+color);
        return ff;    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionTextView = view.findViewById(R.id.questionTextView);
        questionTextView.setText(questionText);
        questionTextView.setBackgroundResource(color);
        Log.d("testing", "icolor near set bvk grnd "+color);


        return view;
    }


}
