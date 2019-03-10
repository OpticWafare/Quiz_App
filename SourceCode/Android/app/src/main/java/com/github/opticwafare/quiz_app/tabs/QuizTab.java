package com.github.opticwafare.quiz_app.tabs;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.elements.QuestionElement;
import com.github.opticwafare.quiz_app.elements.RankUserElement;
import com.github.opticwafare.quiz_app.listener.QuizLoadedListener;
import com.github.opticwafare.quiz_app.model.Answer;
import com.github.opticwafare.quiz_app.model.Question;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.RankUser;
import com.github.opticwafare.quiz_app.servlettasks.GetQuizTask;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Zeigt die Daten eines einzigen Quizzes an
 */
public class QuizTab extends SuperTab implements QuizLoadedListener {

    private Quiz quiz;

    private LayoutInflater inflater;

    private TextView textViewName;
    private LinearLayout linearLayoutWrapper;
    private LinearLayout linearLayoutUsers;
    private LinearLayout linearLayoutQuestions;

    public QuizTab(Quiz quiz) {
        super("Quizze", R.layout.activity_quiz);
        this.quiz = quiz;
    }

    @Override
    public void init(MainActivity mainActivity) {

        // LayoutInflater erstellen. Wird verwendet um ein XML-Dokument in eine lauffähige GUI zu verwandeln
        inflater = LayoutInflater.from(mainActivity);

        textViewName = (TextView) mainActivity.findViewById(R.id.textView_quiz_name);
        linearLayoutWrapper = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_quiz);
        linearLayoutUsers = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_quiz_users);
        linearLayoutQuestions = (LinearLayout) mainActivity.findViewById(R.id.linearlayout_quiz_questions);

        textViewName.setText(quiz.getName() + " (Laden...)");

        GetQuizTask getQuizTask = new GetQuizTask(quiz.getQuizid());
        getQuizTask.setQuizLoadedListener(this);
        getQuizTask.execute("");
    }

    public void showData() {

        textViewName.setText(quiz.getName());

        Date dateJetzt = Calendar.getInstance().getTime();
        Timestamp timestampJetzt = new Timestamp(dateJetzt.getTime());

        List<RankUser> rankUsers = new ArrayList<RankUser>();
        rankUsers.add(new RankUser(1, "Test User 1", "test1@email.com", 10, timestampJetzt));
        rankUsers.add(new RankUser(-1, "Test User 2", "tes2t@email.com", 0, null));

        int maxNumberPoints = 10;

        //List<Question> questions = quiz.getQuestions();
        // Test Code Beginn
        List<Question> questions = new ArrayList<Question>();

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("Antwort 1", false));
        answers1.add(new Answer("Antwort 2", false));
        answers1.add(new Answer("Antwort 3", true));
        answers1.add(new Answer("Antwort 4", false));
        System.out.println("QuizZTab: liste answers1 hat " + answers1.size() + " antworten");

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Antwort A", false));
        answers2.add(new Answer("Antwort B", true));
        answers2.add(new Answer("Antwort C", false));
        answers2.add(new Answer("Antwort D", false));

        Question question1 = new Question(0, "Fragetext der 1ten Frage", answers1);
        Question question2 = new Question(1, "Fragetext der 2ten Frage", answers2);

        questions.add(question1);
        questions.add(question2);

        // Test Code Ende

        for (int i = 0; i < rankUsers.size(); i++) {

            RankUserElement rankUserElement = new RankUserElement();
            ViewGroup rankUserView = rankUserElement.show(inflater, linearLayoutUsers, rankUsers.get(i), maxNumberPoints);
            linearLayoutUsers.addView(rankUserView);
        }

        System.out.println("QuizTab: Zeige Fragen an");
        for (int i = 0; i < questions.size(); i++) {

            System.out.println(i + ". Frage: " + questions.get(i).getText());
            System.out.println("Anzahl Antworten: " + questions.get(i).getAnswers().size());
            QuestionElement questionElement = new QuestionElement();
            ViewGroup questionElementView = questionElement.show_created(inflater, linearLayoutQuestions, questions.get(i), (i + 1));
            linearLayoutQuestions.addView(questionElementView);
        }
    }

    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public void onQuizLoaded(Quiz quiz) {
        this.quiz = quiz;
        showData();
    }
}
