package app.model;

public class Train extends Transport {
	
	private static int numTrains;
	
	private static final TransportType TYPE = TransportType.TRAIN;
	
	public Train(String id, Line line, int time) {
		super(id, line, time, TYPE);
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
