package app.model;

public class TrainStation extends Station {
	
	private static int trainStationCounter;
	private static final TransportType TYPE = TransportType.TRAIN;

	TrainStation(String id, String name, int x, int y, Line l) {
		super(id, name, x, y, TYPE);
	}
	
	public void onEnter() {
		trainStationCounter++;
	}
	
	public void onDelete() {
		trainStationCounter--;
	}
	
	public int getSubwaysNumber() {
		return trainStationCounter;
	}
}
