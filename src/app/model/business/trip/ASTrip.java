package app.model.business.trip;

import app.model.layers.integration.trip.TripDatabaseDAO;

public class ASTrip {
	
	private String _id;
	private String _name;
	private String _route_id;
	private String trip_notes;
	private String _stop_id;
	private String _departure_time;
	private int _stop_sequence;
	private String _stop_notes;
	
	public ASTrip() {
		
	}
	
	public ASTrip(DTOTrip trip) {
		_id = trip.get_id();
		_name = trip.get_name();
		_route_id = trip.get_route_id();
		trip_notes = trip.get_trip_notes();
		_stop_id = trip.get_stop_id();
		_departure_time = trip.get_departureTime();
		_stop_sequence = trip.get_stop_sequence();
		_stop_notes = trip.get_stop_notes();
	}

	public void createTrip(DTOTrip as) {
		TripDatabaseDAO dao = new TripDatabaseDAO();
		dao.createTrip(as);
		dao.createStopTime(as);
	}
}