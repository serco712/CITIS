package app.model;

import java.util.List;

public class Subway extends Transport {
	
	private static int numSubways;
	
	private static final TransportType TYPE = TransportType.SUBWAY;
	
	public Subway(String id, int time, List<Line> line) {
		super(id, time, TYPE, line);
	}
		
	@Override
	public int getAmount() {
		return numSubways;
	}
	
	@Override
	public void onEnter() {
		numSubways++;
	}
	
	@Override
	public void onDelete() {
		numSubways--;
	}	
}
