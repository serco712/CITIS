package app.model.business.station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.layers.integration.station.StationDatabaseDAO;

public class ASStation extends CITISObject {
	
	private static final String TYPE_ID = "station";
	
	private static int numStations;
	private String _name;
	private String _parent;
	private int xCoor, yCoor;
	private TransportType transport;
	private List<ASLine> lines;
	private String _city;

	public ASStation () {
		super(" ");
	}
	
	public ASStation(DTOStation st) {
		super(st.getId());
		
		if (st.getName() == null || st.getName() == "")
			throw new IllegalArgumentException("El nombre no puede ser nulo");
		else if (st.getTransportType() == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		
		_name = st.getName();
		xCoor = st.getXCoor();
		yCoor = st.getYCoor();
		transport = st.getTransportType();
		lines = st.getLines();
		_parent = st.getParent();
		_city = st.getCity();
		lines = new ArrayList<>();
	}
	
	public String getName() {
		return _name;
	}
	
	public int getX() {
		return xCoor;
	}
	
	public int getY() {
		return yCoor;
	}
	
	public TransportType getTransport() {
		return transport;
	}
	
	public int getNumLines () {
		return lines.size();
	}
	
	public List<ASLine> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	@Override
	public String toString() {
		return _name;
	}

	@Override
	public void onEnter() {
		numStations++;
	}

	@Override
	public void onDelete() {
		numStations--;
	}

	@Override
	public int getAmount() {
		return numStations;
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
	
	public void setLineList(List<ASLine> ls) {
		lines = ls;
		for(ASLine al : ls)
			al.addToLine(this);
	}
	
	public List<ASStation> searchStations() {
		StationDatabaseDAO sd = new StationDatabaseDAO();
		List<ASStation> as = sd.searchStations();
		for(ASStation s : as)
			s.setLineList(sd.searchLines(s.getId()));
		
		return as;
	}

	public DTOStation createStation(DTOStation station) {
		StationDatabaseDAO dao = new StationDatabaseDAO();
		return dao.createStation(station);
	}
	
	public List<ASLine> searchLines(String id) {
		StationDatabaseDAO dao = new StationDatabaseDAO();
		return dao.searchLines(id);
	}
	
	public boolean findStation(String id) {
		StationDatabaseDAO dao = new StationDatabaseDAO();
		return dao.findStation(id) != null;
	}
}
