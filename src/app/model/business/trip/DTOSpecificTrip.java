package app.model.business.trip;

import app.misc.TimeADT;

public class DTOSpecificTrip {
	
	private String _id;
	
	private String _name;
	
	private TimeADT _departure;
	
	private String _sTripId;
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public TimeADT getDepartureTime() {
		return _departure;
	}
	
	public String getTripId() {
		return _sTripId;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setTime(TimeADT time) {
		_departure = time;
	}
	
	public void setTripId(String tid) {
		_sTripId = tid;
	}
}
