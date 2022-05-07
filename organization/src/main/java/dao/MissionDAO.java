package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Date;

import entities.Member;
import entities.Mission;
import entities.Photo;
import entities.Video;
import utils.DBConnect;

/**
 * Handles the mission's data from and to the database*/
public class MissionDAO {
	private static Connection connection = DBConnect.getInstance();
	/**
	 * Save the mission to the database and retrun the generated key (id). Return 0 when fails.*/
	public static int addMission(Mission mission) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return 0; // Failed to get a connection instance
		}
		
		//Query 
		String query = "INSERT INTO mission(startDate, endDate, priority, state, familyId, addressId) VALUES "
				+ "(?,?,?,?,?,?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Date.valueOf(mission.getStartDate()));
			ps.setDate(2, Date.valueOf(mission.getEndDate()));
			ps.setString(3, mission.getPriority());
			ps.setString(4, mission.getState());
			ps.setInt(5, mission.getFamily().getFamilyId());
			// Saving the mission's address first
			int addressId = AddressDAO.addAddress(mission.getPlace());
			if (addressId == 0) {
				System.out.println("Failed to save the address for missionId: " + mission.getMissionId());
				return 0;
			}
			ps.setInt(6, addressId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int missionId  = rs.getInt(1);
				// Adding the mission members 
				if (!addMissionMembers(mission.getMembers(), missionId))
					System.out.println("Failed to save the members for missionId: " + missionId);
				// Saving the mission's photos
				MediaDAO.addMisisonPhotos(mission.getPhotos(), missionId);
				// Saving the mission's videos
				MediaDAO.addMissionVideos(mission.getVideos(), missionId);
				// Saving the expense
				if (ExpenseDAO.addExpense(mission.getExpense(), missionId) == 0) 
					System.out.println("Failed to save the expense for missionId: " + missionId);
				
				return missionId;
			}
			// No key was generated
			return 0;
			
		} catch (Exception e) {
			// Failed to save mission
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Adding the member to the associated mission in the table missionMembers in the database.
	 * (missionId, memberId)*/
	public static int addMissionMember(Member member, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return 0; // Failed to get a connection instance
		}
		// Query
		String query = "INSERT INTO missionMembers(missionId, memberId) VALUES (?,?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, missionId);
			ps.setInt(2, member.getMemberId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			// Failed to insert record
			return 0;
		} catch (Exception e) {
			// Failed to insert record
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Adding the members to the associated mission in the table missionMembers in the database.
	 * (missionId, memberId)*/
	public static boolean addMissionMembers(List<Member> members, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return false; // Failed to get a connection instance
		}
		for (Member m: members)
			addMissionMember(m, missionId);
		return true;
	}
	
	
	
	/**
	 * Return the mission associated with the given missionId | null when fails.*/
	public static Mission getMissionById(int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return null; // Failed to get a connection instance
		}
		//Query
		String query = "SELECT * FROM mission WHERE missionId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Mission mission = new Mission();
				mission.setMissionId(missionId);
				mission.setStartDate(rs.getDate("startDate").toLocalDate());
				mission.setEndDate(rs.getDate("endDate").toLocalDate());
				mission.setPriority(rs.getString("priority"));
				mission.setState(rs.getString("state"));
				// Getting the missions Members
				mission.setMembers(MemberDAO.getMissionMembersByMissionId(missionId));
				// Getting the misison's expense
				mission.setExpense(ExpenseDAO.getExpenseByMissionId(missionId));
				// Getting the mission's Family
				mission.setFamily(FamilyDAO.getFamilyById(rs.getInt("familyId")));
				// Getting the mission's address (place)
				mission.setPlace(AddressDAO.getAddressById(rs.getInt("addressId")));
				// Getting the mission's photos
				mission.setPhotos(MediaDAO.getAllMissionPhotosByMissionId(missionId));
				// Getting the misison's videos
				mission.setVideos(MediaDAO.getAllMissionVideosByMissionId(missionId));
				return mission;
			}
			// No row was selected
			return null;
		} catch (Exception e) {
			// Failed to fetch Mission
			return null;
		}
	}
	
	/**
	 * Return a list of all missions | null when fails.
	 * Note! the returned list may be empty*/
	public static List<Mission> getAllMissions(){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return null; // Failed to get a connection instance
		}
		//Query
				String query = "SELECT * FROM mission";
				try {
					// create the prepared statement
					PreparedStatement ps = connection.prepareStatement(query);
					List<Mission> missions = new ArrayList<>();
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Mission mission = new Mission();
						int missionId = rs.getInt("missionId");
						mission.setMissionId(missionId);
						mission.setStartDate(rs.getDate("startDate").toLocalDate());
						mission.setEndDate(rs.getDate("endDate").toLocalDate());
						mission.setPriority(rs.getString("priority"));
						mission.setState(rs.getString("state"));
						// Getting the missions Members
						mission.setMembers(MemberDAO.getMissionMembersByMissionId(missionId));
						// Getting the misison's expense
						mission.setExpense(ExpenseDAO.getExpenseByMissionId(missionId));
						// Getting the mission's Family
						mission.setFamily(FamilyDAO.getFamilyById(rs.getInt("familyId")));
						// Getting the mission's address (place)
						mission.setPlace(AddressDAO.getAddressById(rs.getInt("addressId")));
						// Getting the mission's photos
						mission.setPhotos(MediaDAO.getAllMissionPhotosByMissionId(missionId));
						// Getting the misison's videos
						mission.setVideos(MediaDAO.getAllMissionVideosByMissionId(missionId));
						missions.add(mission);
					}
					return missions;
				} catch (Exception e) {
					// Failed to fetch missions by state
					e.printStackTrace();
					return null;
				}
	}
	
	/**
	 * Return the list of missions who has the given state*/
	public static List<Mission> getMissionsByState(String state) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return null; // Failed to get a connection instance
		}
		//Query
		String query = "SELECT * FROM mission WHERE state = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, state);
			List<Mission> missions = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Mission mission = new Mission();
				int missionId = rs.getInt("missionId");
				mission.setMissionId(missionId);
				mission.setStartDate(rs.getDate("startDate").toLocalDate());
				mission.setEndDate(rs.getDate("endDate").toLocalDate());
				mission.setPriority(rs.getString("priority"));
				mission.setState(rs.getString("state"));
				// Getting the missions Members
				mission.setMembers(MemberDAO.getMissionMembersByMissionId(missionId));
				// Getting the misison's expense
				mission.setExpense(ExpenseDAO.getExpenseByMissionId(missionId));
				// Getting the mission's Family
				mission.setFamily(FamilyDAO.getFamilyById(rs.getInt("familyId")));
				// Getting the mission's address (place)
				mission.setPlace(AddressDAO.getAddressById(rs.getInt("addressId")));
				// Getting the mission's photos
				mission.setPhotos(MediaDAO.getAllMissionPhotosByMissionId(missionId));
				// Getting the misison's videos
				mission.setVideos(MediaDAO.getAllMissionVideosByMissionId(missionId));
				missions.add(mission);
			}
			return missions;
		} catch (Exception e) {
			// Failed to fetch missions by state
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the mission's state to the new state*/
	public static boolean updateMissionStateById(int missionId, String newState) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return false; // Failed to get a connection instance
		}
		// Query
		String query = "UPDATE mission SET state = ? WHERE missionId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, newState);
			ps.setInt(2, missionId);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// Failed to update the mission's state
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Add the photo to the mission with the given id (if exists). Return the photo' id (generated key) | 0 when fails*/
	public static int addPhotoToMissionById(Photo photo, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return 0; // Failed to get a connection instance
		}
		// Checking if the mission already exists
		if (!missionExists(missionId))
			return 0; // Mission does not exist
		
		// Query
		String query = "INSERT INTO missionPhotos(photoPath, missionId) VALUES (?,?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, photo.getPhotoPath());
			ps.setInt(2, missionId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			// Failed to save the photo
			return 0;
		} catch (Exception e) {
			// Cannot execute insert query
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Add the video to the mission with the given id (if exists). Return the photo' id (generated key) | 0 when fails*/
	public static int addVideoToMissionById(Video video,int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return 0; // Failed to get a connection instance
		}
		// Checking if the mission already exists
		if (!missionExists(missionId))
			return 0; // Mission does not exist
		
		// Query
		String query = "INSERT INTO missionVideos(videoPath, missionId) VALUES (?,?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, video.getVideoPath());
			ps.setInt(2, missionId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			// Failed to save the video
			return 0;
		} catch (Exception e) {
			// Cannot execute insert query
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Check whether the mission with the given id exists in the database or not*/
	public static boolean missionExists(int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return false; // Failed to get a connection instance
		}
		// Query
		String query = "SELECT EXISTS(SELECT 1 FROM mission WHERE missionId = ?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
			return false;
		} catch (Exception e) {
			// Failed to execute query
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Add a new member to the mission with the given missionId. */
	public static boolean addMemberToMissionById(Member member, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the MissionDAO !");
			return false; // Failed to get a connection instance
		}
		// Checking if the mission already exists
		if (!missionExists(missionId))
			return false; // Mission does not exist
		
		//Query 
		String query = "INSERT INTO missionMembers(missionId, memberId) VALUES (?, ?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ps.setInt(2, member.getMemberId());
			ps.executeUpdate();
			return true; // We can add more test to confirm that the tuple has been inserted
		}
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("SQL Integrity Constraint Violation: Double check the arguments you passed !");
					
		}
		catch (Exception e) {
			// Failed to execute the query
			e.printStackTrace();
		}
		return false;
	}
}
