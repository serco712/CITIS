package app.model.layers.integration.specifictrip;


import java.sql.*;

import app.model.business.trip.DTOSpecificTrip;

public class SpecificTripDatabaseDAO implements SpecificTripDAO {
	
	private static SpecificTripDAO instance;
	private Connection con;
	
	private SpecificTripDatabaseDAO() {
		con = getConnection();
	}
	
	public static SpecificTripDAO getInstance() {
		if (instance == null)
			instance = new SpecificTripDatabaseDAO();
		
		return instance;
	}
	
	@Override
	public DTOSpecificTrip findSpecificTrip(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOSpecificTrip sTrip = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_specific_trip "
									+ "WHERE specific_trip_id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			sTrip = new DTOSpecificTrip();
			/*
			sTrip.setId(id);
			sTrip.setTime(rs.getTimestamp("departure"));
			sTrip.setCalendar(rs.getString("calendar"));
			sTrip.setTripId(rs.getString("trip_id"));
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
		
		return sTrip;
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSpecificTrip(DTOSpecificTrip sTrip) {
		if (!createSpecificTrip(sTrip).equals(sTrip)) {
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = getConnection();
				ps = con.prepareStatement("UPDATE citis_trip "
										+ "SET calendar_id = ?, route_id = ?, departure = ?, n_route = ?, trip_id = ? "
										+ "WHERE specific_trip_id = ?");
				/*
				ps.setString(1, sTrip.getCalendar());
				ps.setTimestamp(2, sTrip.getDepartureTime());
				ps.setString(3, sTrip.getLineName());
				ps.setString(4, sTrip.getTripId());
				*/
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
	}

	
	
	@Override
	public DTOSpecificTrip createSpecificTrip(DTOSpecificTrip sTrip) {
		DTOSpecificTrip u = findSpecificTrip(sTrip.get_st_id());
		if(u != null)
			return u;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("INSERT INTO citis_specific_trip "
									+ "(?, ?, ?, ?, ?)");
			
			/*
			ps.setString(1, sTrip.getCalendar());
			ps.setTimestamp(2, sTrip.getDepartureTime());
			ps.setString(3, sTrip.getLineName());
			ps.setString(4, sTrip.getId());
			ps.setString(5, sTrip.getTripId());
			*/
			
			
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
		
		return sTrip;
	}
}
