package app.data;

import java.util.ArrayList;
import java.util.List;

import app.model.Bus;
import app.model.CITISMap;
import app.model.Line;
import app.model.Transport;

public class BusFactory extends TransportFactory {

	private static final String FACT_NAME = "bus";
	
	public BusFactory() {
		super(FACT_NAME);
	}

	@Override
	protected Transport createTransport(String[] para, CITISMap cm) {
		// Format: id  time line
		List<String> l = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(para[4]); i++)
			l.add(para[5 + i]);
		List<Line> ll = new ArrayList<>();
		for(String s: l)
			ll.add(cm.searchLine(s));
		return new Bus(para[1], Integer.parseInt(para[2]), ll);
	}	
}
