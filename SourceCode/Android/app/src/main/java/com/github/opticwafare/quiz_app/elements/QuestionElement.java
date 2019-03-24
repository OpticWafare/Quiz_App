package com.github.opticwafare.quiz_app.elements;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.github.opticwafare.quiz_app.R;
import com.github.opticwafare.quiz_app.model.Answer;
import com.github.opticwafare.quiz_app.model.Question;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Fragen-und-Antwort Block.
 * Beinhaltet 1 Fragetext und 4 Antwort-Texte mit jeweiliger Checkbox
 *
 * Wird zum Erstellen, Ausfüllen und Ansehen (für Creator und Teilnehmer) verwendet.
 * Siehe unterschiedliche "show_" Methoden für unterschiedliche Zwecke
 */
public class QuestionElement extends UIElement {

    /**
     * Das GUI Objekt, das dieses QuestionElement-Objekt produziert hat
     */
    private ViewGroup layout = null;

    private Question question;

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

        this.question = question;

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

        this.question = question;
        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewNumber = (TextView) layout.findViewById(R.id.textView_question_number);
        EditText editTextQuestionText = (EditText) layout.findViewById(R.id.editText_question_questionText);


        CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_1);
        CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_2);
        CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_3);
        CheckBox checkBox4 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_4);

        CheckBox[] checkBoxen = new CheckBox[] {checkBox1, checkBox2, checkBox3, checkBox4};

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

        for(int i = 0; i < antworten.size(); i++) {
            if(antworten.get(i).isCorrect() == true) {
                checkBoxen[i].setChecked(true);
                checkBoxen[i].setBackgroundColor(Color.GREEN);
            }
            else {
                checkBoxen[i].setChecked(false);
                checkBoxen[i].setBackgroundColor(Color.RED);
            }
            checkBoxen[i].setEnabled(true);
            checkBoxen[i].setTextIsSelectable(false);
            checkBoxen[i].setFocusable(false);
            checkBoxen[i].setClickable(false);
            checkBoxen[i].setButtonTintList(ContextCompat.getColorStateList(container.getContext(), R.color.view_answer_checkbox_color_list));
        }

        this.layout = layout;
        return layout;
    }

    /**
     * Zeigt den Frage-und Antwortenblock an für Teilnehmer die bereits teilgenommen haben
     * @param layoutInflater
     * @param container
     * @param question
     * @param number
     * @param chosenAnswers
     * @return
     */
    public ViewGroup show_participated(LayoutInflater layoutInflater, ViewGroup container, Question question, int number, List<Answer> chosenAnswers) {

        System.out.println("--- QUESTION ELEMENT: Show Participated ---");
        this.question = question;
        Gson gson = new Gson();
        String questionJson = gson.toJson(question);
        System.out.println("Question object: " + questionJson);
        String chosenAnswersJson = gson.toJson(chosenAnswers);
        System.out.println("chosen answers: " + chosenAnswersJson);
        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewNumber = (TextView) layout.findViewById(R.id.textView_question_number);
        EditText editTextQuestionText = (EditText) layout.findViewById(R.id.editText_question_questionText);

        CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_1);
        CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_2);
        CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_3);
        CheckBox checkBox4 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_4);

        CheckBox[] checkBoxen = new CheckBox[] {checkBox1, checkBox2, checkBox3, checkBox4};

        EditText editText1 = (EditText) layout.findViewById(R.id.editText_question_answer_1);
        EditText editText2 = (EditText) layout.findViewById(R.id.editText_question_answer_2);
        EditText editText3 = (EditText) layout.findViewById(R.id.editText_question_answer_3);
        EditText editText4 = (EditText) layout.findViewById(R.id.editText_question_answer_4);

        textViewNumber.setText(number+". Frage");

        editTextQuestionText.setEnabled(false);
        String fragetext = question.getText();
        editTextQuestionText.setText(fragetext);

        System.out.println("QuestionElement: anzeigen (für participated)");
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

        for(int i = 0; i < antworten.size(); i++) {
            if(antworten.get(i).isCorrect() == true) {
                checkBoxen[i].setBackgroundColor(Color.GREEN);
            }
            else {
                checkBoxen[i].setBackgroundColor(Color.RED);
            }
            checkBoxen[i].setButtonTintList(ContextCompat.getColorStateList(container.getContext(), R.color.view_answer_checkbox_color_list));
        }

        /*if(antworten.get(0).iscorrect() == true) {
            checkbox1.setchecked(true);
        }
        if(antworten.get(1).iscorrect() == true) {
            checkbox2.setchecked(true);
        }
        if(antworten.get(2).iscorrect() == true) {
            checkbox3.setchecked(true);
        }
        if(antworten.get(3).iscorrect() == true) {
            checkbox4.setchecked(true);
        }*/

        // antworten markieren, die der participated user gewählt hat
        for(int i = 0; i < antworten.size(); i++) {
            checkBoxen[i].setEnabled(true);
            checkBoxen[i].setTextIsSelectable(false);
            checkBoxen[i].setFocusable(false);
            checkBoxen[i].setClickable(false);
            checkBoxen[i].setChecked(false);

            for(int j = 0; j < chosenAnswers.size(); j++) {
                if(antworten.get(i).getAnswerid() == chosenAnswers.get(j).getAnswerid()) {

                    checkBoxen[i].setChecked(true);
                    break;
                }
            }
        }

        this.layout = layout;
        return layout;
    }


    /**
     * zum erstellen einer frage/quiz
     * @return question objekt mit allen daten die der user eingegeben hat (fragetext und antworten)
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

    public List<Integer> getChosenAnswerIds() {

        CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_1);
        CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_2);
        CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_3);
        CheckBox checkBox4 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_4);

        List<Answer> answers = question.getAnswers();
        ArrayList<Integer> chosenAnswerIds = new ArrayList<Integer>();

        if(checkBox1.isChecked()) {
            int answerId = answers.get(0).getAnswerid();
            chosenAnswerIds.add(answerId);
        }
        if(checkBox2.isChecked()) {
            int answerId = answers.get(1).getAnswerid();
            chosenAnswerIds.add(answerId);
        }
        if(checkBox3.isChecked()) {
            int answerId = answers.get(2).getAnswerid();
            chosenAnswerIds.add(answerId);
        }
        if(checkBox4.isChecked()) {
            int answerId = answers.get(3).getAnswerid();
            chosenAnswerIds.add(answerId);
        }

        return chosenAnswerIds;
    }
}