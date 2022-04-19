package app.model.layers.integration.line;

import java.util.List;

import app.model.business.line.DTOLine;
import app.model.business.station.ASStation;

public interface LineDAO {
	public DTOLine findLine(String id);
	public void saveLine(DTOLine line);
	public DTOLine createLine(DTOLine line);
	public List<String> findRouteStations(String line_id);
}
