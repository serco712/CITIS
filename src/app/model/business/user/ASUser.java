package app.model.business.user;

import java.util.List;

import javax.swing.ImageIcon;

import app.model.business.station.ASStation;
import app.model.layers.integration.user.UserDatabaseDAO;

public class ASUser {
	
	private static final String TYPE_ID = "customer";
	
	private static ASUser instance;
	
	private int _id;
	private String _name;
	private String surname;
	private String email;
	private String password;
	private List<ASStation> fav;
	private ImageIcon photo;
	private Role rol;
	
	private ASUser (DTOUser user) {
		_id = user.getId();
		_name = user.getName();
		surname = user.getSurname();
		email = user.getSurname();
		password = user.getPassword();
		photo = (ImageIcon) user.getPhoto();
		switch(user.getRole()) {
		case 0:
			rol = Role.USER;
			break;
		case 1:
			rol = Role.ADMIN;
			break;
		default:
			rol = Role.GUEST;
			break;
		}
	}
	
	private ASUser() {
		
	}
	
	public static ASUser getInstance() {
		if (instance == null) {
			instance = new ASUser();
		}
		
		return instance;
	}
	
	public static ASUser getInstance(DTOUser user) {
		if(instance == null)
			instance = new ASUser(user);
		
		return instance;
	}
	
	public static void resetInstance() {
		instance = null;
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
	
	public boolean modify_permissions() {
		return rol == Role.ADMIN;
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
	
	private enum Role {
		GUEST, ADMIN, USER;
	}
}
