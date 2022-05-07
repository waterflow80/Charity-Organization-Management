package entities;

public class OrganizationInfo {
	private String digitalSignature;
	private String inormation;
	public OrganizationInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrganizationInfo(String digitalSignature, String inormation) {
		super();
		this.digitalSignature = digitalSignature;
		this.inormation = inormation;
	}
	public String getDigitalSignature() {
		return digitalSignature;
	}
	public void setDigitalSignature(String digitalSignature) {
		this.digitalSignature = digitalSignature;
	}
	public String getInormation() {
		return inormation;
	}
	public void setInormation(String inormation) {
		this.inormation = inormation;
	}
	
	
	
	
}
