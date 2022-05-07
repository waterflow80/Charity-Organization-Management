package entities;

import java.util.ArrayList;
import java.util.List;
import entities.FamilyMember;

public class Family {
	private int familyId;
	private List<FamilyMember> familyMembers = new ArrayList<FamilyMember> ();
	private Address address;
	
	
	public Family() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Family(int familyId, List<FamilyMember> familyMembers, Address adress) {
		super();
		this.familyId = familyId;
		this.familyMembers = familyMembers;
		this.address = adress;
	}
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public List<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	public void setFamilyMembers(List<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Family [familyId=" + familyId + ", familyMembers=" + familyMembers + ", adress=" + address + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
