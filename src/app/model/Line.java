package app.model;

import java.util.List;

public class Line extends CITISObject {
	
	private static int numLines;
	
	private List<Station> stops;
	
	private TransportType transp;
	
	public Line (String id, List<Station> ls, TransportType t) {
		super(id);
		stops = ls;
		transp = t;
	}
	
	public int getNumStops() {
		return stops.size();
	}
	
	public int getNumLines() {
		return numLines;
	}
	
	public void onEnter() {
		numLines++;
	}
	
	public void onDelete() {
		numLines--;
	}
	
	public boolean isInLine(Station s) {
		return stops.contains(s);
	}
	
	public TransportType getTransport() {
		return transp;
	}
}
