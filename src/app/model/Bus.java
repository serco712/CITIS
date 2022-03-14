package app.model;

import java.util.List;

public class Bus extends Transport {
	
	private static int numBuses;
	
	private static final TransportType TYPE = TransportType.BUS;
	
	public Bus(String id, int time, List<Line> line) {
		super(id, time, TYPE, line);
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
