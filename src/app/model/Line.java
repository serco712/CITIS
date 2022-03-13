package app.model;

import java.util.ArrayList;
import java.util.List;

public class Line extends CITISObject {
	
	private static int numLines;
	
	private List<Station> stops;
	
	private List<Transport> transports;
	
	private TransportType transp;
	
	public Line (String id, TransportType t) {
		super(id);
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
	
	public void addToLine(Station s) {
		stops.add(s);
	}
	
	public boolean isInLine(Station s) {
		return stops.contains(s);
	}
	
	public TransportType getTransport() {
		return transp;
	}

	public void addToLine(Transport t) {
		transports.add(t);
	}
}
