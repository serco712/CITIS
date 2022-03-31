package app.model.business;

public abstract class CITISObject {
	
	protected String _id;
	
	public CITISObject(String id) {
		if (id == null || id == "")
			throw new IllegalArgumentException("El objeto debe tener id");
		else
			_id = id;
	}
	
	public String getId() {
		return _id;
	}
	
	public abstract void onEnter();
	public abstract void onDelete();
	public abstract int getAmount();
	public abstract String getTypeId();
}