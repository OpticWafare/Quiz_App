package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue
	private int answerid;
	@ManyToOne
	@JoinColumn(name = "questionid", nullable = false)
	private Question question;
	@Column(name = "text", nullable = false, unique = false)
	private String text;
	@Column(name = "points", nullable = false, unique = false)
	private int points;
	@Column(name = "correct", nullable  = false, unique = false)
	private boolean correct;
	
	@ManyToMany
	@JoinTable(
			name = "chosenanswer",
			joinColumns = {@JoinColumn(name = "answerid", referencedColumnName="answerid")},
			inverseJoinColumns = {@JoinColumn(name = "userid", referencedColumnName="userid")}
			)
	private List<User> chosenByUsers = new ArrayList<User>();
	
	public Answer(Question question, String text, int points, boolean correct) {
		super();
		this.question = question;
		this.text = text;
		this.points = points;
		this.correct = correct;
	}
	
	public Answer() {
		
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
