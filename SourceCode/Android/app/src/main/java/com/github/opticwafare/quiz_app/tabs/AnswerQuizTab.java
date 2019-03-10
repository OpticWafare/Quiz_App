package com.github.opticwafare.quiz_app.tabs;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.listener.AnswerQuizSendButtonClicked;
import com.github.opticwafare.quiz_app.listener.QuizLoadedListener;
import com.github.opticwafare.quiz_app.model.Answer;
import com.github.opticwafare.quiz_app.model.Question;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.servlettasks.GetQuizTask;

import java.util.ArrayList;
import java.util.List;

public class AnswerQuizTab extends SuperTab implements QuizLoadedListener {

    private Quiz quiz;
    private List<QuestionElement> questionElementList;

    private LinearLayout linearLayout;
    private LayoutInflater layoutInflater;
    private Button btnSend;
    private TextView textView;

    public AnswerQuizTab(Quiz quiz) {
        super("Quizze", R.layout.activity_answerquiz);
        this.quiz = quiz;
    }

    @Override
    public void init(MainActivity mainActivity) {

        textView = (TextView) mainActivity.findViewById(R.id.textView_answerquiz_title);
        textView.setText(quiz.getName() + " (Laden...)");

        linearLayout = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_answerquiz);

        layoutInflater = LayoutInflater.from(mainActivity);

        questionElementList = new ArrayList<QuestionElement>();

        btnSend = (Button) mainActivity.findViewById(R.id.button_answerquiz_send);
        btnSend.setEnabled(false);
        btnSend.setOnClickListener(new AnswerQuizSendButtonClicked(this, mainActivity));

        GetQuizTask getQuizTask = new GetQuizTask(quiz.getQuizid());
        getQuizTask.setQuizLoadedListener(this);
        getQuizTask.execute("");
    }

    public void showData() {

        textView.setText(quiz.getName());

        List<Question> questions = quiz.getQuestions();
        for(int i = 0; i < questions.size(); i++) {

            QuestionElement questionElement = new QuestionElement();
            ViewGroup questionElementUI = questionElement.show_participate(layoutInflater, linearLayout, questions.get(i), i+1);
            // Question Element zur GUI hinzufügen (NACH der Überschrift und VOR dem Button am Ende)
            linearLayout.addView(questionElementUI, i+1);
            questionElementList.add(questionElement);
        }

        btnSend.setEnabled(true);
    }

    public List<QuestionElement> getQuestionElementList() {
        return questionElementList;
    }

    @Override
    public void onQuizLoaded(Quiz quiz) {
        this.quiz = quiz;
        showData();
    }
}
