package com.github.opticwafare.quiz_app.listener;

import android.view.View;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.model.Answer;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.servlettasks.AnswerQuizTask;
import com.github.opticwafare.quiz_app.tabs.AnswerQuizTab;

import java.util.ArrayList;
import java.util.List;

public class AnswerQuizSendButtonClicked implements View.OnClickListener {

    private AnswerQuizTab answerQuizTab;
    private MainActivity mainActivity;

    public AnswerQuizSendButtonClicked(AnswerQuizTab answerQuizTab, MainActivity mainActivity) {
        this.answerQuizTab = answerQuizTab;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {


        System.out.println("AnswerQuizSendButtonClicked onClick");
        List<QuestionElement> questionElementList = answerQuizTab.getQuestionElementList();
        System.out.println("QuestionElementList size: " + questionElementList.size());
        ArrayList<Integer> chosenAnswerIds = new ArrayList<Integer>();
        for(int i = 0; i < questionElementList.size(); i++) {
            List<Integer> answers = questionElementList.get(i).getChosenAnswerIds();
            System.out.println("\t "+i+") size of chosen answer ids: " + answers.size());
            chosenAnswerIds.addAll(answers);
        }

        System.out.println("Complete size of chosen answer ids: " + chosenAnswerIds.size());

        int userid = User.getLoggedInUser().getUserid();

        AnswerQuizTask answerQuizTask = new AnswerQuizTask(chosenAnswerIds, userid, mainActivity);
        answerQuizTask.execute("");
    }
}
