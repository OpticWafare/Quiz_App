package com.github.opticwafare.quiz_app.elements;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.listener.QuizPreviewClickedListener;
import com.github.opticwafare.quiz_app.model.Quiz;
import com.github.opticwafare.quiz_app.model.User;

/**
 * Zeigt kurzinfos über ein Quiz.
 * Klickt man drauf öffnet sich das Quiz (zum Ansehen oder ausfüllen)
 *
 * Wird verwendet in QuizzeTab
 */
public class QuizPreviewElement extends UIElement {

    public QuizPreviewElement() {
        super(R.layout.element_quizpreview);
    }

    public ViewGroup show(LayoutInflater layoutInflater, ViewGroup container, Quiz quiz, MainActivity mainActivity) {
        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewName = (TextView) layout.findViewById(R.id.textView_quizpreview_name);
        TextView textView1 = (TextView) layout.findViewById(R.id.textView_quizpreview_1);
        TextView textView2 = (TextView) layout.findViewById(R.id.textView_quizpreview_2);
        TextView textView3 = (TextView) layout.findViewById(R.id.textView_quizpreview_3);

        textViewName.setText(quiz.getName());
        String numberOfQuestionsText = quiz.getQuestions().size() + " Frage";
        if(quiz.getQuestions().size() > 1) {
            numberOfQuestionsText += "n";
        }
        textView1.setText(numberOfQuestionsText);

        String completedUsersText = "Abgeschlossen: "+quiz.getNumberOfCompletedUsers()+" von "+quiz.getParticipatingUsers().size()+" TeilnehmerIn";
        if(quiz.getParticipatingUsers().size() > 1) {
            completedUsersText += "nen";
        }
        textView2.setText(completedUsersText);

        textView3.setText("Erstellt am "+quiz.getCreationDateAsString()+" um "+quiz.getCreationTimeAsString()+" Uhr");


        boolean hasQuizParticipated = User.getLoggedInUser().hasQuizParticipated(quiz);
        boolean isQuizCreator = User.getLoggedInUser().isQuizCreator(quiz);
        boolean forParticipating;
        if(!isQuizCreator && !hasQuizParticipated) {
            forParticipating = true;
        }
        else {
            forParticipating = false;
        }

        layout.setOnClickListener(new QuizPreviewClickedListener(mainActivity, quiz, forParticipating));

        return layout;
    }
}
