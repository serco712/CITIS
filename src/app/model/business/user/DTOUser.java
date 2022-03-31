package app.model.business.user;

import java.util.List;

import app.model.business.station.ASStation;

public class DTOUser {
	
	private String _id;
	
	private String _name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private List<ASStation> fav;
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<ASStation> getFavs() {
		return fav;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setSurname(String surn) {
		surname = surn;
	}
	
	public void setEmail(String ema) {
		email = ema;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public void setFavorites(List<ASStation> f) {
		fav = f;
	}
}
