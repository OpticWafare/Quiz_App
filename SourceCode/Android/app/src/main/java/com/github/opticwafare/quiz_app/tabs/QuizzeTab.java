package com.github.opticwafare.quiz_app.tabs;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuizPreviewElement;
import com.github.opticwafare.quiz_app.model.Question;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.QuizForUser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuizzeTab extends SuperTab {

    public QuizzeTab() {
        super("Quizze", R.layout.activity_quizze);
    }

    @Override
    public void init(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        List<Quiz> quizze = mainActivity.getQuizzesForLoggedInUser();
        if(quizze != null) {
            showQuizze(quizze);
        }
    }

    public void showQuizze(List<Quiz> quizze) {

        //List<Quiz> quizze = new ArrayList<Quiz>();

        // Test Code Beginn
        /*Date dateJetzt = Calendar.getInstance().getTime();
        Timestamp timestampJetzt = new Timestamp(dateJetzt.getTime());

        Quiz testQuiz = new Quiz(null, "Test Quiz", timestampJetzt, timestampJetzt,
                new ArrayList<Question>(), new ArrayList<QuizForUser>());

        quizze.add(testQuiz);*/
        // Test Code Ende

        LinearLayout linearLayout = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_quizze);

        // LayoutInflater erstellen. Wird verwendet um ein XML-Dokument in eine lauffähige GUI zu verwandeln
        LayoutInflater inflater = LayoutInflater.from(mainActivity);

        int numberViews = linearLayout.getChildCount();
        if(numberViews > 1) {

            for(int i = 1; i < numberViews; i++) {
                linearLayout.removeViewAt(i);
            }
        }

        for(int i = 0; i < quizze.size(); i++) {

            QuizPreviewElement quizPreviewElement = new QuizPreviewElement();
            ViewGroup quizPreview = quizPreviewElement.show(inflater, linearLayout, quizze.get(i), mainActivity);
            linearLayout.addView(quizPreview);
        }
    }
}
