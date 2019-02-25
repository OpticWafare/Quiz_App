package model;

import com.google.gson.annotations.Expose;

/**
 * Ein Objekt aus dieser Klasse wird nach dem Login/Registrieren eines Nutzers an das Endgerät gesendet
 * 
 *  Wenn Fehler aufgetreten sind: Error String Variable ist befüllt
 *  Wenn erfolgeich: User Objekt ist befüllt
 *  
 * @author User
 *
 */
public class LoginTransfer {

	@Expose(serialize = true, deserialize = true)
	private String error;
	@Expose(serialize = true, deserialize = true)
	private User user;
		
	public LoginTransfer(User user) {
		super();
		this.user = user;
	}
	
	public LoginTransfer(String error) {
		super();
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
}