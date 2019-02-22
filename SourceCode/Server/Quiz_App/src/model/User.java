package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private int userid;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "fcmtoken", nullable = false, unique = true)
	private String fcmtoken;
	@OneToMany(mappedBy = "creator")
	private List<Quiz> createdQuizzes = new ArrayList<Quiz>();
	@ManyToMany(mappedBy = "chosenByUsers")
	private List<Answer> chosenAnswers = new ArrayList<Answer>();
	@OneToMany(mappedBy = "user")
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
