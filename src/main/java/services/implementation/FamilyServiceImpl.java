package services.implementation;

import java.util.List;

import entities.Address;
import entities.Family;
import entities.FamilyMember;
import services.FamilyService;

public class FamilyServiceImpl implements FamilyService {

	@Override
	public int addFamily(List<FamilyMember> members, Address adress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Family> getAllFamilies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FamilyMember> getAllMembersByFamilyId(int familyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFamilyById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addFamilyMember(FamilyMember member, int familyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FamilyMember getFamilyMemberById(int familyMemberId, int FamilyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFamilyMemberById(int memberId, int FamilyId) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
