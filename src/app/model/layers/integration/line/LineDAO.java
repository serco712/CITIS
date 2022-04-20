package app.model.layers.integration.line;

import java.util.List;

import javax.sound.sampled.Line;

import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;

public interface LineDAO {
	public DTOLine findLine(String id);
	public void saveLine(DTOLine line);
	public DTOLine createLine(DTOLine line);
	public List<String> findRouteStations(String line_id);
	public List<ASLine> searchLines();
}
