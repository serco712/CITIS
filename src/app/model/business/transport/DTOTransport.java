package app.model.business.transport;

import app.model.business.TransportType;
import app.model.business.line.ASLine;

public class DTOTransport {
	
	private String _id;
	
	private TransportType type;
	
	private ASLine line;
	
	private String enroll;
	
	private String model;
	
	public String getId() {
		return _id;
	}
	
	public TransportType getTransportType() {
		return type;
	}
	
	public ASLine getLine() {
		return line;
	}
	
	public String getEnroll() {
		return enroll;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setLine(ASLine l) {
		line = l;
	}
	
	public void setEnroll(String enrl) {
		enroll = enrl;
	}
	
	public void setModel(String ml) {
		model = ml;
	}
}
