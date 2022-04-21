package app.model.business.user;

import java.util.List;

import javax.swing.ImageIcon;

import app.model.business.station.ASStation;
import app.model.layers.integration.user.UserDatabaseDAO;

public class ASUser {
	
	private static final String TYPE_ID = "customer";
	
	private int _id;
	private String _name;
	private String surname;
	private String email;
	private String password;
	private List<ASStation> fav;
	private ImageIcon photo;
	
	public ASUser (DTOUser user) {
		_id = user.getId();
		_name = user.getName();
		surname = user.getSurname();
		email = user.getSurname();
		password = user.getPassword();
		photo = (ImageIcon) user.getPhoto();
	}
	
	public ASUser() {
		
	}

	public int getId() {
		return _id;
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public String getEmail () {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getSurname() {
		return surname;
	}

	public String getTypeId() {
		return TYPE_ID;
	}
	
	public ImageIcon getPhoto() {
		return photo;
	}
	
	public boolean checkUserDataExists(String email, String password) {
		UserDatabaseDAO dao = new UserDatabaseDAO();
		return dao.checkUserData(email, password);
	}
	
	public DTOUser createUser(DTOUser user) {
		UserDatabaseDAO dao = new UserDatabaseDAO();
		return dao.createUser(user);
	}

	public boolean checkUserExists(String userEmail) {
		UserDatabaseDAO dao = new UserDatabaseDAO();
		return dao.checkUserExists(userEmail);
	}
}
