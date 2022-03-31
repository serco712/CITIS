package app.model.business.user;

import java.util.List;

import app.model.business.station.ASStation;

public class ASUser {
	
	private static final String TYPE_ID = "customer";
	
	private String _id;
	private String _name;
	private String surname;
	private String email;
	private String password;
	private List<ASStation> fav;
	
	public ASUser(String id, String name, String surname, String email, String password) {
		_id = id;
		_name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
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
}
