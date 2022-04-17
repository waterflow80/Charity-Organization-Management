package services;

import java.util.List;

import entities.Member;

public interface MemberService {
	public int addMember(Member member);  // return the memberId of the added memeber
	public List<Member> getAllMembers();
	public Member getMemberById(int id);
	public Member getMemberByRole(String role);
	public boolean deleteMemberById(int id);
}
