package app.model.business.line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.station.ASStation;
import app.model.business.transport.ASTransport;

public class ASLine extends CITISObject {
	
	private static int numLines;
	
	private List<ASStation> stops;
	private List<ASTransport> transports;
	private TransportType transp;
	private static final String TYPE_ID = "line";
	private Color c;
	
	
	public ASLine (String id, TransportType t, int c1, int c2, int c3) {
		super(id);
		
		if (t == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		
		c = new Color(c1, c2, c3);
		stops = new ArrayList<>();
		transports = new ArrayList<>();
		transp = t;
	}
	
	public int getNumStops() {
		return stops.size();
	}
	
	@Override
	public int getAmount() {
		return numLines;
	}
	
	@Override
	public void onEnter() {
		numLines++;
	}
	
	public void onDelete() {
		numLines--;
	}
	
	public void addToLine(ASStation s) {
		stops.add(s);
	}
	
	public boolean isInLine(ASStation s) {
		return stops.contains(s);
	}
	
	public TransportType getTransport() {
		return transp;
	}

	public void addToLine(ASTransport t) {
		transports.add(t);
	}
	
	public List<ASStation> getStations() {
		return Collections.unmodifiableList(stops);
	}
	
	public Color getColor() {
		return c;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(_id + ' ' + transp.toString() + ' ');
		for(ASStation s : stops)
			str.append('(' + s.getId() + ", " + s.getName() + ") ");
		for(ASTransport t : transports)
			str.append(t.getId());
		return str.toString();
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
}
