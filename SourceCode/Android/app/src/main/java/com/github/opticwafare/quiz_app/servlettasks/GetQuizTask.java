package com.github.opticwafare.quiz_app.servlettasks;

import com.github.opticwafare.quiz_app.listener.QuizLoadedListener;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.TimestampLongFormatTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Task um das Quiz für den QuizTab bzw. den AnswerQuizTab zu bekommen
 */
public class GetQuizTask extends SendToServletTask {

    private QuizLoadedListener quizLoadedListener;
    private int quizId;

    public GetQuizTask(int quizId) {
        this.quizId = quizId;
        setServletName("GetQuiz");
        setUrlParameters("quizid="+quizId);
    }

    @Override
    protected void onPostExecute(String s) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
                .create();

        Quiz quiz = gson.fromJson(s, Quiz.class);
        if(quizLoadedListener != null) {
            quizLoadedListener.onQuizLoaded(quiz);
        }
    }

    public QuizLoadedListener getQuizLoadedListener() {
        return quizLoadedListener;
    }

    public void setQuizLoadedListener(QuizLoadedListener quizLoadedListener) {
        this.quizLoadedListener = quizLoadedListener;
    }
}
