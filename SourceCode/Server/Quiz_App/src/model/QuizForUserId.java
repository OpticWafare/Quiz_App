package model;

import java.io.Serializable;

public class QuizForUserId implements Serializable {

	private int quiz;
	private int user;
	
	public int getQuiz() {
		return quiz;
	}
	public void setQuiz(int quiz) {
		this.quiz = quiz;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	
	public QuizForUserId() {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof QuizForUserId) {
			QuizForUserId obj1 = (QuizForUserId) obj;
			if(this.quiz == obj1.quiz && this.user == obj1.user) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return quiz + user;
	}
}