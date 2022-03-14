package app.data;

import java.util.ArrayList;
import java.util.List;

import app.model.CITISMap;
import app.model.Line;
import app.model.Subway;
import app.model.Transport;

public class SubwayFactory extends TransportFactory {

	private static final String FACT_NAME = "subway";
	
	public SubwayFactory() {
		super(FACT_NAME);
	}

	@Override
	protected Transport createTransport(String[] para, CITISMap cm) {
		// Format: id  time num_Lines line
		List<String> l = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(para[3]); i++)
			l.add(para[4 + i]);
		List<Line> ll = new ArrayList<>();
		for(String s: l)
			ll.add(cm.searchLine(s));
		return new Subway(para[1], Integer.parseInt(para[2]), ll);
	}	
}