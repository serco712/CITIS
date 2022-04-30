package app.model.layers.integration.line;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.misc.TimeADT;
import app.misc.Triplet;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;
import app.model.layers.integration.Conectar;

public class LineDatabaseDAO implements LineDAO {

	@Override
	public DTOLine findLine(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOLine line = null;
		
		try {
			con = getConnection();
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

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
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
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return line;
	}

	@Override
	public List<String> findRouteStations(String line_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> as = new ArrayList<>();
		
		try {
			con = getConnection();
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
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return as;
	}

	@Override
	public List<ASLine> searchLines() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ASLine> l = new ArrayList<>();
		
		try {
			con = getConnection();
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
				
				if (con != null)
					con.close();
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
	public List<Triplet<ASLine, TimeADT, String>> findDepartures(List<String> route_ids) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Triplet<ASLine, TimeADT, String>> as = new ArrayList<>();
		
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM citis_route cr "
									+ 	"INNER JOIN citis_trip ct ON cr.route_id = ct.route_id "
									+	"INNER JOIN citis_stop_time cst ON ct.trip_id = cst.trip_id "
									+	"INNER JOIN citis_stop cs ON cs.stop_id = cst.stop_id "
									+ "ORDER BY departure_time;");
			rs = ps.executeQuery();
			
			while(rs.next()){
				if(route_ids.contains(rs.getString("cr.route_id"))) {
					DTOLine dt = new DTOLine();
	                dt.setId(rs.getString("cr.route_id"));
	                dt.setShortName(rs.getString("route_short_name"));
	                int ttype = Integer.parseInt(rs.getString("cr.route_id").substring(0, 1));
	    			if(ttype == 4)
	    				dt.setTransportType(TransportType.SUBWAY);
	    			else if(ttype == 5)
	    				dt.setTransportType(TransportType.TRAIN);
	    			else
	    				dt.setTransportType(TransportType.BUS);
	    			
	    			String[] t = rs.getString("departure_time").split(":");
	    			TimeADT time = new TimeADT(Integer.parseInt(t[0]), Integer.parseInt(t[1]),
	    					Integer.parseInt(t[2]));
	    			
	    			String notes = rs.getString("cst.notes");
	    			
	    			Triplet<ASLine, TimeADT, String> p = new Triplet<ASLine, TimeADT, String>(new ASLine(dt), time, notes);
	    			as.add(p);
				}
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
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return as;
	}

	@Override
	public void removeDeparture(ASLine as, TimeADT adt, String notes) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("DELETE * "
									+ "FROM citis_stop_time cst "
									+ 	"INNER JOIN citis_trip ct ON cst.trip_id = ct.trip_id "
									+ 	"INNER JOIN citis_route cr ON ct.route_id = cr.route_id "
									+ "WHERE cr.route_id = ? AND departure_time = ? AND notes = ?;");
			
			ps.setString(1, as.getId());
			ps.setString(2, adt.toString());
			ps.setString(3, notes);
			
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
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
