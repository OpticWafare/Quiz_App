package com.github.opticwafare.quiz_app.elements;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.model.Answer;
import com.github.opticwafare.quiz_app.model.Question;

import org.w3c.dom.Text;

import java.util.List;

public class QuestionElement extends UIElement {

    public QuestionElement() {
        super(R.layout.element_question);
    }

    /**
     * Gibt das Frage-Element zum Erstellen/Bearbeiten der Frage zurück
     * @param layoutInflater
     * @param container
     * @param number
     * @return
     */
    public ViewGroup show_create(LayoutInflater layoutInflater, ViewGroup container, int number) {

        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewNumber = (TextView) layout.findViewById(R.id.textView_question_number);

        textViewNumber.setText(number+". Frage");

        return layout;
    }

    /**
     * Gibt das Frage-Element zum Ausfüllen/Beantworten der Frage zurück
     * @param layoutInflater
     * @param container
     * @param question
     * @param number
     * @return
     */
    public ViewGroup show_participate(LayoutInflater layoutInflater, ViewGroup container, Question question, int number) {

        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewNumber = (TextView) layout.findViewById(R.id.textView_question_number);
        EditText editTextQuestionText = (EditText) layout.findViewById(R.id.editText_question_questionText);

        EditText editText1 = (EditText) layout.findViewById(R.id.editText_question_answer_1);
        EditText editText2 = (EditText) layout.findViewById(R.id.editText_question_answer_2);
        EditText editText3 = (EditText) layout.findViewById(R.id.editText_question_answer_3);
        EditText editText4 = (EditText) layout.findViewById(R.id.editText_question_answer_4);

        textViewNumber.setText(number+". Frage");

        editTextQuestionText.setEnabled(false);
        String fragetext = question.getText();
        editTextQuestionText.setText(fragetext);

        List<Answer> antworten = question.getAnswers();

        editText1.setText(antworten.get(0).getText());
        editText1.setEnabled(false);
        editText2.setText(antworten.get(1).getText());
        editText2.setEnabled(false);
        editText3.setText(antworten.get(2).getText());
        editText3.setEnabled(false);
        editText4.setText(antworten.get(3).getText());
        editText4.setEnabled(false);

        return layout;
    }

    /**
     * Gibt das Frage-Element zum Ansehen der Frage für den Quiz-Ersteller zurück
     * @param layoutInflater
     * @param container
     * @param question
     * @param number
     * @return
     */
    public ViewGroup show_created(LayoutInflater layoutInflater, ViewGroup container, Question question, int number) {

        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewNumber = (TextView) layout.findViewById(R.id.textView_question_number);
        EditText editTextQuestionText = (EditText) layout.findViewById(R.id.editText_question_questionText);


        CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_1);
        CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_2);
        CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_3);
        CheckBox checkBox4 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_4);

        EditText editText1 = (EditText) layout.findViewById(R.id.editText_question_answer_1);
        EditText editText2 = (EditText) layout.findViewById(R.id.editText_question_answer_2);
        EditText editText3 = (EditText) layout.findViewById(R.id.editText_question_answer_3);
        EditText editText4 = (EditText) layout.findViewById(R.id.editText_question_answer_4);

        textViewNumber.setText(number+". Frage");

        editTextQuestionText.setEnabled(false);
        String fragetext = question.getText();
        editTextQuestionText.setText(fragetext);

        System.out.println("QuestionElement: anzeigen");
        System.out.println("Frage: " + question.getText());
        System.out.println("Anzahl Antworten: " + question.getAnswers().size());

        List<Answer> antworten = question.getAnswers();

        editText1.setText(antworten.get(0).getText());
        editText1.setEnabled(false);
        editText2.setText(antworten.get(1).getText());
        editText2.setEnabled(false);
        editText3.setText(antworten.get(2).getText());
        editText3.setEnabled(false);
        editText4.setText(antworten.get(3).getText());
        editText4.setEnabled(false);

        if(antworten.get(0).isCorrect() == true) {
            checkBox1.setChecked(true);
        }
        if(antworten.get(1).isCorrect() == true) {
            checkBox2.setChecked(true);
        }
        if(antworten.get(2).isCorrect() == true) {
            checkBox3.setChecked(true);
        }
        if(antworten.get(3).isCorrect() == true) {
            checkBox4.setChecked(true);
        }

        checkBox1.setEnabled(false);
        checkBox2.setEnabled(false);
        checkBox3.setEnabled(false);
        checkBox4.setEnabled(false);

        return layout;
    }
}