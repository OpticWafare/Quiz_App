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
import com.github.opticwafare.quiz_app.model.Question;

import org.w3c.dom.Text;

public class QuestionElement extends UIElement {

    public QuestionElement() {
        super(R.layout.element_question);
    }

    public ViewGroup showCreate(LayoutInflater layoutInflater, ViewGroup container, Question question, int number) {

        ViewGroup layout = super.show(layoutInflater, container);

        TextView textViewNumber = (TextView) layout.findViewById(R.id.textView_question_number);
        EditText editTextQuestionText = (EditText) layout.findViewById(R.id.editText_question_questionText);
        CheckBox checkBox1 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_1);
        CheckBox checkBox2 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_2);
        CheckBox checkBox3 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_3);
        CheckBox checkBox4 = (CheckBox) layout.findViewById(R.id.checkBox_question_answer_4);

        textViewNumber.setText(number+". Frage");
        editTextQuestionText.setEnabled(true);

        //Editable editable = new SpannableStringBuilder("Antowort EDITABLE");

        Editable.Factory editableFactory = Editable.Factory.getInstance();
        checkBox1.setEditableFactory(editableFactory);

        return layout;
    }

}
