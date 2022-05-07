package entities;

import java.util.List;

public class FamilyMember {
	private int familyMemberId;
	private String firstName;
	private String lastName;
	private int age;
	private String profession;
	private String nationalCardNumber;
	private String phoneNumber;
	private Float income;
	private boolean isParent; // True if the member is parent (mother or father) | false otherwise
	
	
	
	public FamilyMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public FamilyMember(int familyMemberId, String firstName, String lastName, int age, String profession,
			String nationalCardNumber, String phoneNumber, Float income, boolean isParent) {
		super();
		this.familyMemberId = familyMemberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.profession = profession;
		this.nationalCardNumber = nationalCardNumber;
		this.phoneNumber = phoneNumber;
		this.income = income;
		this.isParent = isParent;
	}


	public FamilyMember(String firstName, String lastName, int age, String profession, String nationalCardNumber,
			String phoneNumber, Float income, boolean isParent) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.profession = profession;
		this.nationalCardNumber = nationalCardNumber;
		this.phoneNumber = phoneNumber;
		this.income = income;
		this.isParent = isParent;
	}


	public int getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(int familyMemberId) {
		this.familyMemberId = familyMemberId;
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

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getNationalCardNumber() {
		return nationalCardNumber;
	}

	public void setNationalCardNumber(String nationalCardNumber) {
		this.nationalCardNumber = nationalCardNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public boolean isParent() {
		return isParent;
	}


	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}


	@Override
	public String toString() {
		return "FamilyMember [familyMemberId=" + familyMemberId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", profession=" + profession + ", nationalCardNumber=" + nationalCardNumber
				+ ", phoneNumber=" + phoneNumber + ", income=" + income + ", isParent=" + isParent + "]";
	}

	
	
	
}
