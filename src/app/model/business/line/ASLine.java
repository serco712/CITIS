package app.model.business.line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.misc.TimeADT;
import app.misc.Triplet;
import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.station.ASStation;
import app.model.business.transport.ASTransport;
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
		LineDatabaseDAO dao = new LineDatabaseDAO();
		return dao.searchLines();
	}

	public DTOLine createLine(DTOLine line) {
		LineDatabaseDAO dao = new LineDatabaseDAO();
		return dao.createLine(line);
	}

	public boolean findLine (String id) {
		LineDatabaseDAO dao = new LineDatabaseDAO();
		return dao.findLine(id) != null;
	}

	public List<Triplet<ASLine, TimeADT, String>> searchDepartureTimes(List<String> route_id) {
		LineDatabaseDAO dao = new LineDatabaseDAO();
		return dao.findDepartures(route_id);
	}
}
