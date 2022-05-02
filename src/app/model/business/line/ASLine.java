package app.model.business.line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.misc.Triplet;
import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.station.ASStation;
import app.model.layers.integration.line.LineDatabaseDAO;

public class ASLine extends CITISObject {
	
	private static int numLines;
	
	private List<String> stops;
	private TransportType transp;
	private static final String TYPE_ID = "line";
	private Color ctext;
	private Color cline;
	private String shortName;
	private String longName;
	private String agency;
	
	public ASLine () {
		super(" ");
	}
	
	public ASLine (DTOLine dto) {
		super(dto.getId());
		
		if (dto.getTransportType() == null)
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		
		stops = new ArrayList<>();
		shortName = dto.getShortName();
		longName = dto.getLongName();
		ctext = dto.getColorText();
		cline = dto.getLineColor();
		transp = dto.getTransportType();
		agency = dto.getAgency();
	}
	
	public int getNumStops() {
		return stops.size();
	}
	
	@Override
	public String toString() {
		return shortName;
	}
	
	@Override
	public int getAmount() {
		return numLines;
	}
	
	@Override
	public void onEnter() {
		numLines++;
	}
	
	public void onDelete() {
		numLines--;
	}
	
	public void addToLine(ASStation s) {
		stops.add(s.getId());
	}
	
	public boolean isInLine(ASStation s) {
		return stops.contains(s.getId());
	}
	
	public TransportType getTransport() {
		return transp;
	}
	
	public List<String> getStations() {
		return Collections.unmodifiableList(stops);
	}
	
	public Color getColorText() {
		return ctext;
	}
	
	public Color getColorLine() {
		return cline;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shname) {
		shortName = shname;
		LineDatabaseDAO.getInstance().
	}
	
	public String getLongName() {
		return longName;
	}
	
	public String getAgency() {
		return agency;
	}
	
	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
	
	public List<ASLine> searchLines() {
		return LineDatabaseDAO.getInstance().searchLines();
	}

	public DTOLine createLine(DTOLine line) {
		return LineDatabaseDAO.getInstance().createLine(line);
	}

	public boolean findLine (String id) {
		return LineDatabaseDAO.getInstance().findLine(id) != null;
	}

	public List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> searchDepartureTimes(String stop_id) {
		return LineDatabaseDAO.getInstance().findDepartures(stop_id);
	}
	
	public static void removeDepartureTime(ASLine as, TimeADT adt, String destiny, String calend_id) {
		LineDatabaseDAO.getInstance().removeDeparture(as, adt, destiny, calend_id);
	}
}
