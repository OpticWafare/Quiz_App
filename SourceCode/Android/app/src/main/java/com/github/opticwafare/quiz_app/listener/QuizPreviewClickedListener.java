package com.github.opticwafare.quiz_app.listener;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.pageradapter.FixedTabsPagerAdapter;
import com.github.opticwafare.quiz_app.tabs.QuizTab;

public class QuizPreviewClickedListener implements View.OnClickListener {

    private MainActivity mainActivity;
    private Quiz quiz;

    public QuizPreviewClickedListener(MainActivity mainActivity, Quiz quiz) {
        this.mainActivity = mainActivity;
        this.quiz = quiz;
    }

    @Override
    public void onClick(View v) {

        QuizTab quizTab = new QuizTab(quiz);
        mainActivity.getPagerAdapter().setTabAt(quizTab, 0); // TODO position variabel
        mainActivity.refreshPagerAdapter();
    }
}
