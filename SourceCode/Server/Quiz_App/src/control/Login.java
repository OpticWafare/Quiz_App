package control;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.DBManager;
import model.LoginTransfer;
import model.LoginTransferTypeAdapter;
import model.Quiz;
import model.QuizToQuizIdTypeAdapter;
import model.TimestampLongFormatTypeAdapter;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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

		String userCredential = request.getParameter("userCredential");
		String password = request.getParameter("password");
		System.out.println("=== LOGIN ===");
		System.out.println("UserCredential: "+ userCredential);
		System.out.println("Password: "+password);
		
		DBManager db = DBManager.getInstance();
		
		User user = null;
		user = db.loginUserEmail(userCredential, password);
		if(user == null)
		{
			user = db.loginUserUsername(userCredential, password);
		}
		
		LoginTransfer loginTransfer = null;
		if(user != null) {

			loginTransfer = new LoginTransfer(user);
		}
		else {
			
			loginTransfer = new LoginTransfer("E-Mail bzw. Username oder Passwort sind nicht korrekt");
		}

		Gson gson = new GsonBuilder()
				//.excludeFieldsWithoutExposeAnnotation()
				//.registerTypeAdapter(Timestamp.class, new TimestampLongFormatTypeAdapter())
				.registerTypeAdapter(LoginTransfer.class, new LoginTransferTypeAdapter())
				.create();
		String json = gson.toJson(loginTransfer);
		System.out.println("Login user json: " + json);
		response.getWriter().append(json);
	}

}
