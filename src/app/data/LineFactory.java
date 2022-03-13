package app.data;

import app.model.CITISObject;
import app.model.Line;
import app.model.TransportType;

public class LineFactory extends Factory {

	private static final String FACT_NAME = "line";
	
	public LineFactory() {
		super(FACT_NAME);
	}

	@Override
	protected CITISObject createObject(String[] para) {
		// Format: id num_stops stops transp_type
		return new Line (para[1], TransportType.valueOf(para[2]));
	}
}
