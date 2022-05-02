package app.model.layers.integration.agency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.business.agency.DTOAgency;
import app.model.layers.integration.Conectar;

public class AgencyDatabaseDAO implements AgencyDAO {
	
	private static AgencyDAO instance;
	private Connection con;
	
	private AgencyDatabaseDAO() {
		con = getConnection();
	}
	
	public static AgencyDAO getInstance() {
		if (instance == null)
			instance = new AgencyDatabaseDAO();
		
		return instance;
	}

	@Override
	public DTOAgency findAgency(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOAgency da = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_agency "
									+ "WHERE agency_name = ?;");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			da = new DTOAgency();
			da.setAgencyName(id);
			da.setAgencyUrl(rs.getString("agency_url"));
			
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
		
		return da;
	}

	private Connection getConnection() {
		Conectar c = new Conectar();
		return c.getConnection();
	}
	
}
