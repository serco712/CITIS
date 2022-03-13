package app.model;

import java.util.List;

public class Train extends Transport {
	
	private static int numTrains;
	
	private static final TransportType TYPE = TransportType.TRAIN;
	
	public Train(String id, int time, List<Line> line) {
		super(id, time, TYPE, line);
	}
		
	@Override
	public int getAmount() {
		return numTrains;
	}
	
	@Override
	public void onEnter() {
		numTrains++;
	}
	
	@Override
	public void onDelete() {
		numTrains--;
	}	
}
