package app.factories;

import app.model.CITISMap;
import app.model.Line;
import app.model.TransportType;

public class LineFactory extends Factory {

	private static final String FACT_NAME = "line";
	
	public LineFactory() {
		super(FACT_NAME);
	}

	@Override
	public void createObject(String[] para, CITISMap cm) {
		// Format: id transp_type
		cm.addLine(new Line(para[1], TransportType.valueOf(para[2]), Integer.parseInt(para[3]),
				Integer.parseInt(para[4]), Integer.parseInt(para[4])));
	}
}
