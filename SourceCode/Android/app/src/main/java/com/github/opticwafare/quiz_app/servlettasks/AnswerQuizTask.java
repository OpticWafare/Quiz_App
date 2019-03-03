package com.github.opticwafare.quiz_app.servlettasks;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AnswerQuizTask extends SendToServletTask {

    private ArrayList<Integer> chosenAnswerIds;
    private int userid;

    public AnswerQuizTask(ArrayList<Integer> chosenAnswerIds, int userid) {
        this.chosenAnswerIds = chosenAnswerIds;
        this.userid = userid;

        Gson gson = new Gson();
        String chosenAnswerIdsJson = gson.toJson(chosenAnswerIds);

        setServerName("");
        setUrlParameters("userid="+userid+"&answerids="+chosenAnswerIdsJson);
    }
}
