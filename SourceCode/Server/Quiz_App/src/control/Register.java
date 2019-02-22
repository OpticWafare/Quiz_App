package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DBManager;
import model.LoginTransfer;
import model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		System.out.println("=== REGISTER ===");

		// Parameter holen
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String fcmToken = request.getParameter("fcmToken");

		System.out.println("username: " + username);

		// DATENCHECK

		// Stimmen Passwörter überein?
		if (password.equals(password2) != true) {
			try {
				showError(request, response, "Passwörter stimmen nicht überein");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		// DBManager erzeugen
		DBManager db = DBManager.getInstance();

		// Ist diese E-Mail Adresse bereits registriert?
			if (db.getUserByEmail(email) != null) {
				try {
					showError(request, response, "Diese Email Addresse ist schon registriert!");
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}

		// Ist dieser Username bereits registriert?
			if (db.getUserByUsername(username) != null) {
				showError(request, response, "Dieser Username ist schon registriert!");
				return;
			}

		// DATENCHECK ERFOLGREICH

		// Daten des Users in Objekt zwischenspeichern
		User user = new User(username, password, email, fcmToken);
			// User erstellen
			db.createUser(user);

		// Erstellten User wieder aus DB holen
		User user2 = null;
			user2 = db.getUserByEmail(email);
			//user2 = db.getUserByUsername(user.getUsername());
		
		System.out.println("User2 objekt: " + user2);
		// Wenn das Holen des erstellten Users aus DB erfolgreich war
		if (user2 != null) {

			LoginTransfer loginTransfer = new LoginTransfer(user2);

			// Daten in JSON umwandeln
			Gson gson = new Gson();
			String json = gson.toJson(loginTransfer);
			// Senden
			response.getWriter().append(json);
			return;
		} else {
			try {
				showError(request, response, "User konnte nicht angelegt werden!");
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	
	private void showError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {

		LoginTransfer loginTransfer = new LoginTransfer(errorMessage);
		Gson gson = new Gson();
		String json = gson.toJson(loginTransfer);
		response.getWriter().append(json);
	}
}