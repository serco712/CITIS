package app.model.layers.integration.transport;

import app.model.business.transport.DTOTransport;

public interface TransportDAO {
	public DTOTransport findTransport(String id);
	public void saveTransport(DTOTransport transport);
	public DTOTransport createTransport(DTOTransport transport);
}
