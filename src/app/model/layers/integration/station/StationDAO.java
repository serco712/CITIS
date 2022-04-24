package app.model.layers.integration.station;

import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;
import app.model.business.station.DTOStation;

public interface StationDAO {
	public DTOStation findStation(String id);
	public void saveStation(DTOStation station);
	public DTOStation createStation(DTOStation station);
	public List<ASStation> searchStations();
	public List<ASLine> searchLines(String id);
}
