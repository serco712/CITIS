package app.model;

public enum TransportType {
	
	TRAIN(3), BUS(1.5), SUBWAY(2);
	
	double basePrice;
	
	private TransportType (double baseTicketPrice) {
		basePrice = baseTicketPrice;
	}
	
	public double getPrice () {
		return basePrice;
	}
}
