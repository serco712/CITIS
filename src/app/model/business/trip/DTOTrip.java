package app.model.business.trip;

public class DTOTrip {
	
	private String _id;
	private String _name;
	private String _notes;
	private String _line;
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getNotes() {
		return _notes;
	}
	
	public String getLine() {
		return _line;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setNotes(String notes) {
		_notes = notes;
	}
	
	public void setLine(String line) {
		_line = line;
	}
}
