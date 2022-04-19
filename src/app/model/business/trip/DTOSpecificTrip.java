package app.model.business.trip;

import java.sql.Timestamp;

public class DTOSpecificTrip {
	
	private String _id;
	private String _calendar;
	private Timestamp _departure;
	private String _sTripId;
	private String _lineName;
	
	public String getId() {
		return _id;
	}
	
	public String getCalendar() {
		return _calendar;
	}
	
	public Timestamp getDepartureTime() {
		return _departure;
	}
	
	public String getTripId() {
		return _sTripId;
	}
	
	public String getLineName() {
		return _lineName;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setCalendar(String calendar) {
		_calendar = calendar;
	}
	
	public void setTime(Timestamp time) {
		_departure = time;
	}
	
	public void setTripId(String tid) {
		_sTripId = tid;
	}
	
	public void setLineName(String name) {
		_lineName = name;
	}
}
