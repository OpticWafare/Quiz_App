package com.github.opticwafare.quiz_app.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.tabs.InsertQuestionsTab;

public class CreateQuizStartButtonListener implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextNumberQuestions;
    private MainActivity mainActivity;

    public CreateQuizStartButtonListener(EditText editTextName, EditText editTextNumberQuestions, MainActivity mainActivity) {
        this.editTextName = editTextName;
        this.editTextNumberQuestions = editTextNumberQuestions;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {

        System.out.println("CreateQuizStartButtonListener - onClick");
        String quizName = editTextName.getText().toString();
        System.out.println("\tquizName: " + quizName);
        if(quizName.equals(" ") || quizName.equals("")) {
            Toast.makeText(mainActivity, "Sie müssen einen gültigen Namen für das Quiz eingeben", Toast.LENGTH_SHORT).show();
            return;
        }

        String numberQuestionsInput = editTextNumberQuestions.getText().toString();
        int numberQuestions = 1;
        try {
            numberQuestions = Integer.valueOf(numberQuestionsInput);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(mainActivity, "Sie müssen eine gültige Anzahl an Fragen eingeben", Toast.LENGTH_SHORT).show();
            return;
        }

        if(numberQuestions < 1) {
            Toast.makeText(mainActivity, "Die Anzahl an Fragen muss mindestens 1 betragen", Toast.LENGTH_SHORT).show();
            return;
        }

        InsertQuestionsTab insertQuestionsTab = new InsertQuestionsTab(quizName, numberQuestions);
        mainActivity.getPagerAdapter().setTabAt(insertQuestionsTab, 1); // TODO tab nummer variabel
        mainActivity.refreshPagerAdapter();
    }
}
