package app.model.layers.integration.station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.business.line.ASLine;
import app.model.business.station.DTOStation;
import app.model.business.user.DTOUser;

public class StationDatabaseDAO implements StationDAO {

	@Override
	public DTOStation findStation(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOStation st = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM city_stops "
									+ "WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			st = new DTOStation();
			st.setId(id);
			st.setName(rs.getString("stop_name"));
			String [] aux = rs.getString("stop_lat_lon").split(" ");
			st.setXCoor(Integer.parseInt(aux[0]));
			st.setYCoor(Integer.parseInt(aux[1]));
			st.setCity(rs.getString("stop_city"));
			st.setLines(new ArrayList<ASLine>());
			// TODO find parent_station
			int ttype = Integer.parseInt(id);
			
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

	private Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveStation(DTOStation station) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTOStation createStation(DTOStation station) {
		// TODO Auto-generated method stub
		return null;
	}

}
