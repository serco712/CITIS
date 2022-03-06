package app.model;

public abstract class Station extends CITISObject {
	
	private String name;
	private int xCoor, yCoor;
	TransportType transport;

	Station(String id, String name, int x, int y,
			TransportType transport) {
		super(id);
		this.name = name;
		xCoor = x;
		yCoor = y;
		this.transport = transport;
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
