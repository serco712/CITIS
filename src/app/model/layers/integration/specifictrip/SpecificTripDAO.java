package app.model.layers.integration.specifictrip;

import app.model.business.trip.DTOSpecificTrip;

public interface SpecificTripDAO {
	public DTOSpecificTrip findSpecificTrip(String id);
	public void saveSpecificTrip(DTOSpecificTrip sTrip);
	public DTOSpecificTrip createSpecificTrip(DTOSpecificTrip sTrip);
}
