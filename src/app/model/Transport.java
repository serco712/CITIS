package app.model;

public abstract class Transport extends CITISObject {
	
	private static int numTransports;
	
	private TransportType type;

	public Transport(String id, Line line, int time, TransportType t) {
		super(id);
		type = t;
	}

	public int getNumTransports() {
		return numTransports;
	}
	
	public void onEnter() {
		numTransports++;
	}
	
	public void onDelete() {
		numTransports--;
	}
	
	public TransportType getType() {
		return type;
	}
}
