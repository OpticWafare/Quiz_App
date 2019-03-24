package control;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Answer;
import model.DBManager;
import model.Question;
import model.Quiz;
import model.SingleQuizToQuizIdTypeAdapter;
import model.TimestampLongFormatTypeAdapter;
import model.User;

/**
 * Servlet implementation class createQuiz
 * 
 * Wenn App-Benutzer ein Quiz erstellt hat
 */
@WebServlet("/createQuiz")
public class createQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		System.out.println("=== CREATE QUIZ ===");
		
		int creator = Integer.parseInt(request.getParameter("creator"));
		request.setCharacterEncoding("UTF-8");
		String quizJson = request.getParameter("quiz");
		System.out.println("Received quiz JSON = " + quizJson);
		
		DBManager dbManager = DBManager.getInstance();
		User user = dbManager.getUserByUserid(creator);
		
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
				.create();
		
		Quiz quiz = gson.fromJson(quizJson, Quiz.class);
		
		// Quiz Daten verfeinern
		
		List<Question> questions = quiz.getQuestions();
		List<Answer> answers;
		for(int i = 0; i < questions.size(); i++) {
			questions.get(i).setQuiz(quiz);
			answers = questions.get(i).getAnswers();
			for(int j = 0; j < answers.size(); j++)
			{
				answers.get(j).setQuestion(questions.get(i));;
			}
		}
		
		quiz.setCreator(user);
		user.addCreatedQuiz(quiz);
		
		dbManager.createQuiz(quiz);
		
		List<User> allUsersExceptCreator = dbManager.getAllUsersExceptCreator(user);
		dbManager.addParticipatingUsersToQuiz(allUsersExceptCreator, quiz);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Quiz.class, new SingleQuizToQuizIdTypeAdapter());
		Gson gsonResponse = gsonBuilder.create();
		
		String jsonResponse = gsonResponse.toJson(quiz);
		System.out.println("createQuiz: Response JSON = " + jsonResponse);
		response.getWriter().append(jsonResponse);
		
		System.out.println("--- createQuiz: Notification an Teilnehmer ---");
	
		long publishTime = quiz.getPublishtime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		long currentTime = c.getTime().getTime();
		
		int tolerance = 30000;
		
		// Soll die Notification JETZT gesendet werden? (Weil das Quiz jetzt gepublished wurde)
		if( (currentTime-tolerance < publishTime)
				&& (currentTime+tolerance > publishTime)) {
			
			String fcmToken;
			for(int i = 0; i < allUsersExceptCreator.size(); i++) {
				
				fcmToken = allUsersExceptCreator.get(i).getFcmtoken();
				try {
					NotificationSender.sendNotification(fcmToken, "Neues Quiz verfügbar!", user.getUsername()+" hat das Quiz "+quiz.getName()+" für dich freigegeben!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
