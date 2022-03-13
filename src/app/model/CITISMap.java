package app.model;

import java.util.HashMap;
import java.util.Map;

public class CITISMap {
	
	private Map<String, Station> stations;
	
	private Map<String, Line> lines;
	
	public CITISMap () {
		stations = new HashMap<>();
		lines = new HashMap<>();
	}
	
	public void addStation(Station s) {
		stations.put(s.getId(), s);
	}
	
	public void addLine(Line l) {
		lines.put(l.getId(), l);
	}
	
	public Line searchLine(String _id) {
		if(lines.containsKey(_id))
			return lines.get(_id);
		return null;
	}
	
	public Station searchStation(String _id) {
		if(stations.containsKey(_id))
			return stations.get(_id);
		return null;
	}
}
