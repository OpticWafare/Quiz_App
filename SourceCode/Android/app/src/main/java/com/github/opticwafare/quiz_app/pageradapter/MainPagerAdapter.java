package com.github.opticwafare.quiz_app.pageradapter;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.tabs.CreateQuizTab;
import com.github.opticwafare.quiz_app.tabs.QuizzeTab;
import com.github.opticwafare.quiz_app.tabs.SettingsTab;

public class MainPagerAdapter extends FixedTabsPagerAdapter {

    public MainPagerAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void init() {
        // Tabs hinzufügen
        addTab(new QuizzeTab());
        addTab(new CreateQuizTab());
        addTab(new SettingsTab(true));
    }
}