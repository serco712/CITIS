package app.model.business.trip;

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
		_id = trip.getId();
		_name = trip.getName();
	
	}
	
	
}