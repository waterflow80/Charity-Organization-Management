package entities;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private int memberId;
	private String fisrtName;
	private String lastName;
	private String role;
	private String nationalIdNumber;
	private String phoneNumber;
	private Address address;
	private Photo profilePhoto;
	
	public Member() {
		super();
	}
	
	

	public Member(int memberId, String fisrtName, String lastName, String role, String nationalIdNumber,
			String phoneNumber, Address address, Photo profilePhoto) {
		super();
		this.memberId = memberId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.role = role;
		this.nationalIdNumber = nationalIdNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.profilePhoto = profilePhoto;
	}


	/**
	 * Constructor without memberId*/
	public Member(String fisrtName, String lastName, String role, String nationalIdNumber, String phoneNumber,
			Address address, Photo profilePhoto) {
		super();
		this.fisrtName = fisrtName;
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
	public String getFisrtName() {
		return fisrtName;
	}
	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
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
		return "Member [memberId=" + memberId + ", fisrtName=" + fisrtName + ", lastName=" + lastName + ", role=" + role
				+ ", nationalIdNumber=" + nationalIdNumber + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", profilePhoto=" + profilePhoto + "]";
	}


	
	
	
	
	


	
	
	
}
