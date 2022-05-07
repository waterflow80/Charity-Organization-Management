package entities;


public class Member {
	private Integer memberId;
	private String firstName;
	private String lastName;
	private String role;
	private String nationalIdNumber;
	private String phoneNumber;
	private Address address;
	private Photo profilePhoto;
	
	public Member() {
		super();
	}
	
	

	public Member(int memberId, String firstName, String lastName, String role, String nationalIdNumber,
			String phoneNumber, Address address, Photo profilePhoto) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.nationalIdNumber = nationalIdNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.profilePhoto = profilePhoto;
	}


	/**
	 * Constructor without memberId*/
	public Member(String firstName, String lastName, String role, String nationalIdNumber, String phoneNumber,
			Address address, Photo profilePhoto) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.nationalIdNumber = nationalIdNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.profilePhoto = profilePhoto;
	}



	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}


	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}


	public Photo getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Photo profilePhoto) {
		this.profilePhoto = profilePhoto;
	}



	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ ", nationalIdNumber=" + nationalIdNumber + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", profilePhoto=" + profilePhoto + "]";
	}



	
	
	
	
	
	


	
	
	
}
