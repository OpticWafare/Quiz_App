package com.github.opticwafare.quiz_app.pageradapter;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.tabs.SettingsTab;

public class MainPagerAdapter extends FixedTabsPagerAdapter {

    public MainPagerAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void init() {
        // Tabs hinzufügen
        //addTab(new NotificationTab());
        addTab(new SettingsTab());
        //addTab(new ContactsTab());
    }
}