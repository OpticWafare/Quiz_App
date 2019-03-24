package control;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Answer;
import model.DBManager;
import model.Quiz;
import model.ShowAnswerTypeAdapter;
import model.TimestampLongFormatTypeAdapter;
import model.User;
import model.UserForQuizDisplayTypeAdapter;

/**
 * Servlet implementation class GetQuiz
 * 
 * Sendet die Daten von dem angegebenen Quiz zurück
 */
@WebServlet("/GetQuiz")
public class GetQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuiz() {
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

		int quizId = Integer.parseInt(request.getParameter("quizid"));
		System.out.println("=== GET QUIZ ===");
		System.out.println("Quiz id: " + quizId);
		
		DBManager dbManager = DBManager.getInstance();
		
		Quiz quiz = dbManager.getQuizForDisplay(quizId);
		
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
				.registerTypeAdapter(User.class, new UserForQuizDisplayTypeAdapter())
				.registerTypeAdapter(Answer.class, new ShowAnswerTypeAdapter())
				.create();
		
		String responseJson = gson.toJson(quiz);
		System.out.println("GetQuiz response JSON: " + responseJson);
		response.getWriter().append(responseJson);
	}

}
