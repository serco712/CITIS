package app.model;

public class Subway extends Transport {
	
	private static int numSubways;
	
	private static final TransportType TYPE = TransportType.TRAIN;
	
	public Subway(String id, Line line, int time) {
		super(id, line, time, TYPE);
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
