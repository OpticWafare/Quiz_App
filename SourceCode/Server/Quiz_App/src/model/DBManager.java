package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
}

