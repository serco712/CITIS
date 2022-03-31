package app.model.business.trip;

import app.misc.TimeADT;

public class ASSpecificTrip extends ASTrip {
	
	private TimeADT _departure;
	private String _sTripId;

	public ASSpecificTrip(String id, String name, TimeADT time,
				String speTripId) {
		super(id, name);
		_departure = time;
		_sTripId = speTripId;
	}

}
