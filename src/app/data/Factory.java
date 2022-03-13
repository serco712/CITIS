package app.data;

import app.model.CITISMap;

public abstract class Factory extends MainFactory {
	
	protected String type;
	
	public Factory (String type) {
		this.type = type;
	}

	protected String getType() {
		return type;
	}
	
	protected abstract void createObject(String[] para, CITISMap cm);
}
