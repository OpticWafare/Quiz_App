package com.github.opticwafare.quiz_app.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int userid;
    private String username;
    private String password;
    private String email;
    private String fcmtoken;

    private List<Quiz> createdQuizzes = new ArrayList<Quiz>();
    private List<Answer> chosenAnswers = new ArrayList<Answer>();
    private List<QuizForUser> participatedQuizzes = new ArrayList<QuizForUser>();
    /**
     * Der derzeit eingeloggte User
     */
    private static User loggedInUser;
    /**
     * Hier wird der FCM Token gespeichert, wenn derzeitig kein User angemeldet ist
     * */
    private static String lonelyFcmToken;

    public User(int userid, String username, String password, String email, String fcmtoken) {
        super();
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fcmtoken = fcmtoken;
    }

    public boolean isQuizCreator(Quiz quiz) {

        for(int i = 0; i < createdQuizzes.size(); i++) {
            if(createdQuizzes.get(i).getQuizid() == quiz.getQuizid()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasQuizParticipated(Quiz quiz) {

        for(int i = 0; i < participatedQuizzes.size(); i++) {
            if(participatedQuizzes.get(i).getQuiz().getQuizid() == quiz.getQuizid()) {
                if(participatedQuizzes.get(i).getAnsweredtime() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFcmtoken() {
        return fcmtoken;
    }

    public void setFcmtoken(String fcmtoken) {
        this.fcmtoken = fcmtoken;
    }

    // Static Getter & Setter

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public static String getLonelyFcmToken() {
        return lonelyFcmToken;
    }

    public static void setLonelyFcmToken(String lonelyFcmToken) {
        User.lonelyFcmToken = lonelyFcmToken;
    }

    public List<Quiz> getCreatedQuizzes() {
        return createdQuizzes;
    }

    public void setCreatedQuizzes(List<Quiz> createdQuizzes) {
        this.createdQuizzes = createdQuizzes;
    }

    public void addCreatedQuiz(Quiz createdQuiz) {
        this.createdQuizzes.add(createdQuiz);
    }

    public List<Answer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(List<Answer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }

    public List<QuizForUser> getParticipatedQuizzes() {
        return participatedQuizzes;
    }

    public void setParticipatedQuizzes(List<QuizForUser> participatedQuizzes) {
        this.participatedQuizzes = participatedQuizzes;
    }
}
