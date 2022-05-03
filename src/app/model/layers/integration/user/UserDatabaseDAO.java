package app.model.layers.integration.user;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.sql.*;

import app.model.business.user.DTOUser;
import app.model.layers.integration.Conectar;

public class UserDatabaseDAO implements UserDAO {
	
	private static UserDAO instance;
	private Connection con;
	
	private UserDatabaseDAO() {
		con = getConnection();
	}
	
	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDatabaseDAO();
		
		return instance;
	}

	@Override
	public DTOUser findUser(String mail) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOUser user = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_users "
									+ "WHERE email = ?;");
			ps.setString(1, mail);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			user = new DTOUser();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setSurname(rs.getString("surname"));
			user.setEmail(rs.getString("email"));
			if(user.getEmail().endsWith("@citis.es"))
				user.setRole(1);
			else
				user.setRole(0);
			user.setPassword(rs.getString("password"));
			user.setBlob(rs.getBlob("photo"));
			
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
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	@Override
	public void saveUser(DTOUser user) {
		DTOUser u = findUser(user.getEmail());
		if(u == null)
			createUser(user);

		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("UPDATE citis_users "
									+ "SET name = ?, surname = ?, email = ?, password = ?, rol = ?, photo = ? "
									+ "WHERE id = ?;");
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getRole());
			ps.setBlob(6, user.getPhoto());
			ps.setInt(7, user.getId());
			
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
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public DTOUser createUser(DTOUser user) {
		DTOUser u = findUser(user.getEmail());
		if(u != null)
			return u;
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO citis_users "
									+ "VALUES (?, ?, ?, ?, ?, ?, ?);");
			
			ps.setInt(1, 0);
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			
			if (user.getEmail().endsWith("@citis.es"))
				ps.setInt(6, 1);
			else
				ps.setInt(6, 0);
			
			try {
				ps.setBlob(7, new FileInputStream("resources/profileImg.png"));
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
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	private Connection getConnection() {
		Conectar c = new Conectar();
		return c.getConnection();
	}

	@Override
	public boolean checkUserData(String email, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pas = "";
		
		try {
			ps = con.prepareStatement("SELECT password "
									+ "FROM citis_users "
									+ "WHERE email = ?;");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return false;
			
			pas = rs.getString("password");
			
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
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return password.contentEquals(pas);
	}

	@Override
	public boolean checkUserExists(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_users "
									+ "WHERE email = ?;");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return false;
			
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
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
