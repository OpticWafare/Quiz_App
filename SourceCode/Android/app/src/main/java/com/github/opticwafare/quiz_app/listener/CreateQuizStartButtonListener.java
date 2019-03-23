package com.github.opticwafare.quiz_app.listener;

import android.view.View;
import android.widget.EditText;

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
        int numberQuestions = Integer.parseInt(editTextNumberQuestions.getText().toString());

        InsertQuestionsTab insertQuestionsTab = new InsertQuestionsTab(quizName, numberQuestions);
        mainActivity.getPagerAdapter().setTabAt(insertQuestionsTab, 1); // TODO tab nummer variabel
        mainActivity.refreshPagerAdapter();
    }
}
