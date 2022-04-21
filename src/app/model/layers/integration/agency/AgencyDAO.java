package app.model.layers.integration.agency;

import app.model.business.agency.DTOAgency;

public interface AgencyDAO {
	public DTOAgency findAgency(String id);
}
