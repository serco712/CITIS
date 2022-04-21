package app.model.business.agency;

public class DTOAgency {
	private String agency_name;
	private String agency_url;
	
	public String getAgencyName() {
		return agency_name;
	}
	
	public String getAgencyUrl() {
		return agency_url;
	}
	
	public void setAgencyName(String name) {
		agency_name = name;
	}
	
	public void setAgencyUrl(String url) {
		agency_url = url;
	}
}
