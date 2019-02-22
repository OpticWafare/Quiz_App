package com.github.opticwafare.quiz_app.tabs;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.model.Question;

public class InsertQuestionsTab extends SuperTab {

    public InsertQuestionsTab() {
        super("Neues Quiz", R.layout.activity_insertquestions);
    }

    @Override
    public void init(MainActivity mainActivity) {

        LinearLayout linearLayout = (LinearLayout) mainActivity.findViewById(R.id.linearLayout_insertquestions);

        // LayoutInflater erstellen. Wird verwendet um ein XML-Dokument in eine lauffähige GUI zu verwandeln
        LayoutInflater inflater = LayoutInflater.from(mainActivity);

        Question question = new Question();

        QuestionElement questionElement = new QuestionElement();
        ViewGroup questionElementUI = questionElement.showCreate(inflater, linearLayout, question, 1);

        linearLayout.addView(questionElementUI);
    }
}
