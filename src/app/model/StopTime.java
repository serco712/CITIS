package app.model;

import app.misc.TimeADT;

public class StopTime {
	private int _seq;
	private TimeADT _time;
	private Station _station;
	private Trip _trip;
	
	public StopTime(TimeADT time, int seq, Station station, Trip trip) {
		_seq = seq;
		_time = time;
		_station = station;
		_trip = trip;
	}
	
	public String getTime() {
		return _time.toString();
	}
}
