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

import java.util.ArrayList;
import java.util.List;

public class QuestionElement extends UIElement {

    /**
     * Das GUI Objekt, das dieses QuestionElement-Objekt produziert hat
     */
    private ViewGroup layout = null;

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

        this.layout = layout;
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

        this.layout = layout;
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

        this.layout = layout;
        return layout;
    }

    /**
     * Zum Erstellen einer Frage/Quiz
     * @return Question Objekt mit allen Daten die der User eingegeben hat (Fragetext und Antworten)
     */
    public Question getQuestionData() {

        if(layout == null) {
            return null;
        }
        else {

            EditText editTextQuestionText = (EditText) layout.findViewById(R.id.editText_question_questionText);

            CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_1);
            CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_2);
            CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_3);
            CheckBox checkBox4 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_4);

            EditText editText1 = (EditText) layout.findViewById(R.id.editText_question_answer_1);
            EditText editText2 = (EditText) layout.findViewById(R.id.editText_question_answer_2);
            EditText editText3 = (EditText) layout.findViewById(R.id.editText_question_answer_3);
            EditText editText4 = (EditText) layout.findViewById(R.id.editText_question_answer_4);

            Question question = new Question();
            question.setText(editTextQuestionText.getText().toString());

            Answer answer1 = new Answer();
            answer1.setText(editText1.getText().toString());
            answer1.setCorrect(checkBox1.isChecked());
            if(checkBox1.isChecked()) {
                answer1.setPoints(1);
            }
            else {
                answer1.setPoints(0);
            }

            Answer answer2 = new Answer();
            answer2.setText(editText2.getText().toString());
            answer2.setCorrect(checkBox2.isChecked());
            if(checkBox2.isChecked()) {
                answer2.setPoints(1);
            }
            else {
                answer2.setPoints(0);
            }

            Answer answer3 = new Answer();
            answer3.setText(editText3.getText().toString());
            answer3.setCorrect(checkBox3.isChecked());
            if(checkBox3.isChecked()) {
                answer3.setPoints(1);
            }
            else {
                answer3.setPoints(0);
            }

            Answer answer4 = new Answer();
            answer4.setText(editText4.getText().toString());
            answer4.setCorrect(checkBox4.isChecked());
            if(checkBox4.isChecked()) {
                answer4.setPoints(1);
            }
            else {
                answer4.setPoints(0);
            }

            ArrayList<Answer> answers = new ArrayList<Answer>();
            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);
            answers.add(answer4);

            question.setAnswers(answers);

            return question;
        }
    }
}