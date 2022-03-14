package app.model;

import java.util.List;

public class BusStation extends Station {
	
	private static int busStationCounter;
	
	private static final TransportType TYPE = TransportType.BUS;
	
	private static final String TYPE_ID = "bus_station";
	
	public BusStation(String id, String name, int x, int y, List<Line> l) {
		super(id, name, x, y, TYPE, l);
	}
	
	@Override
	public void onEnter() {
		busStationCounter++;
	}
	
	@Override
	public void onDelete() {
		busStationCounter--;
	}
	
	@Override
	public int getAmount() {
		return busStationCounter;
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
}
