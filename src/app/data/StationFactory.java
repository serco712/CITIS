package app.data;

import app.model.CITISMap;
import app.model.Station;

public abstract class StationFactory extends Factory {
	
	public StationFactory(String type) {
		super(type);
	}


	public void createObject(String[] para, CITISMap cm) {
		cm.addStation(createStation(para, cm));
	}
	
	protected abstract Station createStation (String[] para, CITISMap cm);
}
