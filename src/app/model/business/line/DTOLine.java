package app.model.business.line;

import java.awt.Color;

import java.util.List;

import app.model.business.TransportType;
import app.model.business.station.ASStation;
import app.model.business.transport.ASTransport;

public class DTOLine {
	
	private String id;
	
	private String shortName;
	
	private String longName;
	
	private List<ASStation> stops;
	
	private TransportType transp;
	
	private Color ctext;
	
	private Color cline;
	
	private String agency;
	
	public String getId() {
		return id;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getLongName() {
		return longName;
	}
	
	public List<ASStation> getStops() {
		return stops;
	}
	
	public TransportType getTransportType() {
		return transp;
	}
	
	public Color getColorText () {
		return ctext;
	}
	
	public Color getLineColor() {
		return cline;
	}
	
	public String getAgency() {
		return agency;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setShortName(String sn) {
		this.shortName = sn;
	}
	
	public void setLongName(String ln) {
		this.longName = ln;
	}
	
	public void setStops(List<ASStation> ls) {
		stops = ls;
	}
	
	public void setTransportType(TransportType tr) {
		transp = tr;
	}
	
	public void setColorText(Color co) {
		ctext = co;
	}
	
	public void setColorLine(Color co) {
		cline = co;
	}

	public void setAgency(String ag) {
		agency = ag;
	}
}
