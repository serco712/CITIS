package app.model.business.line;

import java.awt.Color;

import java.util.List;

import app.model.business.TransportType;
import app.model.business.station.ASStation;
import app.model.business.transport.ASTransport;

public class DTOLine {

	private List<ASStation> stops;
	
	private List<ASTransport> transports;
	
	private TransportType transp;
	
	private Color c;
	
	public List<ASStation> getStops() {
		return stops;
	}
	
	public List<ASTransport> getTransports() {
		return transports;
	}
	
	public TransportType getTransportType() {
		return transp;
	}
	
	public Color getColor () {
		return c;
	}
	
	public void setStops(List<ASStation> ls) {
		stops = ls;
	}
	
	public void setTransports(List<ASTransport> lt) {
		transports = lt;
	}
	
	public void setTransportType(TransportType tr) {
		transp = tr;
	}
	
	public void setColor(Color co) {
		c = co;
	}
}
