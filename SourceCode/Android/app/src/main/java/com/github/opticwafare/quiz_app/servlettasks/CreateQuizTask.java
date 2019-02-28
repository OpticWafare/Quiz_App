package com.github.opticwafare.quiz_app.servlettasks;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.TimestampLongFormatTypeAdapter;
import com.github.opticwafare.quiz_app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

public class CreateQuizTask extends SendToServletTask {

    private MainActivity mainActivity;
    private User creator;
    private Quiz quiz;

    public CreateQuizTask(MainActivity mainActivity, User creator, Quiz quiz) {
        this.mainActivity = mainActivity;
        this.creator = creator;
        this.quiz = quiz;

        setServletName("createQuiz");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
                .create();

        String quizJson = gson.toJson(quiz);
        System.out.println("Finished Quiz JSON: "+quizJson);
        int creatorId = creator.getUserid();

        setUrlParameters("quiz=" + quizJson + "&creator="+creatorId);
    }
}