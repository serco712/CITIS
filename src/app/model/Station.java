package app.model;

import java.util.Collections;
import java.util.List;

public class Station extends CITISObject {
	
	private static final String TYPE_ID = "station";
	
	private static int numStations;
	
	private String _id;
	private String _name;
	private Station _parent;
	private int xCoor, yCoor;
	private TransportType transport;
	private List<Line> lines;
	
	public Station(String id, String name, int x, int y,
			TransportType t, List<Line> l) {
		super(id);
		
		if (name == null || name == "")
			throw new IllegalArgumentException("El nombre no puede ser nulo");
		else if (t == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		else if (l == null)
			throw new IllegalArgumentException("La lista de l�neas no puede ser nula");
		//TODO ver las coordenadas del mapa para lanzar excepci�n si se salen
		
		_id = id;
		_name = name;
		xCoor = x;
		yCoor = y;
		transport = t;
		lines = l;
		for (Line li : lines)
			li.addToLine(this);
	}
	
	
	public String getName() {
		return _name;
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
		str.append(_id + ' ' + _name + ' ' + transport.toString() + ' ');
		for(Line l : lines)
			str.append(l.getId() + ' ');
		return str.toString();
	}


	@Override
	public void onEnter() {
		numStations++;
	}


	@Override
	public void onDelete() {
		numStations--;
	}


	@Override
	public int getAmount() {
		return numStations;
	}


	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
}
