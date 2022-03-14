package app.data;

import app.model.CITISMap;
import app.model.Transport;

public abstract class TransportFactory extends Factory {
	
	public TransportFactory(String type) {
		super(type);
	}
	
	public void createObject(String[] para, CITISMap cm) {
		cm.addTransport(createTransport(para, cm));
	}
	
	protected abstract Transport createTransport(String[] para, CITISMap cm);	
}
