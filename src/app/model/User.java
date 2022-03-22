package app.model;

public class User {
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private static final String TYPE_ID = "customer";
	
	public User (String name, String surname, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public String getUser () {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}

	public String getTypeId() {
		return TYPE_ID;
	}
}
