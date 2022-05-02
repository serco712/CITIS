package app.model.layers.integration.line;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;
import app.model.layers.integration.Conectar;

public class LineDatabaseDAO implements LineDAO {
	
	private static LineDAO instance;
	private Connection con;
	
	private LineDatabaseDAO() {
		con = getConnection();
	}
	
	public static LineDAO getInstance() {
		if (instance == null)
			instance = new LineDatabaseDAO();
		
		return instance;
	}

	@Override
	public DTOLine findLine(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOLine line = null;
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_route "
									+ "WHERE route_id = ?;");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			line = new DTOLine();
			line.setId(rs.getString("route_id"));
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

			int ttype = Integer.parseInt(id.substring(0, 1));
			if(ttype == 4)
				line.setTransportType(TransportType.SUBWAY);
			else if(ttype == 5)
				line.setTransportType(TransportType.TRAIN);
			else
				line.setTransportType(TransportType.BUS);
			
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
		
		return null;
	}

	@Override
	public void saveLine(DTOLine line) {
		DTOLine dl = findLine(line.getId());
		if(dl == null)
			createLine(line);

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("UPDATE citis_route "
									+ "SET route_short_name = ?, route_long_name = ?, route_color = ?, route_text_color = ?, agency_name = ? "
									+ "WHERE route_id = ?;");
			
			ps.setString(1, line.getShortName());
			ps.setString(2, line.getLongName());
			StringBuilder str = new StringBuilder();
			str.append("(" + line.getLineColor().getRed() + ',' + line.getLineColor().getGreen() + ',' +
					line.getLineColor().getBlue() + ")");
			ps.setString(3, str.toString());
			str = new StringBuilder();
			str.append("(" + line.getColorText().getRed() + ',' + line.getColorText().getGreen() + ',' +
					line.getColorText().getBlue() + ")");
			ps.setString(4, str.toString());
			ps.setString(5, line.getAgency());
			ps.setString(6, line.getId());
			
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
	public DTOLine createLine(DTOLine line) {
		DTOLine dl = findLine(line.getId());
		if(dl != null)
			return dl;

		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO citis_route "
									+ "VALUES (?, ?, ?, ?, ?, ?);");
			
			ps.setString(1, line.getId());
			ps.setString(2, line.getShortName());
			ps.setString(3, line.getLongName());
			StringBuilder str = new StringBuilder();
			str.append("(" + line.getLineColor().getRed() + ',' + line.getLineColor().getGreen() + ',' +
					line.getLineColor().getBlue() + ")");
			ps.setString(4, str.toString());
			str = new StringBuilder();
			str.append("(" + line.getColorText().getRed() + ',' + line.getColorText().getGreen() + ',' +
					line.getColorText().getBlue() + ")");
			ps.setString(5, str.toString());
			ps.setString(6, line.getAgency());
			
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
		
		return line;
	}

	@Override
	public List<String> findRouteStations(String line_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> as = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT cs.stop_id "
									+ "FROM citis_route cr"
									+ 	"INNER JOIN citis_trip ct ON cr.route_id = ct.route_id"
									+	"INNER JOIN citis_stop_time cst ON ct.trip_id = cst.trip_id"
									+	"INNER JOIN citis_stop cs ON cs.stop_id = cst.stop_id"
									+ "WHERE route_id = ?;");
			ps.setString(1, line_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
                as.add(rs.getString("stop_id"));
            }
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
		
		return as;
	}

	@Override
	public List<ASLine> searchLines() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ASLine> l = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_route;");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				DTOLine line = new DTOLine();
				line.setId(rs.getString("route_id"));
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
				
				int ttype = Integer.parseInt(rs.getString("route_id").substring(0, 1));
				if(ttype == 4)
					line.setTransportType(TransportType.SUBWAY);
				else if(ttype == 5)
					line.setTransportType(TransportType.TRAIN);
				else
					line.setTransportType(TransportType.BUS);
				
				l.add(new ASLine(line));
			}
				
			rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("Fallo en la bbdd");
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
		return l;
	}
	
	private Connection getConnection() {
		Conectar c = new Conectar();
		return c.getConnection();
	}

	@Override
	public List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> findDepartures(String stop_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> as = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT ct.route_id, route_short_name, calendar_id, trip_long_name, sec_to_time((cst.departure DIV 10000*60*60+(cst.departure-cst.departure DIV 10000*10000) DIV 100*60+(cst.departure-cst.departure DIV 100*100))+(csti.departure_time DIV 10000*60*60+(csti.departure_time-csti.departure_time DIV 10000*10000) DIV 100*60+(csti.departure_time-csti.departure_time DIV 100*100)) % 86400) AS schedule " + 
					                  "FROM citis_route cr " + 
					                  "INNER JOIN citis_trip ct ON cr.route_id = ct.route_id " + 
					                  "INNER JOIN citis_specific_trip cst ON cst.trip_id = ct.trip_id " + 
				                      "INNER JOIN citis_stop_time csti ON ct.trip_id = csti.trip_id " + 
					                  "WHERE stop_id = ? " + 
					                  "ORDER BY schedule ASC;");
			ps.setString(1, stop_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				DTOLine dt = new DTOLine();
                dt.setId(rs.getString("ct.route_id"));
                dt.setShortName(rs.getString("route_short_name"));
                int ttype = Integer.parseInt(rs.getString("ct.route_id").substring(0, 1));
    			if(ttype == 4)
    				dt.setTransportType(TransportType.SUBWAY);
    			else if(ttype == 5)
    				dt.setTransportType(TransportType.TRAIN);
    			else
    				dt.setTransportType(TransportType.BUS);
    			String[] s = rs.getString("schedule").split(":");
    			TimeADT time = new TimeADT(Integer.parseInt(s[0]), Integer.parseInt(s[1]),
    					Integer.parseInt(s[2]));
    			
    			String s1 = rs.getString("trip_long_name");
    			String s2 = rs.getString("calendar_id");
    			
    			Pair<Pair<ASLine, TimeADT>, Pair<String, String>> p = new Pair<Pair<ASLine, TimeADT>, Pair<String, String>>(
    					new Pair<ASLine, TimeADT>(new ASLine(dt), time), new Pair<String, String>(s1, s2));
    			as.add(p);
            }
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
		return as;
	}

	@Override
	public void removeDeparture(ASLine as, TimeADT adt, String destiny, String calend_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("DELETE "
									+ "FROM citis_stop_time "
									+ "WHERE departure_time = ? AND "
									+ 		"trip_id = (SELECT ct.trip_id "
									+ 				   "FROM citis_trip ct "
									+ 				   		"INNER JOIN citis_route cr ON ct.route_id = cr.route_id "
									+ 						"INNER JOIN citis_specific_trip cst ON cst.route_id = ct.route_id"
									+ 				   "WHERE ct.route_id = ? AND cst.calendar_id = ? AND ct.trip_long_name = ?)"
									+ 		"AND "
									+ 		"notes = (SELECT ct.notes "
									+ 				   "FROM citis_trip ct "
									+ 				   		"INNER JOIN citis_route cr ON ct.route_id = cr.route_id "
									+ 						"INNER JOIN citis_specific_trip cst ON cst.route_id = ct.route_id"
									+ 				   "WHERE ct.route_id = ? AND cst.calendar_id = ? AND ct.trip_long_name = ?);");
			
			ps.setString(1, adt.toString());
			ps.setString(2, as.getId());
			ps.setString(3, calend_id);
			ps.setString(4, destiny);
			ps.setString(5, as.getId());
			ps.setString(6, calend_id);
			ps.setString(7, destiny);
			
			ps.executeUpdate();
			ps.close();
			
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
	}

	@Override
	public void updateShortName(String name) {
		/*DTOLine dl = findLine(line.getId());
		if(dl == null)
			createLine(line);

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("UPDATE citis_route "
									+ "SET route_short_name = ?, route_long_name = ?, route_color = ?, route_text_color = ?, agency_name = ? "
									+ "WHERE route_id = ?;");
			
			ps.setString(1, line.getShortName());
			ps.setString(2, line.getLongName());
			StringBuilder str = new StringBuilder();
			str.append("(" + line.getLineColor().getRed() + ',' + line.getLineColor().getGreen() + ',' +
					line.getLineColor().getBlue() + ")");
			ps.setString(3, str.toString());
			str = new StringBuilder();
			str.append("(" + line.getColorText().getRed() + ',' + line.getColorText().getGreen() + ',' +
					line.getColorText().getBlue() + ")");
			ps.setString(4, str.toString());
			ps.setString(5, line.getAgency());
			ps.setString(6, line.getId());
			
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
		}*/
	}
	
}
