package app.data;

import java.util.ArrayList;
import java.util.List;

import app.model.CITISObject;
import app.model.Line;
import app.model.Station;
import app.model.TransportType;

public class LineFactory extends Factory {

	private static final String FACT_NAME = "line";
	
	public LineFactory() {
		super(FACT_NAME);
	}

	@Override
	protected CITISObject createObject(String[] para) {
		// Format: id num_stops stops transp_type
		List<String> ls = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(para[2]); i++) {
			ls.add(para[3 + i]);
		}
		// TODO search string in list cityObjects
		List<Station> lst = new ArrayList<>();
		return new Line (para[1], lst, TransportType.valueOf(para[para.length - 1]));
	}
}
