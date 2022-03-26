package app.factories;

import app.model.CITISMap;
import app.model.Transport;

public class TransportFactory extends Factory {
	
	private static final String FACT_NAME = "transport";
	
	public TransportFactory() {
		super(FACT_NAME);
	}

	public void createObject(String[] para, CITISMap cm) {
		cm.addTransport(createTransport(para, cm));
	}
	
	protected Transport createTransport(String[] para, CITISMap cm) {
		return new Transport(para[1], );
	}
}
