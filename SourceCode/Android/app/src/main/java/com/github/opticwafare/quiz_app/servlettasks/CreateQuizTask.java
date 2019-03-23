package com.github.opticwafare.quiz_app.servlettasks;

import android.widget.Toast;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.TimestampLongFormatTypeAdapter;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.tabs.CreateQuizTab;
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

        System.out.println("Constructor CreateQuizTask");
        System.out.println("\tquiz.getName() = " + quiz.getName());

        setServletName("createQuiz");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
                .create();

        String quizJson = gson.toJson(quiz);
        System.out.println("Finished Quiz JSON: "+quizJson);
        int creatorId = creator.getUserid();

        setUrlParameters("quiz=" + quizJson + "&creator="+creatorId);
    }

    @Override
    protected void onPostExecute(String s) {

        System.out.println("CreatedQuizTask - onPostExecute: ");
        System.out.println("\tresponse = " + s);
        Gson gson = new Gson();
        Quiz createdQuiz = gson.fromJson(s, Quiz.class);

        GetQuizzesForUserTask getQuizzesForUserTask = new GetQuizzesForUserTask(mainActivity);
        getQuizzesForUserTask.execute("");

        if(createdQuiz != null) {
            Toast.makeText(mainActivity, "Quiz wurde erstellt!", Toast.LENGTH_SHORT).show();
            User.getLoggedInUser().addCreatedQuiz(createdQuiz);
        }
        else {
            Toast.makeText(mainActivity, "Beim Erstellen des Quiz ist ein Fehler aufgetreten!", Toast.LENGTH_LONG).show();
        }

        System.out.println("CreateQuizTask - onPostExecute: CreateQuizTab am pager adapter setzen und refreshen");
        mainActivity.getPagerAdapter().setTabAt(new CreateQuizTab(), 1); // TODO variabel
        mainActivity.refreshPagerAdapter();
    }
}