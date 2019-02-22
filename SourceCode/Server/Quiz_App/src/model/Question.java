package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue
	private int questionid;
	@Column(name = "text", nullable = false, unique = false)
	private String text;
	@ManyToOne
	@JoinColumn(name = "quizid", nullable = false)
	private Quiz quiz;
	@OneToMany(mappedBy = "question")
	private List<Answer> answers = new ArrayList<Answer>();
	
	public Question() {
		
	}
	
	public Question(String text, Quiz quiz) {
		super();
		this.text = text;
		this.quiz = quiz;
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
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}
