package entities;

public class Admin {
	private int adminId;
	private String firstName;
	private String lastName;
	private String nationalIdNumber;
	private String phoneNumber;
	private String role;
	private Address address;
	
	
	public Admin() {
		super();
	}
	
	public Admin(int adminId, String firstName, String lastName, String nationalIdNumber, String phoneNumber,
			String role, Address address) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalIdNumber = nationalIdNumber;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.address = address;
	}
	
	
	/**
	 * Constructor without the adminId*/
	public Admin(String firstName, String lastName, String nationalIdNumber, String phoneNumber, String role,
			Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalIdNumber = nationalIdNumber;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.address = address;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", nationalIdNumber=" + nationalIdNumber + ", phoneNumber=" + phoneNumber + ", role=" + role
				+ ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + adminId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((nationalIdNumber == null) ? 0 : nationalIdNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (adminId != other.adminId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nationalIdNumber == null) {
			if (other.nationalIdNumber != null)
				return false;
		} else if (!nationalIdNumber.equals(other.nationalIdNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	
	
	
	
	
}
