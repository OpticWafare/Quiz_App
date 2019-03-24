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

/**
 * Holt alle Quizze für den eingeloggten User.
 * Diese werden danach in der Quizze-Liste im Quizze-Tab angezeigt.
 * Dort kann der Benutzer ein Quiz auswählen
 *
 * ABLAUF:
 *  1) GetQuizzesForUserTask holt Quizze
 *  2) Quizze werden in der MainActivity gesetzt (setQuizzesForLoggedInUser)
 *  3) In der MainActivity werden sie zwischengespeichert
 *  4) Zusätzlich werden sie lokal am Gerät (in die SharedPreferences) gespeichert
 *  5) Wenn der QuizzeTab gerade angezeigt wird, wird die Quizze-Liste dort gleich angezeigt
 *  6) Sonst verbleibt die Quizze-Liste weiterhin in der MainActivity.
 *  7) Wenn der QuizzeTab irgendwann mal die Quizze Liste braucht, holt er sie von der MainActivity.
 *
 *  Achtung:
 *  Durch Inaktivität (z.B. Minimieren der App) kann es sein,
 *  dass die quizzesForLoggedInUser in der MainActivity "entladen" werden
 *  => Liste ist null oder hat 0 Elemente.
 *  Damit diese nicht wieder mit einem GetQuizzesForUserTask vom Internet/Server geholt werden müssen,
 *  werden sie eben lokal am Gerät (in die SharedPreferences) gespeichert.
 *  Sollten die quizzesForLoggedInUser in der MainActivity "entladen" worden sein,
 *  werden sie von den SharedPreferences wieder geholt
 *
 */
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
        // Quizze-Liste von Response-JSON in ArrayList-Objekt umwandeln
        ArrayList<Quiz> quizze = gson.fromJson(s, listType);

        System.out.println("GetQuizzesForUserTask - onPostExecute: setQuizzesForLoggedInUser");

        // die neuen Quizze anzeigen
        mainActivity.resetQuizzeInQuizzeTab();
        mainActivity.setQuizzesForLoggedInUser(quizze);
    }
}
