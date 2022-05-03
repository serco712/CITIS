package app.model.layers.integration.line;

import java.util.List;

import app.misc.Pair;
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
	public void removeDeparture(ASLine as, TimeADT adt, String destiny, String calend_id, String st_id, String strip_id);
	public List<Triplet<Pair<ASLine, TimeADT>, Pair<String, String>, String>> findDepartures(String stop_id);
	public void updateShortName(String oldName, String newName);
}
