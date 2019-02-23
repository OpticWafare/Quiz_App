package com.github.opticwafare.quiz_app.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RankUser {

    private int rank = -1;
    private String username;
    private String email;
    private int pointsAchieved;
    private Timestamp answeredTime = null;

    public RankUser(int rank, String username, String email, int pointsAchieved, Timestamp answeredTime) {
        this.rank = rank;
        this.username = username;
        this.email = email;
        this.pointsAchieved = pointsAchieved;
        this.answeredTime = answeredTime;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPointsAchieved() {
        return pointsAchieved;
    }

    public void setPointsAchieved(int pointsAchieved) {
        this.pointsAchieved = pointsAchieved;
    }

    public Timestamp getAnsweredTimestamp() {
        return answeredTime;
    }

    public void setAnsweredTimestamp(Timestamp answeredTime) {
        this.answeredTime = answeredTime;
    }

    public String getAnsweredDateAsString() {
        if(getAnsweredTimestamp() == null) {
            return "";
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String stringDate = dateFormat.format(getAnsweredTimestamp());
            return stringDate;
        }
    }

    public String getAnsweredTimeAsString() {
        if(getAnsweredTimestamp() == null) {
            return "";
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String stringTime = dateFormat.format(getAnsweredTimestamp());
            return stringTime;
        }
    }
}