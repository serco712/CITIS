package app.model.layers.integration.user;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.sql.*;

import app.model.business.user.DTOUser;

public class UserDatabaseDAO implements UserDAO {

	@Override
	public DTOUser findUser(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOUser user = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM AV_USERS "
									+ "WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			user = new DTOUser();
			user.setName(rs.getString("name"));
			user.setSurname(rs.getString("surname"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			
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
	public void saveUser(DTOUser user) {
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
	public DTOUser createUser(DTOUser user) {
		return null;
	}
	
	private Connection getConnection() {
		return null;
	}
}
