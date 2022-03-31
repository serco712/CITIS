package app.factories;

import app.model.business.CITISMap;
import app.model.business.Line;
import app.model.business.TransportType;
import app.model.business.line.ASLine;

public class LineFactory extends Factory {

	private static final String FACT_NAME = "line";
	
	public LineFactory() {
		super(FACT_NAME);
	}

	@Override
	public void createObject(String[] para, CITISMap cm) {
		// Format: id transp_type
		cm.addLine(new ASLine(para[1], TransportType.valueOf(para[2]), Integer.parseInt(para[3]),
				Integer.parseInt(para[4]), Integer.parseInt(para[4])));
	}
}
