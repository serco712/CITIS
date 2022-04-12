package app.model.layers.integration.transport;

import java.sql.*;

import app.model.business.transport.DTOTransport;

public class TransportDatabaseDAO implements TransportDAO {

	@Override
	public DTOTransport findTransport(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTOTransport transport = null;
		/*
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT * "
									+ "FROM ");
		}
		*/
		return null;
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
