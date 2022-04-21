package app.model.business.agency;

import app.model.layers.integration.agency.AgencyDatabaseDAO;

public class ASAgency {
	private String agency_name;
	private String agency_url;
	
	public ASAgency() {
		 
	}
	
	public ASAgency(DTOAgency agency) {
		agency_name = agency.getAgencyName();
		agency_url = agency.getAgencyUrl();
	}
	
	public String getAgencyName() {
		return agency_name;
	}
	
	public String getAgencyUrl() {
		return agency_url;
	}

	public boolean findAgency(String name) {
		AgencyDatabaseDAO dao = new AgencyDatabaseDAO();
		return dao.findAgency(name) != null;
	}
}
