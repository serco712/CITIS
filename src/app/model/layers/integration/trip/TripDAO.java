package app.model.layers.integration.trip;

import app.model.business.trip.DTOTrip;

public interface TripDAO {
	public DTOTrip findTrip(String id);
	public void saveTrip(DTOTrip trip);
	public DTOTrip createTrip(DTOTrip trip);
	public DTOTrip createStopTime(DTOTrip trip);
}
