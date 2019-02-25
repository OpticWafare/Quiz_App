package com.github.opticwafare.quiz_app.servlettasks;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.TimestampLongFormatTypeAdapter;
import com.github.opticwafare.quiz_app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;

public class GetQuizzesForUserTask extends SendToServletTask {

    private MainActivity mainActivity;

    public GetQuizzesForUserTask(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
        setServletName("GetQuizzesForUser");
        setUrlParameters("userid="+ User.getLoggedInUser().getUserid());
    }

    @Override
    protected void onPostExecute(String s) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
                .create();
        Type listType = new TypeToken<ArrayList<Quiz>>(){}.getType();
        ArrayList<Quiz> quizze = gson.fromJson(s, listType);

        mainActivity.setQuizzesForLoggedInUser(quizze);
    }
}
