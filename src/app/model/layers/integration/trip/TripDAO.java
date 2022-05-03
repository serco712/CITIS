package app.model.layers.integration.trip;

import java.util.List;

import app.model.business.trip.ASTrip;
import app.model.business.trip.DTOTrip;

public interface TripDAO {
	public DTOTrip findTrip(String id);
	public DTOTrip createTrip(DTOTrip trip);
	public DTOTrip createStopTime(DTOTrip trip);
	public int findLastSequence_Id(String trip);
	public List<ASTrip> listTrips();
}
