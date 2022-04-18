package app.model.layers.integration.user;

import app.model.business.user.DTOUser;

public interface UserDAO {
	public DTOUser findUser(String id);
	public void saveUser(DTOUser user);
}
