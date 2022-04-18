package app.model.business.station;

import java.util.List;

import app.model.business.TransportType;
import app.model.business.line.ASLine;

public class DTOStation {
	
	private String _id;
	private String _name;
	private ASStation _parent;
	private int xCoor, yCoor;
	private TransportType transport;
	private List<ASLine> lines ;
	private String _city;
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public ASStation getParent() {
		return _parent;
	}
	
	public int getXCoor() {
		return xCoor;
	}
	
	public int getYCoor() {
		return yCoor;
	}
	
	public TransportType getTransportType() {
		return transport;
	}
	
	public List<ASLine> getLines() {
		return lines;
	}
	
	public String getCity() {
		return _city;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setParent(ASStation st) {
		_parent = st;
	}
	
	public void setXCoor(int x) {
		xCoor = x;
	}
	
	public void setYCoor(int y) {
		yCoor = y;
	}

	public void setTransportType(TransportType tr) {
		transport = tr;
	}
	
	public void setLines (List<ASLine> ls) {
		lines = ls;
	}
	
	public void setCity (String city) {
		_city = city;
	}
}
