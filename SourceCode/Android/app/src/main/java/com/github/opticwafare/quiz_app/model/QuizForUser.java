package com.github.opticwafare.quiz_app.model;

import java.sql.Timestamp;

public class QuizForUser {

    private User user;
    private Quiz quiz;
    private Timestamp answeredtime;

    public QuizForUser(User user, Quiz quiz, Timestamp answeredtime) {
        this.user = user;
        this.quiz = quiz;
        this.answeredtime = answeredtime;
    }

    public boolean hasUserCompletedQuiz() {
        if(getAnsweredtime() != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Timestamp getAnsweredtime() {
        return answeredtime;
    }

    public void setAnsweredtime(Timestamp answeredtime) {
        this.answeredtime = answeredtime;
    }
}
