package com.github.opticwafare.quiz_app.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int questionid;
    private String text;
    //private Quiz quiz;
    private List<Answer> answers = new ArrayList<Answer>();

    public Question() {

    }

    public Question(int questionid, String text, List<Answer> answers) {
        this.questionid = questionid;
        this.text = text;
        this.answers = answers;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
