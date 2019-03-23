package com.github.opticwafare.quiz_app.servlettasks;

import android.widget.Toast;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.TimestampLongFormatTypeAdapter;
import com.github.opticwafare.quiz_app.tabs.QuizTab;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;

public class AnswerQuizTask extends SendToServletTask {

    private ArrayList<Integer> chosenAnswerIds;
    private int userid;
    MainActivity mainActivity;

    public AnswerQuizTask(ArrayList<Integer> chosenAnswerIds, int userid, MainActivity mainActivity) {
        this.chosenAnswerIds = chosenAnswerIds;
        this.userid = userid;
        this.mainActivity = mainActivity;

        Gson gson = new Gson();
        String chosenAnswerIdsJson = gson.toJson(chosenAnswerIds);

        setServletName("AnswerQuiz");
        setUrlParameters("userid="+userid+"&answerids="+chosenAnswerIdsJson);
    }

    @Override
    protected void onPostExecute(String s) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
                .create();

        Quiz answeredQuiz = gson.fromJson(s, Quiz.class);

        if(answeredQuiz == null) {
            Toast.makeText(mainActivity, "Beim Beantworten des Quiz ist ein Fehler aufgetreten!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(mainActivity, "Du hast das Quiz beantwortet!", Toast.LENGTH_SHORT).show();
        }

        QuizTab quizTab = new QuizTab(answeredQuiz);
        mainActivity.getPagerAdapter().setTabAt(quizTab, 0); // TODO position variabel
        mainActivity.refreshPagerAdapter();
    }
}
