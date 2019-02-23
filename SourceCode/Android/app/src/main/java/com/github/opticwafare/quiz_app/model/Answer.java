package com.github.opticwafare.quiz_app.model;

public class Answer {

    private int answerid;
    private Question question;
    private String text;
    private int points;
    private boolean correct;

    public Answer() {

    }

    /**
     * Nur zum testen
     * @param text
     * @param correct
     */
    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
