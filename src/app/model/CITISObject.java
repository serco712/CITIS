package app.model;

public abstract class CITISObject {
	
	protected String _id;
	
	CITISObject(String id) {
		if (id == null)
			throw new IllegalArgumentException("El objeto debe tener id");
		else
			_id = id;
	}
	
	public String getId() {
		return _id;
	}
}
