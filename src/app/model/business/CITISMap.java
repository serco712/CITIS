package app.model.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.model.business.line.ASLine;
import app.model.business.station.ASStation;
import app.model.business.transport.ASTransport;
import app.model.business.user.ASUser;

public class CITISMap implements Observable<CITISObserver> {
	
	private Map<String, ASStation> stations_map;
	
	private List <ASStation> stations;
	
	private Map<String, ASLine> lines_map;
	
	private List<ASLine> lines;
	
	private Map<String, ASTransport> transports_map;
	
	private List<ASTransport> transports;
	
	private Map<String, ASUser> customers_map;
	
	private List<ASUser> customers;
	
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
	
	public void addStation(ASStation s) {
		stations.add(s);
		stations_map.put(s.getId(), s);
		for(CITISObserver cs : co)
			cs.CITISObjectAdded(this, s);
	}
	
	public void addLine(ASLine l) {
		lines.add(l);
		lines_map.put(l.getId(), l);
		for(CITISObserver cs : co)
			cs.CITISObjectAdded(this, l);
	}
	
	public void addTransport(ASTransport t) {
		transports.add(t);
		transports_map.put(t.getId(), t);
		for(CITISObserver cs : co)
			cs.CITISObjectAdded(this, t);
	}
	
	public void addUser(ASUser u) {
		customers.add(u);
		customers_map.put(u.getId(), u);
	}
	
	public ASLine searchLine(String _id) {
		if(lines_map.containsKey(_id))
			return lines_map.get(_id);
		return null;
	}
	
	public ASStation searchStation(String _id) {
		if(stations_map.containsKey(_id))
			return stations_map.get(_id);
		return null;
	}
	
	public ASTransport searchTransport(String _id) {
		if(transports_map.containsKey(_id))
			return transports_map.get(_id);
		return null;
	}
	
	public List<ASStation> getStations(){
		return Collections.unmodifiableList(stations);
	}
	public List<ASLine> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	public List<ASTransport> getTransports (){
		return Collections.unmodifiableList(transports);
	}
	
	public List<ASUser> getUsers() {
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
