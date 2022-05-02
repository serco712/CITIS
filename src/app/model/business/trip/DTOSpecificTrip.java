package app.model.business.trip;


public class DTOSpecificTrip {
	
	private String _id; 
	private String _name;  
	private String _route_id; 
	private String _trip_notes;  
	private String _stop_id;
	private String _departureTime;
	private int _stop_sequence;
	private String _stop_notes;
	private String _st_id;
	private String _calendar_id;
	
	public String getCalendarId() {
		return _calendar_id;
	}
	
	public void setCalendarId(String cal) {
		_calendar_id = cal;
	}
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
	}
	
	public String get_route_id() {
		return _route_id;
	}
	
	public void set_route_id(String _route_id) {
		this._route_id = _route_id;
	}
	
	public String get_trip_notes() {
		return _trip_notes;
	}
	
	public void set_trip_notes(String _trip_notes) {
		this._trip_notes = _trip_notes;
	}
	
	public String get_stop_id() {
		return _stop_id;
	}
	
	public void set_stop_id(String _stop_id) {
		this._stop_id = _stop_id;
	}
	
	public String get_departureTime() {
		return _departureTime;
	}
	
	public void set_departureTime(String _departureTime) {
		this._departureTime = _departureTime;
	}
	
	public int get_stop_sequence() {
		return _stop_sequence;
	}
	
	public void set_stop_sequence(int _stop_sequence) {
		this._stop_sequence = _stop_sequence;
	}
	
	public String get_stop_notes() {
		return _stop_notes;
	}
	
	public void set_stop_notes(String _stop_notes) {
		this._stop_notes = _stop_notes;
	}

	public String get_st_id() {
		return _st_id;
	}

	public void set_st_id(String _st_id) {
		this._st_id = _st_id;
	}
}
