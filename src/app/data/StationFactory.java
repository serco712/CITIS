package app.data;

import app.model.CITISObject;
import app.model.Station;

public abstract class StationFactory extends Factory {
	
	public StationFactory(String type) {
		super(type);
	}

	@Override
	protected CITISObject createObject(String[] para) {
		return createStation(para);
	}
	
	protected abstract Station createStation (String[] para);
}
