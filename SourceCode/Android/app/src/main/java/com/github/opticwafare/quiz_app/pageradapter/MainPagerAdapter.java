package com.github.opticwafare.quiz_app.pageradapter;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.tabs.CreateQuizTab;
import com.github.opticwafare.quiz_app.tabs.QuizTab;
import com.github.opticwafare.quiz_app.tabs.QuizzeTab;
import com.github.opticwafare.quiz_app.tabs.SettingsTab;

public class MainPagerAdapter extends FixedTabsPagerAdapter {

    public MainPagerAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    private QuizzeTab quizzeTab;

    @Override
    protected void init() {
        // Tabs hinzufügen
        quizzeTab = new QuizzeTab();
        addTab(quizzeTab);
        addTab(new CreateQuizTab());
        addTab(new SettingsTab(true));
    }

    public QuizzeTab getQuizzeTab() {
        return quizzeTab;
    }
}