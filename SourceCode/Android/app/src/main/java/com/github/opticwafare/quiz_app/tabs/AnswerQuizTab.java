package com.github.opticwafare.quiz_app.tabs;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.model.Answer;
import com.github.opticwafare.quiz_app.model.Question;
import com.github.opticwafare.quiz_app.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AnswerQuizTab extends SuperTab {

    private Quiz quiz;
    private List<QuestionElement> questionElementList;

    public AnswerQuizTab(Quiz quiz) {
        super("Quizze", R.layout.activity_answerquiz);
        this.quiz = quiz;
    }

    @Override
    public void init(MainActivity mainActivity) {

        TextView textView = (TextView) mainActivity.findViewById(R.id.textView_answerquiz_title);
        textView.setText(quiz.getName());

        LinearLayout linearLayout = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_answerquiz);

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);

        questionElementList = new ArrayList<QuestionElement>();
        List<Question> questions = quiz.getQuestions();
        for(int i = 0; i < questions.size(); i++) {

            QuestionElement questionElement = new QuestionElement();
            ViewGroup questionElementUI = questionElement.show_participate(layoutInflater, linearLayout, questions.get(i), i+1);
            // Question Element zur GUI hinzufügen (NACH der Überschrift und VOR dem Button am Ende)
            linearLayout.addView(questionElementUI, i+1);
            questionElementList.add(questionElement);
        }
    }

    public List<QuestionElement> getQuestionElementList() {
        return questionElementList;
    }
}
