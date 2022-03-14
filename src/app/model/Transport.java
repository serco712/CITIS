package app.model;

import java.util.Collections;
import java.util.List;

public abstract class Transport extends CITISObject {
	
	private static int numTransports;
	
	private TransportType type;
	
	private List<Line> lines;
	
	private int time;

	public Transport(String id, int time, TransportType type, List<Line> l) {
		super(id);
		
		if (time < 0)
			throw new IllegalArgumentException("El tiempo no puede ser negativo");
		else if (type == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		else if (l == null)
			throw new IllegalArgumentException("La lista no puede ser nula");
		
		this.type = type;
		this.time = time;
		lines = l;
		for(Line li : lines)
			li.addToLine(this);		
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
	
	public int getNumLines() {
		return lines.size();
	}
	
	public List<Line> getLines(){
		return Collections.unmodifiableList(lines);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(_id + ' ' + time + ' ' + type.toString() + ' ');
		for(Line l : lines)
			str.append(l.getId() + ' ');
		return str.toString();
	}
}
