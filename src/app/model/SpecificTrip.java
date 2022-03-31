package app.model;

import app.misc.TimeADT;

public class SpecificTrip extends Trip {
	
	private TimeADT _departure;
	private String _sTripId;

	public SpecificTrip(String id, String name, TimeADT time,
				String speTripId) {
		super(id, name);
		_departure = time;
		_sTripId = speTripId;
	}

}
