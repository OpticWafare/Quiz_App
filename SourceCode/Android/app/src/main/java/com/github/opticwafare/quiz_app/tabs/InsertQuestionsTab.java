package com.github.opticwafare.quiz_app.tabs;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.listener.InsertQuestionsFinishedButtonListener;
import com.github.opticwafare.quiz_app.model.Question;

import java.util.ArrayList;

public class InsertQuestionsTab extends SuperTab {

    public InsertQuestionsTab() {
        super("Quiz erstellen", R.layout.activity_insertquestions);
    }

    private String quizName;
    private int numberQuestions;

    private ArrayList<QuestionElement> questionElementList;

    public InsertQuestionsTab(String quizName, int numberQuestions) {
        this();
        this.quizName = quizName;
        this.numberQuestions = numberQuestions;
    }

    @Override
    public void init(MainActivity mainActivity) {

        LinearLayout linearLayout = (LinearLayout) mainActivity.findViewById(R.id.linearLayout_insertquestions);

        // LayoutInflater erstellen. Wird verwendet um ein XML-Dokument in eine lauffähige GUI zu verwandeln
        LayoutInflater inflater = LayoutInflater.from(mainActivity);

        questionElementList = new ArrayList<QuestionElement>();

        // Frage-Elemente erstellen (so viele wie der Ersteller des Quiz eingegeben hat)
        for(int i = 0; i < numberQuestions; i++) {
            // Neues Frage-Element erstellen
            QuestionElement questionElement = new QuestionElement();
            // GUI von dem Frage-Element erstellen
            ViewGroup questionElementUI = questionElement.show_create(inflater, linearLayout, i+1);
            // Question Element zur GUI hinzufügen (NACH der Überschrift und VOR dem Button am Ende)
            linearLayout.addView(questionElementUI, i+1);
            questionElementList.add(questionElement);
        }

        Button finishedButton = (Button) mainActivity.findViewById(R.id.button_insertquestions_create);
        finishedButton.setOnClickListener(new InsertQuestionsFinishedButtonListener(quizName, this, mainActivity));
    }

    public ArrayList<QuestionElement> getQuestionElementList() {
        return questionElementList;
    }
}
