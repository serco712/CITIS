package app.model.layers.integration.user;

import app.model.business.user.DTOUser;

public interface UserDAO {
	public DTOUser findUser(int id);
	public void saveUser(DTOUser user);
	public DTOUser createUser(DTOUser user);
	public boolean checkUserData(String email, String password);
	public boolean checkUserExists(String email);
}
