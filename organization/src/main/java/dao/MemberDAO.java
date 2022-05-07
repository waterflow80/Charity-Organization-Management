package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entities.Account;
import entities.Address;
import entities.Member;
import utils.DBConnect;

/**
 * Handles the member's (of the organization) data from and to the database*/
public class MemberDAO {
	private static Connection connection = DBConnect.getInstance();
	
	/**
	 * Save the member to the database. Return the generated id | 0 when fails*/
	public static int addMember(Member member) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return 0; // Failed to get a connection instance
		}
		
		// Query
		String query = "INSERT INTO Member(firstName, lastName, nationalIdNumber, role, addressId, phoneNumber) VALUES "
				+ "(?,?,?,?,?,?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, member.getFirstName());
			ps.setString(2, member.getLastName());
			ps.setString(3, member.getNationalIdNumber());
			ps.setString(4, member.getRole());
			int addressId = AddressDAO.addAddress(member.getAddress()); // Saving the member's address and getting the addressId
			ps.setString(5, Integer.toString(addressId));
			ps.setString(6, member.getPhoneNumber());
			// Execute the query
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				// Saving the profile photo of the new member. We did it here because previously we did not have the id of the member.
				MediaDAO.addMemberPhoto(member.getProfilePhoto(), rs.getInt(1)); 
				return rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// Cannot execute query
			e.printStackTrace();
			return 0;
		}	
		return 0;
	}
	
	/**
	 * Save the member and return this Member with its memberId set 
	 * (generated after save)*/
	public static Member addMemberGetMember(Member member) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return null; // Failed to get a connection instance
		}
		int memberId = addMember(member);
		member.setMemberId(memberId);
		return member;
	}
	
	
	/**
	 * Save all the members in the list and return a new list containing the saved 
	 * members, each with its newly generated memberId set*/
	public static List<Member> addMembersGetMembers(List<Member> members){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return null; // Failed to get a connection instance
		}
		List<Member> newMembers = new ArrayList<Member>();
		for (Member m: members) 
			newMembers.add(addMemberGetMember(m));
		return newMembers;
	}
	
	/**
	 * Return the Member associated with the given id | null when fails*/
	public static Member getMemberById(int memberId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return null; // Failed to get a connection instance
		}
		
		Member member = new Member();
		
		// Query 
		String query = "SELECT * FROM Member WHERE memberId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(memberId));
			// Execute the query
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				member.setMemberId(memberId);
				member.setFirstName(rs.getString("firstName"));
				member.setLastName(rs.getString("lastName"));
				member.setNationalIdNumber(rs.getString("nationalIdNumber"));
				member.setRole(rs.getString("role"));
				// Retrieving the member's address
				Address address = AddressDAO.getAddressById(rs.getInt("addressId"));
				member.setAddress(address);
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setProfilePhoto(MediaDAO.getMemberPhotoByMemberId(memberId));
			}else {
				// No row was fetched
				return null;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// Failed to fetch Member
			e.printStackTrace();
			return null;
		}
		return member;
	}
	
	/**
	 * Return a list of all members stored in the database | null when fails.
	 * Note! the returned list may be empty*/
	public static List<Member> getAllMembers(){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return null; // Failed to get a connection instance
		}
		
		List<Member> members = new ArrayList<>();
		
		//Query
		String query = "SELECT * FROM Member";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt("memberId"));
				member.setFirstName(rs.getString("firstName"));
				member.setLastName(rs.getString("lastName"));
				member.setNationalIdNumber(rs.getString("nationalIdNumber"));
				member.setRole(rs.getString("role"));
				// Retrieving the member's address
				Address address = AddressDAO.getAddressById(rs.getInt("addressId"));
				member.setAddress(address);
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setProfilePhoto(MediaDAO.getMemberPhotoByMemberId(member.getMemberId()));
				members.add(member);	
			}
		} catch (Exception e) {
			// Failed to fetch members
			e.printStackTrace();
			return null;
		}
		return members.size() == 0 ? null: members;
	}
	
	/**
	 * Return the list of all members associated with the given missionId.*/
	public static List<Member> getMissionMembersByMissionId(int missionId){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return null; // Failed to get a connection instance
		}
		// Query
		String query = "SELECT * FROM missionMembers WHERE missionId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ResultSet rs = ps.executeQuery();
			List<Member> members = new ArrayList<>();
			while (rs.next()) {
				Member member = MemberDAO.getMemberById(rs.getInt("memberId"));
				members.add(member);
			}
			return members;
		} catch (Exception e) {
			// Failed to fetch missionMembers
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	/**
	 * Delete the member who has the given id. true: deleted, false: not deleted*/
	public static boolean deleteMemberById(int id) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return false; // Failed to get a connection instance
		}
		
		//Query
		String query = "DELETE FROM Member WHERE memberId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(id));
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// Failed to delete the member
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Return the list of all member who has the given role | null when fails.
	 * Note! the returned list may be empty*/
	public static List<Member> getMembersByRole(String role){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MemberDAO !");
			return null; // Failed to get a connection instance
		}
		
		//Query 
		String query = "SELECT * FROM Member WHERE role = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, role);
			ResultSet rs = ps.executeQuery();
			List<Member> members = new ArrayList<>();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt("memberId"));
				member.setFirstName(rs.getString("firstName"));
				member.setLastName(rs.getString("lastName"));
				member.setNationalIdNumber(rs.getString("nationalIdNumber"));
				member.setRole(rs.getString("role"));
				// Retrieving the member's address
				Address address = AddressDAO.getAddressById(rs.getInt("addressId"));
				member.setAddress(address);
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setProfilePhoto(MediaDAO.getMemberPhotoByMemberId(rs.getInt("memberId")));;
				members.add(member);
			}
			return members;
		} catch (Exception e) {
			// Failed to get all members 
			return null;
		}
	}
}
