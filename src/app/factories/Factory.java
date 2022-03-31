package app.factories;

import app.model.business.CITISMap;

public abstract class Factory {
	
	protected String type;
	
	public Factory (String type) {
		if (type == null || type == "")
			throw new IllegalArgumentException("El tipo no puede ser nulo");
		
		this.type = type;
	}

	protected String getType() {
		return type;
	}
	
	public abstract void createObject(String[] para, CITISMap cm);
}
