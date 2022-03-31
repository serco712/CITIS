package app.model.layers.integration;

import app.model.business.user.ASUser;

public interface UserDAO {
	public ASUser findUser(String id);
	public void saveUser(ASUser user);
	public ASUser createUser(ASUser user);
}
