package services;

import java.util.List;

import entities.Address;
import entities.Family;
import entities.FamilyMember;

public interface FamilyService {
	public int addFamily(List<FamilyMember> members, Address adress); // return the id of the added family
	public List<Family> getAllFamilies();
	public List<FamilyMember> getAllMembersByFamilyId(int familyId);
	public boolean deleteFamilyById(int id);
	public int addFamilyMember(FamilyMember member, int familyId);
	public FamilyMember getFamilyMemberById(int familyMemberId, int FamilyId);
	public boolean deleteFamilyMemberById(int memberId, int FamilyId);
}
