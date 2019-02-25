package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.User;

public class DBManager {

private static DBManager instance;
	
	private String PERSISTENCE_UNIT_NAME = "quiz_app";
	private EntityManagerFactory emFactory = null;
	private EntityManager entityManager = null;
	
	private DBManager ()
	{
		
	}
	
	public String getPERSISTENCE_UNIT_NAME() {
		return PERSISTENCE_UNIT_NAME;
	}

	public void setPERSISTENCE_UNIT_NAME(String pERSISTENCE_UNIT_NAME) {
		PERSISTENCE_UNIT_NAME = pERSISTENCE_UNIT_NAME;
	}

	public static DBManager getInstance ()
	{
		if(instance == null)
		{
			instance = new DBManager();
		}
		return instance;
	}
	
	private EntityManagerFactory getEntityManagerFactory() {
		if(emFactory == null) {
			emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
    	return emFactory;
	}
	
	private EntityManager getEntityManager() {
		if(entityManager == null) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}
	
	//////////////////////////////////
	
	public User loginUserEmail(String email, String password) {
		EntityManager em = getEntityManager();
		TypedQuery<User> typedQuery = em.createQuery("from User u where u.email = :email and u.password = :password", User.class);
		typedQuery.setParameter("email", email);
		typedQuery.setParameter("password", password);
		try {
			User user  = typedQuery.getSingleResult();
			return user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public User loginUserUsername(String username, String password) {
		EntityManager em = getEntityManager();
		TypedQuery<User> typedQuery = em.createQuery("from User u where u.username = :username and u.password = :password", User.class);
		typedQuery.setParameter("username", username);
		typedQuery.setParameter("password", password);
		try {
			User user = typedQuery.getSingleResult();
			return user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public User getUserByEmail(String email)
	{
		EntityManager em = getEntityManager();
		TypedQuery<User> typedQuery = em.createQuery("from User u where u.email = :email", User.class);
		typedQuery.setParameter("email", email);
		try {
			User user = typedQuery.getSingleResult();
			return user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public User getUserByUsername(String username)
	{
		EntityManager em = getEntityManager();
		TypedQuery<User> typedQuery = em.createQuery("from User u where u.username = :username", User.class);
		typedQuery.setParameter("username", username);
		try {
			User user = typedQuery.getSingleResult();
			return user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public void createUser(User user)
	{
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		em.persist(user);
		
		em.getTransaction().commit();
	}
	
	public User getUserByUserid (int userid)
	{
		EntityManager em  = getEntityManager();
		TypedQuery<User> typedQuery = em.createQuery("from User u where u.userid = :userid" , User.class);
		typedQuery.setParameter("userid", userid);
		try {
			User user = typedQuery.getSingleResult();
			return user;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	public List<Quiz> getQuizzesForUser(User user)
	{
		EntityManager em = getEntityManager();
		TypedQuery<Quiz> typedQuery = em.createQuery("from Quiz q left join QuizForUser qfu on(qfu.quiz=q) where q.creator = :user or qfu.user = :user", Quiz.class);
		typedQuery.setParameter("user", user);
		List<Quiz> quizzesForUser = typedQuery.getResultList();
		return quizzesForUser;
	}
	
	public void createQuiz (Quiz quiz)
	{
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(quiz);
		em.getTransaction().commit();
	}

	
	public static void main(String[] args) {
		DBManager dbManager = getInstance();
		User testuser = dbManager.getUserByUsername("Test01");
		User testuser2 = dbManager.getUserByUsername("martin1");
        
		Calendar calendar = Calendar.getInstance();
		Date dateJetzt = calendar.getTime();
        Timestamp timestampJetzt = new Timestamp(dateJetzt.getTime());
		/*
		Quiz q = new Quiz(testuser, "Quiz Test 01", timestampJetzt, timestampJetzt);
		testuser.getCreatedQuizzes().add(q);

        List<Answer> answers1 = new ArrayList<>();
        List<Answer> answers2 = new ArrayList<>();
        
        Question question1 = new Question("Fragetext der 1ten Frage", q);
        Question question2 = new Question("Fragetext der 2ten Frage", q);
        
        answers1.add(new Answer(question1, "Antwort 1", 0, false));
        answers1.add(new Answer(question1, "Antwort 2", 0, false));
        answers1.add(new Answer(question1, "Antwort 3", 1, true));
        answers1.add(new Answer(question1, "Antwort 4", 0, false));
        System.out.println("QuizZTab: liste answers1 hat "+answers1.size()+" antworten");

        answers2.add(new Answer(question2, "Antwort A", 0, false));
        answers2.add(new Answer(question2, "Antwort B", 1, true));
        answers2.add(new Answer(question2, "Antwort C", 0, false));
        answers2.add(new Answer(question2, "Antwort D", 0, false));

        question1.setAnswers(answers1);
        question2.setAnswers(answers2);
        
        q.getQuestions().add(question1);
        q.getQuestions().add(question2);
        
        dbManager.createQuiz(q);*/
        
        calendar.add(Calendar.MINUTE, 10);
        Date dateSpaeter = calendar.getTime();
        Timestamp timestampSpaeter = new Timestamp(dateSpaeter.getTime());
		
		
		List<Quiz> quizzes = dbManager.getQuizzesForUser(testuser);
		
		Quiz testquiz = quizzes.get(0);
		
		/*EntityManager em = dbManager.getEntityManager();
		em.getTransaction().begin();
		
		QuizForUser quizForUser = new QuizForUser(testuser2, testquiz, timestampSpaeter);
		testquiz.getParticipatingUsers().add(quizForUser);
		testuser2.getParticipatedQuizzes().add(quizForUser);
		
		em.getTransaction().commit();*/
		
		System.out.println("anzahl quizze: " + quizzes.size());
		System.out.println("quiz 01: anzahl fragen = " + testquiz.getQuestions().size());
		//System.out.println("quiz 01: anzahl antowrten der ersten frage = " + testquiz.getQuestions().get(0).getAnswers().size());
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(testquiz.getQuestions().get(0));
		System.out.println("1. frage: " + json);
		
		System.out.println("quiz 01: anzahl participating user " + testquiz.getParticipatingUsers().size());
		String json2 = gson.toJson(testquiz.getParticipatingUsers().get(0));
		System.out.println("teilenhmende user: " + json2);
		
		String jsonQuiz = gson.toJson(testquiz);
		System.out.println("quiz json: " + jsonQuiz);
		//System.out.println("quiz 01: participating user 01 " + testquiz.getParticipatingUsers().get(0).getUser());
	}
}