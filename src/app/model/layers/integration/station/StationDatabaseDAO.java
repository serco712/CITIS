package app.model.layers.integration.station;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;
import app.model.business.station.ASStation;
import app.model.business.station.DTOStation;
import app.model.business.trip.ASSpecificTrip;
import app.model.layers.integration.Conectar;

public class StationDatabaseDAO implements StationDAO {
	
	private static StationDAO instance;
	private Connection con;
	
	private StationDatabaseDAO() {
		con = getConnection();
	}
	
	public static StationDAO getInstance() {
		if (instance == null)
			instance = new StationDatabaseDAO();
		
		return instance;
	}

	@Override
	public DTOStation findStation(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOStation st = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_stop "
									+ "WHERE stop_id = ?;");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			st = new DTOStation();
			st.setId(id);
			st.setName(rs.getString("stop_name"));
			st.setXCoor(rs.getInt("x_coor"));
			st.setYCoor(rs.getInt("y_coor"));
			st.setCity(rs.getString("stop_city"));
			st.setLines(new ArrayList<ASLine>());
			st.setParent(rs.getString("parent_id"));
			int ttype = Integer.parseInt(id.substring(0, 1));
			if(ttype == 4)
				st.setTransportType(TransportType.SUBWAY);
			else if(ttype == 5)
				st.setTransportType(TransportType.TRAIN);
			else
				st.setTransportType(TransportType.BUS);
			
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
		
		return st;	
	}

	@Override
	public void saveStation(DTOStation station) {
		DTOStation dt = findStation(station.getId());
		if(dt == null)
			createStation(station);

		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("UPDATE citis_stop"
									+ "SET stop_name = ?, x_coor = ?, y_coor = ?, stop_city = ?, parent_id = ?"
									+ "WHERE stop_id = ?;");
			
			ps.setString(1, station.getName());
			ps.setInt(2, station.getXCoor());
			ps.setInt(3, station.getYCoor());
			ps.setString(4, station.getCity());
			ps.setString(5, station.getParent());
			ps.setString(6, station.getId());
			
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
	public DTOStation createStation(DTOStation station) {
		DTOStation dt = findStation(station.getId());
		if(dt != null)
			return dt;

		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO citis_stop "
									+ "VALUES (?, ?, ?, ?, ?, ?);");
			
			ps.setString(1, station.getId());
			ps.setString(2, station.getName());
			ps.setInt(3, station.getXCoor());
			ps.setInt(4, station.getYCoor());
			ps.setString(5, station.getCity());
			ps.setString(6, station.getParent());
			
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
		
		return station;
	}
	
	private Connection getConnection() {
		Conectar c = new Conectar();
		return c.getConnection();
	}

	@Override
	public List<ASStation> searchStations() {
		List<ASStation> ls = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOStation st = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_stop;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				st = new DTOStation();
				st.setId(rs.getString("stop_id"));
				st.setName(rs.getString("stop_name"));
				st.setXCoor(rs.getInt("x_coor"));
				st.setYCoor(rs.getInt("y_coor"));
				st.setCity(rs.getString("stop_city"));
				st.setParent(rs.getString("parent_id"));
				int ttype = Integer.parseInt(st.getId().substring(0, 1));
				if(ttype == 4)
					st.setTransportType(TransportType.SUBWAY);
				else if(ttype == 5)
					st.setTransportType(TransportType.TRAIN);
				else
					st.setTransportType(TransportType.BUS);
				
				ls.add(new ASStation(st));
			}
			
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
		
		return ls;
	}

	@Override
	public List<ASLine> searchLines(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ASLine> al = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT cr.route_id, route_short_name, route_long_name, route_color, route_text_color, agency_name "
							+   "FROM citis_route cr "
							+ 	"INNER JOIN citis_trip ct ON cr.route_id = ct.route_id "
							+	"INNER JOIN citis_stop_time cst ON ct.trip_id = cst.trip_id "
							+	"INNER JOIN citis_stop cs ON cs.stop_id = cst.stop_id "
							+   "WHERE cs.stop_id = ?;");
			
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				DTOLine line = new DTOLine();
				line.setId(rs.getString("cr.route_id"));
				line.setShortName(rs.getString("route_short_name"));
				line.setLongName(rs.getString("route_long_name"));
				String co = rs.getString("route_text_color");
				co = (String) co.subSequence(1, co.length() - 1);
				String [] aux = co.split(",");
				line.setColorText(new Color(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), 
						Integer.parseInt(aux[2])));
				co = rs.getString("route_color");
				co = (String) co.subSequence(1, co.length() - 1);
				aux = co.split(",");
				line.setColorText(new Color(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), 
						Integer.parseInt(aux[2])));
				line.setAgency(rs.getString("agency_name"));

				int ttypel = Integer.parseInt(line.getId().substring(0, 1));
				if(ttypel == 4)
					line.setTransportType(TransportType.SUBWAY);
				else if(ttypel == 5)
					line.setTransportType(TransportType.TRAIN);
				else
					line.setTransportType(TransportType.BUS);
				
				ASLine a = new ASLine(line);
				boolean find = false;
				for(ASLine l : al)
					if(l.getId().equals(a.getId()))
						find = true;
				
				if(!find)
					al.add(a);
			}
			
			ps.close();
			rs.close();
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
		
		return al;
	}
	
	@Override
	public List<Pair<TimeADT, String>> searchTimes(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pair<TimeADT, String>> time = new ArrayList<>();
		
		try {
			Calendar calendario = new GregorianCalendar();
			int hora = calendario.get(Calendar.HOUR_OF_DAY);
			int minutos = calendario.get(Calendar.MINUTE);
			int segundos = calendario.get(Calendar.SECOND);
			Time t = new Time((hora * 3600 + minutos * 60 + segundos - 3600) * 1000);
			
			System.out.println(t.toString());
			ps = con.prepareStatement("SELECT DISTINCT sec_to_time(MOD ((cst.departure DIV 10000*60*60+(cst.departure-cst.departure DIV 10000*10000) DIV 100*60+(cst.departure-cst.departure DIV 100*100))+(csti.departure_time DIV 10000*60*60+(csti.departure_time-csti.departure_time DIV 10000*10000) DIV 100*60+(csti.departure_time-csti.departure_time DIV 100*100)), 86400)) AS schedule, notes "
									+ "FROM citis_stop_time csti "
									+ "INNER JOIN citis_specific_trip cst ON csti.trip_id = cst.trip_id "
									+ "WHERE stop_id = ? AND sec_to_time(MOD ((cst.departure DIV 10000*60*60+(cst.departure-cst.departure DIV 10000*10000) DIV 100*60+(cst.departure-cst.departure DIV 100*100))+(csti.departure_time DIV 10000*60*60+(csti.departure_time-csti.departure_time DIV 10000*10000) DIV 100*60+(csti.departure_time-csti.departure_time DIV 100*100)), 86400)) >= ? "
									+ "ORDER BY schedule;");
			
			ps.setString(1, id);
			ps.setTime(2, t);
			rs = ps.executeQuery();
			
			while (rs.next() && time.size() < 10) {
				Time ti = rs.getTime("schedule");
				String s = rs.getString("notes");
				String[] s1 = ti.toString().split(":");
				TimeADT t1 = new TimeADT(Integer.parseInt(s1[0]), Integer.parseInt(s1[1]),
						Integer.parseInt(s1[2]));
			
				time.add(new Pair<TimeADT, String>(t1, s));
			}
			
			ps.close();
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return time;
	}
}
