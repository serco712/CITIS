package app.model;

import java.util.List;

public class SubwayStation extends Station {
	
	private static int subwayStationCounter;
	
	private static final TransportType TYPE = TransportType.SUBWAY;
	
	private static final String TYPE_ID = "subway_station";
	
	public SubwayStation(String id, String name, int x, int y, List<Line> l) {
		super(id, name, x, y, TYPE, l);
	}
	
	@Override
	public void onEnter() {
		subwayStationCounter++;
	}
	
	@Override
	public void onDelete() {
		subwayStationCounter--;
	}
	
	@Override
	public int getAmount() {
		return subwayStationCounter;
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
}
