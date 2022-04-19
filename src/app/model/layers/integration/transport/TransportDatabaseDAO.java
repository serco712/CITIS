package app.model.layers.integration.transport;

import java.sql.*;

import app.model.business.TransportType;
import app.model.business.transport.DTOTransport;

public class TransportDatabaseDAO implements TransportDAO {

	@Override
	public DTOTransport findTransport(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOTransport transport = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM transport "
									+ "WHERE id = ?");
			
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			transport = new DTOTransport();
			
			transport.setEnroll(rs.getString("enrollment"));
			transport.setModel(rs.getString("model"));
			
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
		
		return transport;
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTransport(DTOTransport transport) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTOTransport createTransport(DTOTransport transport) {
		// TODO Auto-generated method stub
		return null;
	}

}
