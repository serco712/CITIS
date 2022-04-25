package app.model.business.trip;

public class DTOTrip {
	
	private String _id; // 
	private String _name; // 
	private String _route_id; //
	private String _trip_notes; // 
	private String _stop_id;
	private String _departureTime;
	private int _stop_sequence;
	private String _stop_notes;
	
	public int getStopSeq() {
		return _stop_sequence;
	}
	
	public String getStopNotes() {
		return _stop_notes;
	}
	
	public String getStopId() {
		return _stop_id;
	}
	
	public String getDepartureTime() {
		return _departureTime;
	}
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getTripNotes() {
		return _trip_notes;
	}
	
	public String getLine() {
		return _route_id;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setNotes(String notes) {
		_notes = notes;
	}
	
	public void setLine(String line) {
		_line = line;
	}
}
