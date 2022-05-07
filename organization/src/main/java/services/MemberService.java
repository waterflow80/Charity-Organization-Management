package services;

import java.util.List;

import entities.Member;
import exceptions.AddFailureException;
import exceptions.DeleteFailureException;
import exceptions.EntityNotFoundException;

public interface MemberService {
	public int addMember(Member member) throws AddFailureException;  // return the memberId of the added memeber
	public List<Member> getAllMembers() throws EntityNotFoundException;
	public Member getMemberById(int memberId) throws EntityNotFoundException;
	public List<Member> getMembersByRole(String role) throws EntityNotFoundException;
	public boolean deleteMemberById(int memberId) throws DeleteFailureException;
}
