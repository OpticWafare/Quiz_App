package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue
	@Expose(serialize = true, deserialize = true)
	private int quizid;
	@ManyToOne
	@JoinColumn(name = "creator", nullable = false)
	@Expose(serialize = true, deserialize = true)
	private User creator;
	@Column(name = "name", nullable = false, unique = false)
	@Expose(serialize = true, deserialize = true)
	private String name;
	@Column(name = "creationtime", nullable = false, unique = false)
	@Expose(serialize = true, deserialize = true)
	private Timestamp creationtime;
	@Column(name = "publishtime", nullable = false, unique = false)
	@Expose(serialize = true, deserialize = true)
	private Timestamp publishtime;
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST)
	@Expose(serialize = true, deserialize = true)
	private List<Question> questions =  new ArrayList<Question>();
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST)
	@Expose(serialize = true, deserialize = true)
	private List<QuizForUser> participatingUsers = new ArrayList<QuizForUser>();
	
	public Quiz(User creator, String name, Timestamp creationtime, Timestamp publishtime) {
		super();
		this.creator = creator;
		this.name = name;
		this.creationtime = creationtime;
		this.publishtime = publishtime;
	}
	
	public Quiz() {
		
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
	
	public void addParticipatingUser(QuizForUser quizForUser) {
		this.participatingUsers.add(quizForUser);
	}
	
}
