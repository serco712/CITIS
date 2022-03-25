package app.factories;

public class MainFactory {
	private Factory[] elems = {
		new LineFactory(),
		new BusStationFactory(),
		new SubwayStationFactory(),
		new TrainStationFactory(),
		new BusFactory(),
		new SubwayFactory(),
		new TrainFactory(),
		new CustomerFactory()
	};
	
	public Factory searchFactory(String s) {
		for (Factory f : elems) {
			if(f.getType().equals(s))
				return f;
		}
		return null;
	}
}
