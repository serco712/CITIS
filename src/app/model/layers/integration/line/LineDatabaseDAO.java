package app.model.layers.integration.line;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.business.line.DTOLine;

public class LineDatabaseDAO implements LineDAO {

	@Override
	public DTOLine findLine(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOLine line = null;
		
		try {
			// con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM AV_USERS "
									+ "WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			/*user = new DTOUser();
			user.setName(rs.getString("name"));
			user.setSurname(rs.getString("surname"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			*/
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
		
		return null;
	}

	@Override
	public void saveLine(DTOLine line) {
		// TODO Auto-generated method stub
		
	}

}
