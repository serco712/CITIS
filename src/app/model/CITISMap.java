package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CITISMap {
	
	private Map<String, Station> stations_map;
	
	private List <Station> stations;
	
	private Map<String, Line> lines_map;
	
	private List<Line> lines;
	
	private Map<String, Transport> transports_map;
	
	private List<Transport> transports;
	
	public CITISMap () {
		stations = new ArrayList<>();
		lines = new ArrayList<>();
		transports = new ArrayList<>();
		stations_map = new HashMap<>();
		lines_map = new HashMap<>();
		transports_map = new HashMap<>();
	}
	
	public void addStation(Station s) {
		stations.add(s);
		stations_map.put(s.getId(), s);
	}
	
	public void addLine(Line l) {
		lines.add(l);
		lines_map.put(l.getId(), l);
	}
	
	public void addTransport(Transport t) {
		transports.add(t);
		transports_map.put(t.getId(), t);
	}
	
	public Line searchLine(String _id) {
		if(lines_map.containsKey(_id))
			return lines_map.get(_id);
		return null;
	}
	
	public Station searchStation(String _id) {
		if(stations_map.containsKey(_id))
			return stations_map.get(_id);
		return null;
	}
	
	public Transport searchTransport(String _id) {
		if(transports_map.containsKey(_id))
			return transports_map.get(_id);
		return null;
	}
	
	public List<Station> getStations(){
		return Collections.unmodifiableList(stations);
	}
	public List<Line> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	public List<Transport> getTransports (){
		return Collections.unmodifiableList(transports);
	}
}
