package com.github.opticwafare.quiz_app.servlettasks;

import android.widget.Toast;

import com.github.opticwafare.quiz_app.model.LoginTransfer;
import com.github.opticwafare.quiz_app.model.TimestampLongFormatTypeAdapter;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.pageradapter.MainPagerAdapter;
import com.github.opticwafare.quiz_app.tabs.LoginTab;
import com.github.opticwafare.quiz_app.tabs.LoginTab;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

public class LoginTask extends SendToServletTask {

    private LoginTab loginTab;

    /** Username oder Email */
    private String userCredential;
    private String password;

    public LoginTask(LoginTab loginTab) {
        setServletName("Login");
        this.loginTab = loginTab;

        this.userCredential = loginTab.getEditTextUsername().getText().toString();
        System.out.println("User Credential in Login Task: " + userCredential);
        this.password = loginTab.getEditTextPassword().getText().toString();

        setUrlParameters("userCredential="+userCredential+"&password="+password);
    }

    @Override
    protected void onPostExecute(String s) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
                .create();
        LoginTransfer loginTransfer = gson.fromJson(s, LoginTransfer.class);

        if(loginTransfer == null) {
            loginTab.showError("Login fehlgeschlagen");
        }
        else if(loginTransfer.getError() != null) {
            loginTab.showError(loginTransfer.getError());
        }
        else {
            Toast.makeText(loginTab.getMainActivity(), "User erfolgreich eingeloggt!", Toast.LENGTH_SHORT).show();
            User.setLoggedInUser(loginTransfer.getUser());

            loginTab.getEditTextUsername().clearFocus();
            loginTab.getEditTextPassword().clearFocus();

            // Von den Login-Tabs zu den Haupt-Tabs wechseln
            loginTab.getMainActivity().setPagerAdapter(new MainPagerAdapter(loginTab.getMainActivity()));
            // Quizze für die Quizze-Liste laden
            GetQuizzesForUserTask getQuizzesForUserTask = new GetQuizzesForUserTask(loginTab.getMainActivity());
            getQuizzesForUserTask.execute("");
        }
    }
}
