package services;

import java.util.List;

import entities.Address;
import entities.Family;
import entities.FamilyMember;
import exceptions.AddFailureException;
import exceptions.DeleteFailureException;
import exceptions.EntityNotFoundException;

public interface FamilyService {
	public int addFamily(List<FamilyMember> members, Address address) throws AddFailureException; // return the id of the added family
	public List<Family> getAllFamilies() throws EntityNotFoundException;
	public List<FamilyMember> getAllMembersByFamilyId(int familyId) throws EntityNotFoundException;
	public boolean deleteFamilyById(int id) throws DeleteFailureException;
	public int addFamilyMember(FamilyMember member, int familyId) throws AddFailureException;
	public FamilyMember getFamilyMemberById(int familyMemberId) throws EntityNotFoundException;
	public boolean deleteFamilyMemberById(int memberId) throws DeleteFailureException;
	public Family getFamilyById(int familyId) throws EntityNotFoundException;
}
