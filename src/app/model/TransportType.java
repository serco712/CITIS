package app.model;

public enum TransportType {
	
	TRAIN(3, "train", "train_station"), BUS(1.5, "bus", "bus_station"), SUBWAY(2, "subway", "subway_station");
	
	private double basePrice;
	private String idStation;
	
	private TransportType (double baseTicketPrice, String name, String idStation) {
		basePrice = baseTicketPrice;
		this.idStation = idStation;
	}
	
	public double getPrice () {
		return basePrice;
	}
	
	public String getIdStation() {
		return idStation;
	}
}
