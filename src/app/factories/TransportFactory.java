package app.factories;

import app.model.business.CITISMap;
import app.model.business.TransportType;
import app.model.business.transport.ASTransport;

public class TransportFactory extends Factory {
	
	private static final String FACT_NAME = "transport";
	
	public TransportFactory() {
		super(FACT_NAME);
	}

	public void createObject(String[] para, CITISMap cm) {
		cm.addTransport(createTransport(para, cm));
	}
	
	protected ASTransport createTransport(String[] para, CITISMap cm) {
		return new ASTransport(para[1], para[2], para[3], Integer.parseInt(para[4]),
				TransportType.valueOf(para[5]), cm.searchLine(para[6]));
	}
}
