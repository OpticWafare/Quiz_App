package com.github.opticwafare.quiz_app.listener;

import android.view.View;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.model.Question;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.servlettasks.CreateQuizTask;
import com.github.opticwafare.quiz_app.tabs.InsertQuestionsTab;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InsertQuestionsFinishedButtonListener implements View.OnClickListener {

    private String quizName;
    private InsertQuestionsTab insertQuestionsTab;
    private MainActivity mainActivity;

    public InsertQuestionsFinishedButtonListener(String quizName, InsertQuestionsTab insertQuestionsTab, MainActivity mainActivity) {
        System.out.println("Constructor InsertQuestionsFinishedButtonListener");
        this.quizName = quizName;
        System.out.println("\tquizName: " + quizName);
        this.insertQuestionsTab = insertQuestionsTab;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {

        System.out.println("InsertQuestionsFinishedButtonListener - onClick");
        System.out.println("\tquizName: " + quizName);
        Quiz quiz = new Quiz();
        quiz.setName(quizName);

        Calendar calendar = Calendar.getInstance();
        Date dateNow  = calendar.getTime();
        Timestamp timestampNow = new Timestamp(dateNow.getTime());

        quiz.setCreationtime(timestampNow);
        quiz.setPublishtime(timestampNow);

        ArrayList<QuestionElement> questionElementList = insertQuestionsTab.getQuestionElementList();
        ArrayList<Question> questions = new ArrayList<Question>();
        for(int i = 0; i < questionElementList.size(); i++) {
            Question question = questionElementList.get(i).getQuestionData();
            questions.add(question);
        }

        quiz.setQuestions(questions);

        User creator = User.getLoggedInUser();
        CreateQuizTask createQuizTask = new CreateQuizTask(mainActivity, creator, quiz);
        createQuizTask.execute("");
    }
}
