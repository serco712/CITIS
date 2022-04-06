package app.model.layers.integration.station;

import app.model.business.station.DTOStation;

public interface StationDAO {
	public DTOStation findStation(String id);
	public void saveStation(DTOStation station);
	public DTOStation createStation(DTOStation station);
}
