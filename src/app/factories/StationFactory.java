package app.factories;

import java.util.ArrayList;
import java.util.List;

import app.model.business.CITISMap;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;

public class StationFactory extends Factory {
	
	private static final String FACT_NAME = "station";
	
	public StationFactory() {
		super(FACT_NAME);
	}


	public void createObject(String[] para, CITISMap cm) {
		cm.addStation(createStation(para, cm));
	}
	
	protected ASStation createStation (String[] para, CITISMap cm) {
		List<String> l = new ArrayList<>();
		for(int i = 0; i < Integer.parseInt(para[6]); i++) {
			l.add(para[7 + i]);
		}
		List<ASLine> ll = new ArrayList<>();
		for (String s : l)
			ll.add(cm.searchLine(s));
		
		return new ASStation(para[1], para[2], Integer.parseInt(para[4]),
					Integer.parseInt(para[5]), TransportType.valueOf(para[3]), ll);
	}
}
