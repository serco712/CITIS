package app.model;

public class Train extends Transport {
	
	private static final TransportType TYPE = TransportType.TRAIN;
	
	public Train(String id, Line line, int time) {
		super(id, line, time, TYPE);
	}
	
		
}
