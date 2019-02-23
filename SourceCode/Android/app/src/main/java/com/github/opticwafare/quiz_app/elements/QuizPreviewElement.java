package com.github.opticwafare.quiz_app.elements;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.MainActivity;
import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.listener.QuizPreviewClickedListener;
import com.github.opticwafare.quiz_app.model.Quiz;

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
        textView1.setText(quiz.getQuestions().size() + " Fragen");

        textView2.setText("Abgeschlossen: "+quiz.getNumberOfCompletedUsers()+" von "+quiz.getParticipatingUsers().size()+" TeilnehmerInnen");

        textView3.setText("Erstellt am "+quiz.getCreationDateAsString()+" um "+quiz.getCreationTimeAsString()+" Uhr");

        layout.setOnClickListener(new QuizPreviewClickedListener(mainActivity, quiz));

        return layout;
    }
}
