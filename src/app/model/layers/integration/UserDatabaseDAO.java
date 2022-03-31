package app.model.layers.integration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

import app.model.business.user.User;

public class UserDatabaseDAO implements UserDAO {

	@Override
	public User findUser(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM AV_USERS "
									+ "WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String email = rs.getString("email");
			String password = rs.getString("password");
			
			user = new User(id, name, surname, email, password);
			
			ps.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				
				if (ps != null)
					ps.close();
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	@Override
	public void saveUser(User user) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("INSERT INTO AV_USERS "
									+ "(?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			
			if (user.getEmail().contains("@citis.es"))
				ps.setInt(6, 1);
			else
				ps.setInt(6, 0);
			
			try {
				ps.setBlob(7, new FileInputStream("resources/check.jpg"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				
				if (ps != null)
					ps.close();
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ASUser createUser(User user) {
		return null;
	}
	
	private Connection getConnection() {
		
		return null;
	}

}
