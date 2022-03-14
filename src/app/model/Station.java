package app.model;

import java.util.Collections;
import java.util.List;

public abstract class Station extends CITISObject {
	
	private String name;
	private int xCoor, yCoor;
	TransportType transport;
	private List<Line> lines;
	
	public Station(String id, String name, int x, int y,
			TransportType t, List<Line> l) {
		super(id);
		
		if (name == null || name == "")
			throw new IllegalArgumentException("El nombre no puede ser nulo");
		else if (t == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		else if (l == null)
			throw new IllegalArgumentException("La lista de líneas no puede ser nula");
		//TODO ver las coordenadas del mapa para lanzar excepción si se salen
		
		this.name = name;
		xCoor = x;
		yCoor = y;
		transport = t;
		lines = l;
		for (Line li : lines)
			li.addToLine(this);
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getX() {
		return xCoor;
	}
	
	public int getY() {
		return yCoor;
	}
	
	public TransportType getTransport() {
		return transport;
	}
	
	public int getNumLines () {
		return lines.size();
	}
	
	public List<Line> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(_id + ' ' + name + ' ' + transport.toString() + ' ');
		for(Line l : lines)
			str.append(l.getId() + ' ');
		return str.toString();
	}
}
