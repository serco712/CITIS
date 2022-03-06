package app.model;

public class SubwayStation extends Station {
	
	private static int subwayStationCounter;
	private static final TransportType TYPE = TransportType.SUBWAY;

	SubwayStation(String id, String name, int x, int y) {
		super(id, name, x, y, TYPE);
	}
	
	public void onEnter() {
		subwayStationCounter++;
	}
	
	public void onDelete() {
		subwayStationCounter--;
	}
	
	public int getSubwaysNumber() {
		return subwayStationCounter;
	}

}
