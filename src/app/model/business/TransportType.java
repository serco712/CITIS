package app.model.business;

public enum TransportType {
	
	TRAIN(3, "train", "train_station", "trenes"), BUS(1.5, "bus", "bus_station", "autobuses"), 
		SUBWAY(2, "subway", "subway_station", "metro");
	
	private double basePrice;
	
	private String idStation;
	
	private String translation;
	
	private TransportType (double baseTicketPrice, String name, String idStation, String tr) {
		basePrice = baseTicketPrice;
		this.idStation = idStation;
		translation = tr;
	}
	
	public double getPrice () {
		return basePrice;
	}
	
	public String getIdStation() {
		return idStation;
	}
	
	public String getTranslation() {
		return translation;
	}
}
