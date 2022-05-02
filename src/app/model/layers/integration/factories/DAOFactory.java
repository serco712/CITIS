package app.model.layers.integration.factories;

import app.model.layers.integration.line.LineDAO;
import app.model.layers.integration.specifictrip.SpecificTripDAO;
import app.model.layers.integration.station.StationDAO;
import app.model.layers.integration.transport.TransportDAO;
import app.model.layers.integration.trip.TripDAO;
import app.model.layers.integration.user.UserDAO;

public abstract class DAOFactory {
	public abstract LineDAO createLineDAO();
	public abstract SpecificTripDAO createSpecificDAO();
	public abstract StationDAO createStationDAO();
	public abstract TransportDAO createTransportDAO();
	public abstract TripDAO createTripDAO();
	public abstract UserDAO createUserDAO();
}
