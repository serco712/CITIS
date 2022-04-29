package app.model.layers.integration.trip;

import java.sql.*;

import app.model.business.trip.DTOTrip;
import app.model.layers.integration.Conectar;

public class TripDatabaseDAO implements TripDAO {

	@Override
	public DTOTrip findTrip(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOTrip trip = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_trip "
									+ "WHERE trip_id = ?;");
			
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			trip = new DTOTrip();
			trip.set_name(rs.getString("trip_long_name"));
			trip.set_trip_notes(rs.getString("notes"));
			trip.set_route_id(rs.getString("route_id"));
			
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
		
		return trip;
	}

	private Connection getConnection() {
		Conectar conectar = new Conectar();
		return conectar.getConnection();
	}

	@Override
	public void saveTrip(DTOTrip trip) {
		if (!createTrip(trip).equals(trip)) {
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = getConnection();
				ps = con.prepareStatement("UPDATE citis_trip "
										+ "SET trip_long_name = ?, route_id = ?, notes = ? "
										+ "WHERE trip_id = ?;");
				/*
				ps.setString(1, trip.getName());
				ps.setString(2, trip.getLine());
				//ps.setString(3, trip.getNotes());
				ps.setString(4, trip.getId());
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
	public DTOTrip createTrip(DTOTrip trip) {
		DTOTrip aux = findTrip(trip.get_id());
		if (aux != null)
			return aux;
		else {
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = getConnection();
				ps = con.prepareStatement("INSERT INTO citis_trip "
										+ "VALUES (?, ?, ?, ?);");
				
				ps.setString(1, trip.get_id());
				ps.setString(2, trip.get_route_id());
				ps.setString(3, trip.get_name());
				ps.setString(4, trip.get_trip_notes());
				
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
			return trip;
		}
	}
	
	@Override
	public DTOTrip createStopTime(DTOTrip trip) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("INSERT INTO citis_stop_time "
									+ "VALUES(?, ?, ?, ?, ?);");
			
			ps.setString(1, trip.get_id());
			ps.setString(2, trip.get_stop_id());
			String[] t = trip.get_departureTime().split(":");
			Time ti = new Time((Integer.parseInt(t[0])*3600 + Integer.parseInt(t[1]) *60 + 
					Integer.parseInt(t[2]))/1000);
			ps.setTime(3, ti);
			ps.setInt(4, trip.get_stop_sequence());
			ps.setString(5, trip.get_stop_notes());
			
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
		return trip;
	}
	
	@Override
	public void deleteTrip(DTOTrip trip) {
		if (!createTrip(trip).equals(trip)) {
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = getConnection();
				ps = con.prepareStatement("DELETE citis_trip "
										+ "SET trip_long_name = ?, route_id = ?, notes = ? "
										+ "WHERE trip_id = ?;");
				/*
				ps.setString(1, trip.getName());
				ps.setString(2, trip.getLine());
				//ps.setString(3, trip.getNotes());
				ps.setString(4, trip.getId());
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
}
