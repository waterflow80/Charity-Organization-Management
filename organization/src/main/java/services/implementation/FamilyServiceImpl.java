package services.implementation;

import java.util.List;

import dao.FamilyDAO;
import entities.Address;
import entities.Family;
import entities.FamilyMember;
import exceptions.AddFailureException;
import exceptions.DeleteFailureException;
import exceptions.EntityNotFoundException;
import services.FamilyService;

public class FamilyServiceImpl implements FamilyService {

	@Override
	public int addFamily(List<FamilyMember> members, Address address) throws AddFailureException {
		int familyId = FamilyDAO.addFamily(members, address);
		if (familyId == 0)
			throw new AddFailureException("Cannot add family !");
		return familyId;
	}

	@Override
	public List<Family> getAllFamilies() throws EntityNotFoundException {
		List<Family> families = FamilyDAO.getAllFamilies();
		if (families == null)
			throw new EntityNotFoundException("Cannot get the list of all families !");
		return families;
	}

	@Override
	public List<FamilyMember> getAllMembersByFamilyId(int familyId) throws EntityNotFoundException {
		List<FamilyMember> members = FamilyDAO.getAllFamilyMembersByFamilyId(familyId);
		if (members == null)
			throw new EntityNotFoundException("Cannot get the member of the family with id " + familyId);
		return members;
	}

	@Override
	public boolean deleteFamilyById(int id) throws DeleteFailureException {
		boolean familyDeleted = FamilyDAO.deleteFamilyById(id);
		if (!familyDeleted)
			throw new DeleteFailureException("Cannot delete the family with the id " + id);
		return true;
	}

	@Override
	public int addFamilyMember(FamilyMember member, int familyId) throws AddFailureException {
		int memberId = FamilyDAO.addFamilyMember(member, familyId);
		if (memberId == 0)
			throw new AddFailureException("Cannot add member: " + member.getFirstName() + " " + member.getLastName());
		return memberId;
	}

	@Override
	public FamilyMember getFamilyMemberById(int familyMemberId) throws EntityNotFoundException {
		FamilyMember member = FamilyDAO.getFamilyMemberById(familyMemberId);
		if (member == null)
			throw new EntityNotFoundException("Failed to get the family member with id: " + familyMemberId);
		return member;
	}

	@Override
	public boolean deleteFamilyMemberById(int memberId) throws DeleteFailureException {
		boolean memberDeleted = FamilyDAO.deleteFamilyMemberById(memberId);
		if (!memberDeleted)
			throw new DeleteFailureException("Cannot delete family member with id: " + memberId);
		return true;
	}

	@Override
	public Family getFamilyById(int familyId) throws EntityNotFoundException {
		Family family = FamilyDAO.getFamilyById(familyId);
		if (family == null)
			throw new EntityNotFoundException("Cannot find family with id: " + familyId);
		return family;
	}

	
}
