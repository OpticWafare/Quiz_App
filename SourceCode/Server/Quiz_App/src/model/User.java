package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Expose(serialize = true, deserialize = true)
	private int userid;
	@Column(name = "username", nullable = false, unique = true)
	@Expose(serialize = true, deserialize = true)
	private String username;
	@Column(name = "password", nullable = false, unique = false)
	@Expose(serialize = false, deserialize = true)
	private String password;
	@Column(name = "email", nullable = false, unique = true)
	@Expose(serialize = true, deserialize = true)
	private String email;
	@Column(name = "fcmtoken", nullable = false, unique = true)
	@Expose(serialize = true, deserialize = true)
	private String fcmtoken;
	@OneToMany(mappedBy = "creator")
	@Expose(serialize = false, deserialize = true)
	private List<Quiz> createdQuizzes = new ArrayList<Quiz>();
	@ManyToMany(mappedBy = "chosenByUsers", fetch = FetchType.LAZY)
	@Expose(serialize = true, deserialize = true)
	private List<Answer> chosenAnswers = new ArrayList<Answer>();
	@OneToMany(mappedBy = "user")
	@Expose(serialize = false, deserialize = true)
	private List<QuizForUser> participatedQuizzes = new ArrayList<QuizForUser>();
	
	public User(String username, String password, String email, String fcmtoken) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fcmtoken = fcmtoken;
	}
	
	public User() {
		
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


	public List<Quiz> getCreatedQuizzes() {
		return createdQuizzes;
	}


	public void setCreatedQuizzes(List<Quiz> createdQuizzes) {
		this.createdQuizzes = createdQuizzes;
	}
	
	public void addCreatedQuiz(Quiz quiz) {
		this.createdQuizzes.add(quiz);
	}


	public List<Answer> getChosenAnswers() {
		return chosenAnswers;
	}


	public void setChosenAnswers(List<Answer> chosenAnswers) {
		this.chosenAnswers = chosenAnswers;
	}

	public void addChosenAnswer(Answer answer) {
		this.chosenAnswers.add(answer);
	}

	public List<QuizForUser> getParticipatedQuizzes() {
		return participatedQuizzes;
	}


	public void setParticipatedQuizzes(List<QuizForUser> participatedQuizzes) {
		this.participatedQuizzes = participatedQuizzes;
	}
	
	public void addParticipatedQuiz(QuizForUser quizForUser) {
		participatedQuizzes.add(quizForUser);
	}

	
	
}
