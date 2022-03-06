package app.model;

public class BusStation extends Station {
	
	private static int busStationCounter;
	private static final TransportType TYPE = TransportType.BUS;

	BusStation(String id, String name, int x, int y) {
		super(id, name, x, y, TYPE);
	}

	public void onEnter() {
		busStationCounter++;
	}
	
	public void onDelete() {
		busStationCounter--;
	}
	
	public int getSubwaysNumber() {
		return busStationCounter;
	}
}
