package services.implementation;

import java.util.List;

import dao.MemberDAO;
import entities.Member;
import exceptions.AddFailureException;
import exceptions.DeleteFailureException;
import exceptions.EntityNotFoundException;
import services.MemberService;

public class MemberServiceImpl implements MemberService {

	@Override
	public int addMember(Member member) throws AddFailureException {
		int memberId = MemberDAO.addMember(member);
		if (memberId == 0)
			throw new AddFailureException("Cannot add member: " + member.getFirstName() + " " + member.getLastName());
		return memberId;
	}

	@Override
	public List<Member> getAllMembers() throws EntityNotFoundException {
		List<Member> members = MemberDAO.getAllMembers();
		if (members == null)
			throw new EntityNotFoundException("Cannot get the list of all members !");
		return members;
	}

	@Override
	public Member getMemberById(int memberId) throws EntityNotFoundException {
		Member member = MemberDAO.getMemberById(memberId);
		if (member == null)
			throw new EntityNotFoundException("Cannot find member with id: " + member.getMemberId());
		return member;
	}

	@Override
	public List<Member> getMembersByRole(String role) throws EntityNotFoundException {
		List<Member> members = MemberDAO.getMembersByRole(role);
		if (members == null)
			throw new EntityNotFoundException("Cannot get members with role: " + role);
		return members;
	}

	@Override
	public boolean deleteMemberById(int memberId) throws DeleteFailureException {
		boolean memberDeleted = MemberDAO.deleteMemberById(memberId);
		if (!memberDeleted)
			throw new DeleteFailureException("Cannot delete the member with the id: " + memberId);
		return true;
	}


	
	
}
