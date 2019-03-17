package control;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.DBManager;
import model.Quiz;
import model.QuizToQuizShellTypeAdapter;
import model.TimestampLongFormatTypeAdapter;
import model.User;

/**
 * Servlet implementation class AnswerQuiz
 */
@WebServlet("/AnswerQuiz")
public class AnswerQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerQuiz() {
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
		
		System.out.println("=== AnswerQuiz ===");
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		String answeridsJson = request.getParameter("answerids");
		
		System.out.println("Userid: " + userid);
		System.out.println("Answerids (json): " + answeridsJson);
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
		
		List<Integer> answerids = gson.fromJson(answeridsJson, listType);

		DBManager dbManager = DBManager.getInstance();
		dbManager.insertAnsweredQuestions(userid, answerids);
		
		System.out.println("chosen answers inserted");
		
		Quiz quiz = dbManager.getQuizFromAnswerId(answerids.get(0));
		User user = dbManager.getUserByUserid(userid);
		
		System.out.println("Quiz: " + quiz.getQuizid());
		System.out.println("User: " + user.getUserid());
		
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
		
		dbManager.setQuizAnsweredTime(user, quiz, currentTimestamp);
		
		System.out.println("Quiz answered time set");
		System.out.println("Sending back Quiz Shell as response");
		
		Gson gson2 = new GsonBuilder()
				.registerTypeAdapter(Quiz.class, new QuizToQuizShellTypeAdapter())
				.create();
		String quizJson = gson2.toJson(quiz);
		System.out.println("Quiz shell JSON: " + quizJson);
		
		response.getWriter().append(quizJson);
	}
}