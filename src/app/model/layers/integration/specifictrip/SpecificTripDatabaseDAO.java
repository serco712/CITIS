package app.model.layers.integration.specifictrip;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.model.business.trip.DTOSpecificTrip;
import app.model.layers.integration.Conectar;

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
		Conectar conectar = new Conectar();
		return conectar.getConnection();
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
									+ "VALUES (?, ?, ?, ?, ?);");


			ps.setString(1, sTrip.get_st_id());
			String[] s = sTrip.get_departureTime().split(":");
			int milis = Integer.parseInt(s[0])*3600 + Integer.parseInt(s[1]) * 60 +
					Integer.parseInt(s[2]);
			ps.setTime(2, new Time(milis * 1000));
			ps.setString(3, "");
			ps.setString(4, sTrip.get_id());
			ps.setString(5, sTrip.getCalendarId());
			
			
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
	
	@Override
	public List<String> findCalendarIds() {
		List<String> ls = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT calendar_id "
									+ "FROM citis_calendar;");
			
			rs = ps.executeQuery();
			while(rs.next())
				ls.add(rs.getString("calendar_id"));
			
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
		
		return ls;
	}
}
