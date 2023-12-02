package com.example.quizapp_adeline;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ChooseQuestionsDialogFragment {


    public static class chooseQuestionsDialogFragment extends DialogFragment {

        public interface ChooseQuestionsDialogListener {
            void onQuestionsChosen(int numberOfQuestions);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Number of Questions");

            // Create a RadioGroup
            RadioGroup radioGroup = new RadioGroup(getActivity());
            radioGroup.setOrientation(RadioGroup.VERTICAL);

            // Create the RadioButtons
            RadioButton radioButton5 = new RadioButton(getActivity());
            radioButton5.setText("5 Questions");
           // radioButton5.setId(5);

            RadioButton radioButton10 = new RadioButton(getActivity());
            radioButton10.setText("10 Questions");
           // radioButton10.setId(10);

            // Add RadioButtons to the RadioGroup
            radioGroup.addView(radioButton5);
            radioGroup.addView(radioButton10);

            builder.setView(radioGroup);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    if (selectedId != -1) {
                        RadioButton selectedRadioButton = radioGroup.findViewById(selectedId);
                        int numberOfQuestions = Integer.parseInt(selectedRadioButton.getText().toString().split(" ")[0]);
                        ChooseQuestionsDialogListener listener = (ChooseQuestionsDialogListener) getActivity();
                        listener.onQuestionsChosen(numberOfQuestions);
                    }
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Cancel the dialog
                }
            });

            return builder.create();
        }
    }

}
