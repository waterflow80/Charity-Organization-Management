package entities;

public class Donor {
	private int donorId;
	private String firstName;
	private String lastName;
	private String nationalIdNumber;
	private String position; // position can be individual or organization
	
	public Donor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Donor(int donorId, String firstName, String lastName, String nationalIdNumber, String position) {
		super();
		this.donorId = donorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalIdNumber = nationalIdNumber;
		this.position = position;
	}
	
	
	/**
	 * Constructor without the donorId*/
	public Donor(String firstName, String lastName, String nationalIdNumber, String position) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalIdNumber = nationalIdNumber;
		this.position = position;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNationalIdNumber() {
		return nationalIdNumber;
	}
	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", nationalIdNumber=" + nationalIdNumber + ", position=" + position + "]";
	}
	
	
	
	
}
