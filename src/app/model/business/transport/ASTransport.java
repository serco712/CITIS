package app.model.business.transport;

import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.line.ASLine;

public class ASTransport extends CITISObject {
	
	private static int numTransports;
	
	private TransportType type;
	private ASLine line;
	private int time;
	private String enroll;
	private String model;
	private static final String TYPE_ID = "transport";
	
	public ASTransport(String id, String enroll, String model, int time, TransportType type, ASLine l) {
		super(id);
		
		if (time < 0)
			throw new IllegalArgumentException("El tiempo no puede ser negativo");
		else if (type == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		else if (l == null)
			throw new IllegalArgumentException("La lista no puede ser nula");
		
		this.type = type;
		this.time = time;
		this.enroll = enroll;
		this.model = model;
		line = l;	
	}

	@Override
	public int getAmount() {
		return numTransports;
	}
	
	@Override
	public void onEnter() {
		numTransports++;
	}
	
	@Override
	public void onDelete() {
		numTransports--;
	}
	
	public TransportType getType() {
		return type;
	}
	
	public int getTime() {
		return time;
	}
	
	public String getLine() {
		return line.getId();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(_id + ' ' + time + ' ' + type.toString() + ' ');
		str.append(line.getId());
		
		return str.toString();
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
	
	public String getEnrollment() {
		return enroll;
	}
	
	public String getModel() {
		return model;
	}
}
