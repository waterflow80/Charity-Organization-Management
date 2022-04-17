package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Family;
import entities.FamilyMember;
import utils.DBConnect;

/**
 * Handles the family's data from and to the database*/
public class FamilyDAO {
	
	public static Connection connection = DBConnect.getInstance();
	
	/**
	 * Save the member to the database.
	 * Return the primary key of the newly inserted familyMember | 0 when fails.*/
	public static int addFamilyMember(FamilyMember member, int familyId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return 0; // Failed to get a connection instance
		}	
		
		int familyMemberId = 0;
		
		// Query
		String query = "INSERT INTO familyMember(familyId, firstName, lastName, age, profession, nationalIdNumber, phoneNumber, isParent)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			// create the prepared statement and add the adminId
			PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, Integer.toString(familyId));
			pstmt.setString(2, member.getFirstName());
			pstmt.setString(3, member.getLastName());
			pstmt.setString(4, Integer.toString(member.getAge()));
			pstmt.setString(5, member.getProfession());
			pstmt.setString(6, member.getNationalCardNumber());
			pstmt.setString(7, member.getPhoneNumber());
			pstmt.setString(8, Integer.toString(member.isParent() ? 1 : 0)); // true : 1 | false : 0
			// Executing the statement
			pstmt.executeUpdate();
			
			// process the results
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				familyMemberId = rs.getInt(1);
			}else {
				// No key was returned
				return 0;
			}
			rs.close();
			pstmt.close();
			
			
		} catch (SQLException e) {
			// Failed to fetch the admin's data
			e.printStackTrace();
			return 0;
		}
		return familyMemberId;
	}
	
	
	/**
	 * Return the FamilyMember object associated with the given id*/
	public static FamilyMember getFamilyMemberById(int familyMemberId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return null; // Failed to get a connection instance
		}	
		
		// Select Query
		String query = "SELECT * FROM familyMember WHERE familyMemberId = ?";
		
		FamilyMember member = new FamilyMember();
		
		try {
			// create the prepared statement
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, Integer.toString(familyMemberId));
			
			// Process result
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setFamilyMemberId(familyMemberId);
				member.setFirstName(rs.getString("firstName"));
				member.setLastName(rs.getString("lastName"));
				member.setAge(rs.getInt("age"));
				member.setProfession(rs.getString("profession"));
				member.setNationalCardNumber(rs.getString("nationalIdNumber"));
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setParent(rs.getInt("isParent") == 1 ? true : false);

			}else {
				// No result fetched 
				return null;
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			// Cannot fetch the familyMember
			e.printStackTrace();
			return null;
		}
		return member;
	}
	
	
	
	/**
	 * Save the family to the database. Return the newly inserted familyId | 0 when fails*/
	public static int addFamily(List<FamilyMember> members, Address address) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return 0; // Failed to get a connection instance
		}	
		
		// Inserting the address first and recovering the generated key (id)
		int addressId = AddressDAO.addAddress(address);
		
		if (addressId == 0) {
			// Failed to save the address. We cannot complete with the family
			return 0;
		}
		
		// Query
		String query = "INSERT INTO family(addressId) VALUES(?)";
		int familyId = 0;
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, Integer.toString(addressId));
			
			// Process results
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				familyId = rs.getInt(1);
				}
			else {
				// No key was returned
				return 0;
			}
			// Saving the family members to the database
			// Now inserting the family members into the database
			for (FamilyMember fm: members) {
				addFamilyMember(fm, familyId);
				
			}
			rs.close();
			ps.close();
			} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return familyId;
	}
	
	/**
	 * Return a list of all familyMembers associated with the given familyId*/
	public static List<FamilyMember> getAllFamilyMembersByFamilyId(int familyId){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return null; // Failed to get a connection instance
		}	
		
		List<FamilyMember> members = new ArrayList<>();
		
		// Query
		String query = "SELECT * FROM familyMember where familyId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(familyId));
			
			//Process results
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FamilyMember member = getFamilyMemberById(rs.getInt("familyMemberId"));
				members.add(member);
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			// Cannot fetch familyMembers
			e.printStackTrace();
			return null;
		}
		return members;
	}
	
	
	/**
	 * Retutrn the family object associated with the given familyId*/
	public static Family getFamilyById(int familyId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return null; // Failed to get a connection instance
		}	
		
		Family family = new Family();;
		
		// Query
		String query = "SELECT * FROM family WHERE familyId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(familyId));
			
			//Process results
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int addressId = rs.getInt("addressId");
				Address address = AddressDAO.getAddressById(addressId);
				List<FamilyMember> members = getAllFamilyMembersByFamilyId(familyId);
				family.setFamilyId(familyId);
				family.setAddress(address);
				family.setFamilyMembers(members);
				
			}else {
				// No row was fetched
				return null;
			}
		} catch (Exception e) {
			// Cannot fetch family
			e.printStackTrace();
			return null;
		}
		return family;
	}
	
	
	/**
	 * Return a list of all Families stored in the database*/
	public static List<Family> getAllFamilies(){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return null; // Failed to get a connection instance
		}
		
		List<Family> allFamilies = new ArrayList<>();
		
		// Query
		String query = "SELECT * FROM family";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			
			//Process results
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Family family = getFamilyById(rs.getInt("familyId"));
				allFamilies.add(family);
			}
			
		} catch (Exception e) {
			// Failed to fetch all families
			e.printStackTrace();
			return null;
		}
		return allFamilies;
	}
	
	/**
	 * Delete the family with the given id and return it's id (past) | 0 if fails*/
	public static int deleteFamilyById(int familyId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return 0; // Failed to get a connection instance
		}
		
		// Query 
		String query = "DELETE FROM family WHERE familyId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(familyId));
			
			// Execute Query
			ps.executeUpdate();
			
			// Note! We set the familyId Foreign Key of the familyMember to 'ON DELTE CASCADE', 
			//so the associated family members will be deleted automatically
			return familyId;
			
		} catch (Exception e) {
			// Cannot delete family
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Delete the member who has the given memberId. Return the deleted memberId | 0 when fails*/
	public static int deleteFamilyMemberById(int memberId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the FamilyDAO !");
			return 0; // Failed to get a connection instance
		}
		
		// Query 
		String query = "DELETE FROM familyMember WHERE familyMemberId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(memberId));
			
			// Execute Query
			ps.executeUpdate();
			
			return memberId;
			
		} catch (Exception e) {
			// Cannot execute query
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
}	
