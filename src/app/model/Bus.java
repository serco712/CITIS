package app.model;

public class Bus extends Transport {
	
	private static int numBuses;
	
	private static final TransportType TYPE = TransportType.TRAIN;
	
	public Bus(String id, Line line, int time) {
		super(id, line, time, TYPE);
	}
		
	@Override
	public int getAmount() {
		return numBuses;
	}
	
	@Override
	public void onEnter() {
		numBuses++;
	}
	
	@Override
	public void onDelete() {
		numBuses--;
	}	
}
