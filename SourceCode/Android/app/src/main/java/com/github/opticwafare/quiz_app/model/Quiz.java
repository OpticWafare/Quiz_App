package com.github.opticwafare.quiz_app.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int quizid;
    private User creator;
    private String name;
    private Timestamp creationtime;
    private Timestamp publishtime;
    private List<Question> questions =  new ArrayList<Question>();
    private List<QuizForUser> participatingUsers = new ArrayList<QuizForUser>();

    public Quiz(int quizid, User creator, String name, Timestamp creationtime, Timestamp publishtime, List<Question> questions, List<QuizForUser> participatingUsers) {
        this.quizid = quizid;
        this.creator = creator;
        this.name = name;
        this.creationtime = creationtime;
        this.publishtime = publishtime;
        this.questions = questions;
        this.participatingUsers = participatingUsers;
    }

    public Quiz(User creator, String name, Timestamp creationtime, Timestamp publishtime, List<Question> questions, List<QuizForUser> participatingUsers) {
        this.creator = creator;
        this.name = name;
        this.creationtime = creationtime;
        this.publishtime = publishtime;
        this.questions = questions;
        this.participatingUsers = participatingUsers;
    }

    public Quiz() {
    }

    public int getNumberOfCompletedUsers() {
        int counter = 0;
        for(int i = 0; i < participatingUsers.size(); i++) {
            if(participatingUsers.get(i).hasUserCompletedQuiz() == true) {
                counter++;
            }
        }
        return counter;
    }

    public int getQuizid() {
        return quizid;
    }

    public void setQuizid(int quizid) {
        this.quizid = quizid;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreationtime() {
        return creationtime;
    }

    public String getCreationDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String stringDate = dateFormat.format(getCreationtime());
        return stringDate;
    }

    public String getCreationTimeAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String stringTime = dateFormat.format(getCreationtime());
        return stringTime;
    }

    public void setCreationtime(Timestamp creationtime) {
        this.creationtime = creationtime;
    }

    public Timestamp getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Timestamp publishtime) {
        this.publishtime = publishtime;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<QuizForUser> getParticipatingUsers() {
        return participatingUsers;
    }

    public void setParticipatingUsers(List<QuizForUser> participatingUsers) {
        this.participatingUsers = participatingUsers;
    }
}
