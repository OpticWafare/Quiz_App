package com.github.opticwafare.quiz_app.listener;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.pageradapter.FixedTabsPagerAdapter;
import com.github.opticwafare.quiz_app.tabs.AnswerQuizTab;
import com.github.opticwafare.quiz_app.tabs.QuizTab;

public class QuizPreviewClickedListener implements View.OnClickListener {

    private MainActivity mainActivity;
    private Quiz quiz;
    private boolean forParticipating;

    public QuizPreviewClickedListener(MainActivity mainActivity, Quiz quiz, boolean forParticipating) {
        this.mainActivity = mainActivity;
        this.quiz = quiz;
        this.forParticipating = forParticipating;
    }

    @Override
    public void onClick(View v) {

        System.out.println("QuizPreviewClickedListener - onClick: forParticipating = " + forParticipating);
        if(this.forParticipating == true) {
            // Quiz anzeigen zum Ausfüllen
            AnswerQuizTab answerQuizTab = new AnswerQuizTab(quiz);
            mainActivity.getPagerAdapter().setTabAt(answerQuizTab, 0); // TODO position variabel
            mainActivity.refreshPagerAdapter();
        }
        else {
            // Quiz anzeigen
            QuizTab quizTab = new QuizTab(quiz);
            mainActivity.getPagerAdapter().setTabAt(quizTab, 0); // TODO position variabel
            mainActivity.refreshPagerAdapter();
        }
    }
}
