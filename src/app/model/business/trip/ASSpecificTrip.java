package app.model.business.trip;

import app.misc.TimeADT;

public class ASSpecificTrip extends ASTrip {

	private String _sTripId;

	public ASSpecificTrip(DTOSpecificTrip st) {
		super(createTransfer(st));
		_sTripId = st.get_st_id();
	}
	
	private static DTOTrip createTransfer(DTOSpecificTrip trip) {
		DTOTrip dto = new DTOTrip();
		dto.set_id(trip.get_id());
		dto.set_name(trip.get_name());
		dto.set_route_id(trip.get_route_id());
		dto.set_trip_notes(trip.get_trip_notes());
		dto.set_stop_id(trip.get_stop_id());
		dto.set_departureTime(trip.get_departureTime());
		dto.set_stop_sequence(trip.get_stop_sequence());
		dto.set_stop_notes(trip.get_stop_notes());
		return dto;
	}
}
