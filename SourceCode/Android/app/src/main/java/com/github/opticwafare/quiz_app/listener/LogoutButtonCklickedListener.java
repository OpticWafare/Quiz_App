package com.github.opticwafare.quiz_app.listener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;

public class LogoutButtonCklickedListener implements View.OnClickListener {

MainActivity mainActivity;

    @Override
    public void onClick(View v) {
        Button button = (Button) mainActivity.findViewById(R.id.button_settings_logout);
    }
}
