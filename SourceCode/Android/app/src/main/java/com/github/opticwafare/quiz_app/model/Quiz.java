package com.github.opticwafare.quiz_app.model;

import android.support.annotation.NonNull;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Comparable<Quiz> {

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

    /**
     *
     * @return Anzahl der Teilnehmer die das Quiz schon ausgefüllt haben
     */
    public int getNumberOfCompletedUsers() {
        int counter = 0;
        for(int i = 0; i < participatingUsers.size(); i++) {
            if(participatingUsers.get(i).hasUserCompletedQuiz() == true) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return Alle RankUser Objekte fixfertig für die GUI Darstellung
     */
    public List<RankUser> getRankUsers() {

        List<RankUser> rankUsers = new ArrayList<RankUser>();
        for(int i = 0; i < participatingUsers.size(); i++) {
            QuizForUser quizForUser = participatingUsers.get(i);
            User user = quizForUser.getUser();

            System.out.println("getRankUsers: User " + user.getUserid());
            System.out.println("\tChosen answers size before: " + user.getChosenAnswers().size());
            List<Answer> chosenAnswers = getChosenAnswerObjects(user.getChosenAnswers());
            user.setChosenAnswers(chosenAnswers);
            System.out.println("\tChosen answers size after: " + user.getChosenAnswers().size());

            int points = getPointsFromParticipatingUser(quizForUser);

            RankUser rankUser = new RankUser(user.getUsername(),
                    user.getEmail(),
                    points,
                    quizForUser.getAnsweredtime());

            rankUsers.add(rankUser);
        }

        rankUsers = sortRankUsers(rankUsers);
        rankUsers = rankRankUsers(rankUsers);

        return rankUsers;
    }

    /**
     * Sortiert die angegebenen RankUsers absteigend nach den erhaltenen Punkten
     * @param rankUsers
     * @return
     */
    public List<RankUser> sortRankUsers(List<RankUser> rankUsers) {

        for(int i = 0; i < rankUsers.size()-1; i++){
            for(int j = 0; j < rankUsers.size()-i-1; j++) {

                if( (rankUsers.get(j).hasAnsweredQuiz() == true) && (rankUsers.get(j+1).hasAnsweredQuiz() == true)) {
                    if((rankUsers.get(j).getPointsAchieved() < rankUsers.get(j + 1).getPointsAchieved())) {

                        swapRankUsers(rankUsers, j, j+1);
                    }
                }
                else {
                    if( (rankUsers.get(j).hasAnsweredQuiz() == false) && (rankUsers.get(j + 1).hasAnsweredQuiz() == true) ) {

                        swapRankUsers(rankUsers, j, j+1);
                    }
                }
            }
        }

        return rankUsers;
    }

    /**
     * Vergibt Ränge zu den angegebenen RankUsern
     * VORAUSSETZUNG: Rank User sind bereits nach den Punkten absteigend sortiert!
     * @param rankUsers
     * @return
     */
    public List<RankUser> rankRankUsers(List<RankUser> rankUsers) {

        int currentRank = 1;
        for(int i = 0; i < rankUsers.size(); i++) {

            // Wenn der RankUser das Quiz noch nicht ausgefüllt hat -> ein '?' anstatt des Ranges anzeigen.
            // (Rank = -1 wird in der GUI als '?' angezeigt)
            if(rankUsers.get(i).hasAnsweredQuiz() == false) {
                rankUsers.get(i).setRank(-1);
            }
            else {
                rankUsers.get(i).setRank(currentRank);

                if(i < rankUsers.size()-1) {
                    // Wenn zwei Leute hintereinander die gleiche Punktezahl haben, bekommen sie den gleichen Rank
                    if(rankUsers.get(i).getPointsAchieved() != rankUsers.get(i+1).getPointsAchieved()) {
                        currentRank++;
                    }
                }
            }
        }
        return rankUsers;
    }

    /**
     * Tauscht die Position des ersten- und zweiten angegebenen RankUser in der angegebenen Liste
     * @param rankUsers
     * @param index1 Index des ersten RankUsers
     * @param index2 Index des zweiten Rank users
     */
    private void swapRankUsers(List<RankUser> rankUsers, int index1, int index2) {

        RankUser temp = rankUsers.get(index1);
        rankUsers.set(index1, rankUsers.get(index2));
        rankUsers.set(index2, temp);
    }

    /**
     *
     * @return Alle Antworten aus allen Fragen des Quiz
     */
    public List<Answer> getAllAnswers() {

        ArrayList<Answer> allAnswers = new ArrayList<Answer>();
        for(int i = 0; i < questions.size(); i++) {
            List<Answer> answersOfQuestion = questions.get(i).getAnswers();
            allAnswers.addAll(answersOfQuestion);
        }
        return allAnswers;
    }

    /**
     * @param chosenAnswers
     * @return Anzahl der Punkte die man erreicht hat wenn man die angegebenen Antworten gewählt hat
     */
    public int getPointsFromChosenAnswers(List<Answer> chosenAnswers) {

        int points = 0;
        System.out.println("getPointsFromChosenAnswers: chosenAnswers size: " + chosenAnswers.size());
        for(int i = 0; i < chosenAnswers.size(); i++) {
            System.out.println("\tPoints of chosenAnswer "+i+": "+chosenAnswers.get(i).getPoints());
            points = points + chosenAnswers.get(i).getPoints();
        }
        System.out.println("getPointsFromChosenAnswers: Points = " + points);
        return points;
    }

    public int getPointsFromParticipatingUser(QuizForUser quizForUser) {

        List<Answer> chosenAnswers = quizForUser.getUser().getChosenAnswers();
        int points = getPointsFromChosenAnswers(chosenAnswers);
        System.out.println("User " + quizForUser.getUser().getUserid() + " (" + quizForUser.getUser().getUsername()+") has " + points + " points");

        // TODO Hier Zeitbomben modus einbauen

        return points;
    }

    /**
     *
     * @param chosenAnswerIds Liste aus Antworten, die von einem User gewählt worden sind. Diese Antwort-Objekte enthalten aber nur die Antwort ID
     * @return Liste aus Antworten, die von einem User gewählt worden sind ("komplette" Objekte, nicht nur Antowort-Ids)
     */
    public List<Answer> getChosenAnswerObjects(List<Answer> chosenAnswerIds) {

        // Alle Antworten dieses Quiz holen
        // TODO performance: zwischenspeichern
        List<Answer> allAnswers = getAllAnswers();

        for(int i = 0; i < chosenAnswerIds.size(); i++) {
            Answer chosenAnswerId = chosenAnswerIds.get(i);
            // Die gewählte Antwort in allen Antworten des Quiz finden
            for(int j = 0; j < allAnswers.size(); j++) {
                Answer answer = allAnswers.get(j);
                if(chosenAnswerId.getAnswerid() == answer.getAnswerid()) {
                    chosenAnswerIds.set(i, answer);
                    break;
                }
            }
        }
        return chosenAnswerIds;
    }

    /**
     *
     * @return Gibt die maximale Punkteanzahl zurück, die man in diesem Quiz erreichen kann
     */
    public int getMaxNumberOfPoints() {

        // TODO performance: zwischenspeichern (bzw. bei allAnswers List erstellen punkte berechnen)
        int points = 0;
        List<Answer> allAnswers = getAllAnswers();
        for(int i = 0; i < allAnswers.size(); i++) {
            if(allAnswers.get(i).getPoints() > 0) {
                points = points + allAnswers.get(i).getPoints();
            }
        }
        return points;
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

    /**
     * Verwendet zum Sortieren der Quizze (nach Veröffentlichungszeit)
     * TODO abfangen von getPublishtime() = null
     * @param otherQuiz
     * @return
     */
    @Override
    public int compareTo(@NonNull Quiz otherQuiz) {
        long thisTime = this.publishtime.getTime();
        long otherTime = otherQuiz.getPublishtime().getTime();
        long difference = thisTime - otherTime;
        return (int) difference;
    }
}
