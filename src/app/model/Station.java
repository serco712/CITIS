package app.model;

import java.util.List;

public abstract class Station extends CITISObject {
	
	private String name;
	private int xCoor, yCoor;
	TransportType transport;
	private List<Line> lines;
	
	public Station(String id, String name, int x, int y,
			TransportType transport, List<Line> l) {
		super(id);
		this.name = name;
		xCoor = x;
		yCoor = y;
		this.transport = transport;
		lines = l;
		for(Line li : lines)
			li.addToLine(this);
	}
	
	
	public String getName() {
		return name;
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
	
}
