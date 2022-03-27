package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CITISMap implements Observable<CITISObserver> {
	
	private Map<String, Station> stations_map;
	
	private List <Station> stations;
	
	private Map<String, Line> lines_map;
	
	private List<Line> lines;
	
	private Map<String, Transport> transports_map;
	
	private List<Transport> transports;
	
	private Map<String, User> customers_map;
	
	private List<User> customers;
	
	private List<CITISObserver> co;
	
	public CITISMap () {
		stations = new ArrayList<>();
		lines = new ArrayList<>();
		transports = new ArrayList<>();
		customers = new ArrayList<>();
		stations_map = new HashMap<>();
		lines_map = new HashMap<>();
		transports_map = new HashMap<>();
		customers_map = new HashMap<>();
		co = new ArrayList<>();
	}
	
	public void addStation(Station s) {
		stations.add(s);
		stations_map.put(s.getId(), s);
		for(CITISObserver cs : co)
			cs.CITISObjectAdded(this, s);
	}
	
	public void addLine(Line l) {
		lines.add(l);
		lines_map.put(l.getId(), l);
		for(CITISObserver cs : co)
			cs.CITISObjectAdded(this, l);
	}
	
	public void addTransport(Transport t) {
		transports.add(t);
		transports_map.put(t.getId(), t);
		for(CITISObserver cs : co)
			cs.CITISObjectAdded(this, t);
	}
	
	public void addUser(User u) {
		customers.add(u);
		customers_map.put(u.getUser(), u);
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
	
	public List<User> getUsers() {
		return Collections.unmodifiableList(customers);
	}
	
	public boolean checkUser(String username, String password) {	
		return customers_map.containsKey(username) && 
				customers_map.get(username).getPassword().equals(password);
	}

	@Override
	public void addObserver(CITISObserver o) {
		co.add(o);
		for(CITISObserver c : co)
			c.registerObserver(this);
	}

	@Override
	public void removeObserver(CITISObserver o) {
		co.remove(o);
	}
}
