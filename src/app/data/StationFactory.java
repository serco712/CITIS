package app.data;

import app.model.CITISObject;

public class StationFactory extends Factory {
	
	public StationFactory(String type) {
		super(type);
	}

	@Override
	protected CITISObject createObject(String[] para) {
		return null;
	}

}
