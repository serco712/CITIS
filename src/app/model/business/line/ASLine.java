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
	
	private List<String> stops;
	private TransportType transp;
	private static final String TYPE_ID = "line";
	private Color ctext;
	private Color cline;
	private String shortName;
	private String longName;
	private String agency;
	
	public ASLine (String id, TransportType t, int c1, int c2, int c3) {
		super(id);
		
		if (t == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		
		cline = new Color(c1, c2, c3);
		stops = new ArrayList<>();
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
		stops.add(s.getId());
	}
	
	public boolean isInLine(ASStation s) {
		return stops.contains(s.getId());
	}
	
	public TransportType getTransport() {
		return transp;
	}
	
	public List<String> getStations() {
		return Collections.unmodifiableList(stops);
	}
	
	public Color getColorText() {
		return ctext;
	}
	
	public Color getColorLine() {
		return cline;
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
}
