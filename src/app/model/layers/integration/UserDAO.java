package app.model.layers.integration;

import app.model.business.user.User;

public interface UserDAO {
	public User findUser(String id);
	public void saveUser(User user);
	public User createUser(User user);
}
