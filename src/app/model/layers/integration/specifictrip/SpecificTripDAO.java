package app.model.layers.integration.specifictrip;

import java.util.List;

import app.model.business.trip.DTOSpecificTrip;

public interface SpecificTripDAO {
	public DTOSpecificTrip findSpecificTrip(String id);
	public DTOSpecificTrip createSpecificTrip(DTOSpecificTrip sTrip);
	public List<String> findCalendarIds();
}
