package app.factories;

public class MainFactory {
	private Factory[] elems = {
		new StationFactory(),
		new LineFactory(),
		new TransportFactory(),
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
