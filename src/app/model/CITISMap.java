package app.model;

import java.util.HashMap;
import java.util.Map;

public class CITISMap {
	
	private Map<String, Station> stations;
	
	private Map<String, Line> lines;
	
	private Map<String, Transport> transports;
	
	public CITISMap () {
		stations = new HashMap<>();
		lines = new HashMap<>();
		transports = new HashMap<>();
	}
	
	public void addStation(Station s) {
		stations.put(s.getId(), s);
	}
	
	public void addLine(Line l) {
		lines.put(l.getId(), l);
	}
	
	public void addTransport(Transport t) {
		transports.put(t.getId(), t);
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
	
	public Transport searchTransport(String _id) {
		if(transports.containsKey(_id))
			return transports.get(_id);
		return null;
	}
}
