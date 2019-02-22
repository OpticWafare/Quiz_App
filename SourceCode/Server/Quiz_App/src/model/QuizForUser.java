package model;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "quizforuser")
@IdClass(QuizForUserId.class)
public class QuizForUser {

	@Id
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	@Id
	@ManyToOne
	@JoinColumn(name = "quizid")
	private Quiz quiz;
	@Column(name  = "answeredtime", nullable = true, unique = false)
	private Timestamp answeredtime;
	
	
	public QuizForUser(User user, Quiz quiz, Timestamp answeredtime) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.answeredtime = answeredtime;
	}
	
	public QuizForUser() {
		
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
