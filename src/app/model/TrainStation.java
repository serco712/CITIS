package app.model;

import java.util.List;

public class TrainStation extends Station {
	
	private static int trainStationCounter;
	
	private static final TransportType TYPE = TransportType.TRAIN;
	
	private static final String TYPE_ID = "train_station";
	
	public TrainStation(String id, String name, int x, int y, List<Line> l) {
		super(id, name, x, y, TYPE, l);
	}
	
	@Override
	public void onEnter() {
		trainStationCounter++;
	}
	
	@Override
	public void onDelete() {
		trainStationCounter--;
	}
	
	@Override
	public int getAmount() {
		return trainStationCounter;
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
}
