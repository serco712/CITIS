package app.data;

import java.util.ArrayList;
import java.util.List;

import app.model.Line;
import app.model.Station;
import app.model.SubwayStation;

public class SubwayStationFactory extends StationFactory {
	
	private static final String FACT_NAME = "subway_station";
	
	public SubwayStationFactory() {
		super(FACT_NAME);
	}

	@Override
	protected Station createStation(String[] para) {
		// Format: id name x y number_lines lines_id
		List<String> l = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(para[6]); i++)
			l.add(para[7 + i]);
		List<Line> ll = new ArrayList<>();
		return new SubwayStation(para[1], para[2], Integer.parseInt(para[3]), Integer.parseInt(para[4]),
				ll);
	}
}
