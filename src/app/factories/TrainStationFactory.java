package app.factories;

import java.util.ArrayList;
import java.util.List;

import app.model.CITISMap;
import app.model.Line;
import app.model.Station;
import app.model.TrainStation;

public class TrainStationFactory extends StationFactory {
	
	private static final String FACT_NAME = "train_station";
	
	public TrainStationFactory() {
		super(FACT_NAME);
	}

	@Override
	protected Station createStation(String[] para, CITISMap cm) {
		// Format: id name x y number_lines lines_id
		List<String> l = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(para[5]); i++)
			l.add(para[6 + i]);
		List<Line> ll = new ArrayList<>();
		for(String s : l)
			ll.add(cm.searchLine(s));
		return new TrainStation(para[1], para[2], Integer.parseInt(para[3]), Integer.parseInt(para[4]),
				ll);
	}
}
