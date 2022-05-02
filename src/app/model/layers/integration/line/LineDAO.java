package app.model.layers.integration.line;

import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;

public interface LineDAO {
	public DTOLine findLine(String id);
	public void saveLine(DTOLine line);
	public DTOLine createLine(DTOLine line);
	public List<String> findRouteStations(String line_id);
	public List<ASLine> searchLines();
	public void removeDeparture(ASLine as, TimeADT adt, String destiny, String calend_id);
	List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> findDepartures(String stop_id);
	public void updateShortName(String oldName, String newName);
}
