package app.model;

public abstract class Transport extends CITISObject {
	
	private static int numTransports;
	
	private TransportType type;
	
	private Line line;
	
	private int time;

	public Transport(String id, Line line, int time, TransportType type) {
		super(id);
		this.type = type;
		this.line = line;
		this.time = time;
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
	
	public Line getLine() {
		return line;
	}
	
	public TransportType getType() {
		return type;
	}
	
	public int getTime() {
		return time;
	}
}
