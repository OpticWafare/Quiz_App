package com.github.opticwafare.quiz_app.tabs;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.listener.EditTextIPAddressChangedListener;
import com.github.opticwafare.quiz_app.listener.EditTextPortChangedListener;
//import com.github.opticwafare.hunde_gassi_app.listener.EditTextServerNameChangedListener;
import com.github.opticwafare.quiz_app.servlettasks.SendToServletTask;

public class SettingsTab extends SuperTab {

    private EditText editTextIP;
    private EditText editTextPort;
    private EditText editTextServerName;

    private boolean showLogoutButton = true;

    public SettingsTab(boolean showLogoutButton) {
        super("Einstellungen", R.layout.activity_settings);
        this.showLogoutButton = showLogoutButton;
    }

    @Override
    public void init(MainActivity mainActivity) {

        this.mainActivity = mainActivity;

        editTextIP = (EditText) mainActivity.findViewById(R.id.editTextIP);
        editTextPort = (EditText) mainActivity.findViewById(R.id.editTextPort);
        //editTextServerName = (EditText) mainActivity.findViewById(R.id.editTextServerName);

        editTextIP.setText(SendToServletTask.getServerIPAddress());
        editTextPort.setText(SendToServletTask.getServerPort()+"");
        //editTextServerName.setText(SendToServletTask.getServerName());

        editTextIP.addTextChangedListener(new EditTextIPAddressChangedListener());
        editTextPort.addTextChangedListener(new EditTextPortChangedListener());
        //editTextServerName.addTextChangedListener(new EditTextServerNameChangedListener());

        Button buttonLogout = (Button) mainActivity.findViewById(R.id.button_settings_logout);
        if(showLogoutButton == false) {
            buttonLogout.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isShowLogoutButton() {
        return showLogoutButton;
    }

    public void setShowLogoutButton(boolean showLogoutButton) {
        this.showLogoutButton = showLogoutButton;
    }
}
