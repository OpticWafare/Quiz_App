package control;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.DBManager;
import model.Quiz;
import model.TimestampLongFormatTypeAdapter;
import model.User;

/**
 * Servlet implementation class GetQuizzesForUser
 * 
 * Holt alle Quizze, die der User zum Anzeigen in der Quizze-Liste benötigt.
 * (die Quizze die er erstellt hat und die bei denen er teilnimmt)
 */
@WebServlet("/GetQuizzesForUser")
public class GetQuizzesForUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuizzesForUser() {
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
		
		System.out.println("=== Get Quizzes for User ===");
		int userid = Integer.parseInt(request.getParameter("userid"));
		DBManager dbmanager = DBManager.getInstance();
		User user = dbmanager.getUserByUserid(userid);
		
		List<Quiz> quizze = dbmanager.getQuizzesForUser(user);
		
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
				.create();
		String json = gson.toJson(quizze);
		
		System.out.println("JSON: "+json);
		response.getWriter().append(json);
	}

}
