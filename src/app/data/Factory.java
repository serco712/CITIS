package app.data;

import app.model.CITISObject;

public abstract class Factory {
	private Factory[] elems = {
		new LineFactory(),
		new BusStationFactory()
	};
	
	protected String type;
	
	public Factory (String type) {
		this.type = type;
	}
	
	public Factory searchFactory(String s) {
		for (Factory f : elems) {
			if(f.getType().equals(s))
				return f;
		}
		return null;
	}

	protected String getType() {
		return type;
	}
	
	protected abstract CITISObject createObject(String[] para);
}
