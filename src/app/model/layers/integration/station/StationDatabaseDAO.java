package app.model.layers.integration.station;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;
import app.model.business.station.DTOStation;
import app.model.business.user.DTOUser;
import app.model.layers.integration.Conectar;

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
			DTOStation pat = findStation(rs.getString("parent_id"));
			st.setParent(new ASStation(pat.getId(), pat.getName(), pat.getXCoor(), pat.getYCoor(),
					pat.getTransportType(), pat.getLines(), pat.getParent(), pat.getCity()));
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
				
				if (con != null)
					con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return st;	
	}

	@Override
	public void saveStation(DTOStation station) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement("INSERT INTO city_stops "
									+ "(?, ?, ?, ?, ?)");
			
			ps.setString(1, station.getId());
			ps.setString(2, station.getName());
			StringBuilder aux = new StringBuilder();
			aux.append(station.getXCoor() + ' ');
			aux.append(station.getYCoor());
			ps.setString(3, aux.toString());
			ps.setString(4, station.getCity());
			ps.setString(5, station.getParent().getId());
			
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

	private Connection getConnection() {
		Conectar c = new Conectar();
		return c.getConnection();
	}
}
