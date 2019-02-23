package com.github.opticwafare.quiz_app.pageradapter;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.tabs.InsertQuestionsTab;
import com.github.opticwafare.quiz_app.tabs.LoginTab;
import com.github.opticwafare.quiz_app.tabs.RegisterTab;
import com.github.opticwafare.quiz_app.tabs.SettingsTab;

public class LoginPagerAdapter extends FixedTabsPagerAdapter {

    public LoginPagerAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void init() {

        addTab(new LoginTab());
        addTab(new RegisterTab());
        addTab(new SettingsTab(false));
    }
}