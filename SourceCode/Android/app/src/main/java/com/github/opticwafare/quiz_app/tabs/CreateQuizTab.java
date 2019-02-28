package com.github.opticwafare.quiz_app.tabs;

import android.widget.Button;
import android.widget.EditText;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.listener.CreateQuizStartButtonListener;

public class CreateQuizTab extends SuperTab {

    public CreateQuizTab() {
        super("Quiz erstellen", R.layout.activity_createquiz);
    }

    @Override
    public void init(MainActivity mainActivity) {


        Button createQuizStartBtn = (Button) mainActivity.findViewById(R.id.button_createquiz_create);
        EditText editTextName = (EditText) mainActivity.findViewById(R.id.editText_createquiz_name);
        EditText editTextNumberQuestions = (EditText) mainActivity.findViewById(R.id.editText_createquiz_numberquestions);

        createQuizStartBtn.setOnClickListener(new CreateQuizStartButtonListener(editTextName, editTextNumberQuestions, mainActivity));
    }
}
