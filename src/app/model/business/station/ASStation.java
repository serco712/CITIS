package app.model.business.station;

import java.util.Collections;
import java.util.List;

import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.line.ASLine;

public class ASStation extends CITISObject {
	
	private static final String TYPE_ID = "station";
	
	private static int numStations;
	
	private String _id;
	private String _name;
	private ASStation _parent;
	private int xCoor, yCoor;
	private TransportType transport;
	private List<ASLine> lines;
	private String _city;
	
	public ASStation(String id, String name, int x, int y,
			TransportType t, List<ASLine> l) {
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
		for (ASLine li : lines)
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
	
	public List<ASLine> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(_id + ' ' + _name + ' ' + transport.toString() + ' ');
		for(ASLine l : lines)
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
