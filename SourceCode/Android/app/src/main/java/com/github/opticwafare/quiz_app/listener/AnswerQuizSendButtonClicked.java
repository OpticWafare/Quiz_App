package com.github.opticwafare.quiz_app.listener;

import android.view.View;

import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.model.User;
import com.github.opticwafare.quiz_app.tabs.AnswerQuizTab;

import java.util.ArrayList;
import java.util.List;

public class AnswerQuizSendButtonClicked implements View.OnClickListener {

    private AnswerQuizTab answerQuizTab;

    public AnswerQuizSendButtonClicked(AnswerQuizTab answerQuizTab) {
        this.answerQuizTab = answerQuizTab;
    }

    @Override
    public void onClick(View v) {

        List<QuestionElement> questionElementList = answerQuizTab.getQuestionElementList();
        ArrayList<Integer> chosenAnswerIds = new ArrayList<Integer>();
        for(int i = 0; i < questionElementList.size(); i++) {
            List<Integer> answers = questionElementList.get(i).getChosenAnswerIds();
            chosenAnswerIds.addAll(answers);
        }

        int userid = User.getLoggedInUser().getUserid();

    }
}
