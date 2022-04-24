package app.model.layers.integration.line;

import java.util.List;

import app.misc.TimeADT;
import app.misc.Triplet;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;

public interface LineDAO {
	public DTOLine findLine(String id);
	public void saveLine(DTOLine line);
	public DTOLine createLine(DTOLine line);
	public List<String> findRouteStations(String line_id);
	public List<ASLine> searchLines();
	public List<Triplet<ASLine, TimeADT, String>> findDepartures(List<String> route_ids);
}
