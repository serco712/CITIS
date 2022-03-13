package app.model;

import java.util.List;

public abstract class Transport extends CITISObject {
	
	private static int numTransports;
	
	private TransportType type;
	
	private List<Line> lines;
	
	private int time;

	public Transport(String id, int time, TransportType type, List<Line> l) {
		super(id);
		this.type = type;
		this.time = time;
		lines = l;
		for(Line li : lines)
			li.addToLine(this);		
	}

	@Override
	public int getAmount() {
		return numTransports;
	}
	
	@Override
	public void onEnter() {
		numTransports++;
	}
	
	@Override
	public void onDelete() {
		numTransports--;
	}
	
	public TransportType getType() {
		return type;
	}
	
	public int getTime() {
		return time;
	}
}
