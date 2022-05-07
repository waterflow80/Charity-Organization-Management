package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Admin;
import utils.DBConnect;

/**
 * Handles the admin's data from and to the database*/
public class AdminDAO {
	
	private static Connection connection = DBConnect.getInstance();
	
	/**
	 * Save the admin to the database. Return the generated key (id) | 0 when fails*/
	public static int addAdmin(Admin admin, int accountId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AdminDAO !");
			return 0; // Failed to get a connection instance
		}
		
		
		// Query
		String query = "INSERT INTO admin(accountId, firstName, lastName, nationalIdNumber, phoneNumber, role, addressId) "
				+ "VALUES (?,?,?,?,?,?,?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, accountId);
			ps.setString(2, admin.getFirstName());
			ps.setString(3, admin.getLastName());
			ps.setString(4, admin.getNationalIdNumber());
			ps.setString(5, admin.getPhoneNumber());
			ps.setString(6, admin.getRole());
			// Saving the admin's address first
			int addressId = AddressDAO.addAddress(admin.getAddress());
			if (addressId == 0) {
				// Failed to save the address of the admin (required)
				return 0;
			}
			ps.setInt(7, addressId);
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// Failed to add admin
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	/**
	 * Return the admin who has the given id | null when fails*/
	public static Admin getAdminById(int id) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AdminDAO !");
			return null; // Failed to get a connection instance
		}	
		
		// Select Query
		String query = "SELECT * FROM admin WHERE adminId = ?";
		
		Admin admin = new Admin();
		
		try {
			// create the prepared statement
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, Integer.toString(id));
			
			// process the results
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				admin.setAdminId(rs.getInt("adminId"));
				admin.setFirstName(rs.getString("firstName"));
				admin.setLastName(rs.getString("lastName"));
				admin.setNationalIdNumber(rs.getString("nationalIdNumber"));
				admin.setPhoneNumber(rs.getString("phoneNumber"));
				admin.setRole(rs.getString("role"));
				Address address;
				if (rs.getInt("addressId") != 0) {
					 address = AddressDAO.getAddressById(rs.getInt("addressId"));
					 admin.setAddress(address);
				}else {
					// The address filed was null
					admin.setAddress(null);
				}
				
			}else {
				// No admin was fetched
				return null;
			}
			rs.close();
			pstmt.close();
			
			
		} catch (SQLException e) {
			// Failed to fetch the admin's data
			e.printStackTrace();
			return null;
		}
		
		return admin;
		
	}
	
	
	/**
	 * Return the Admin object associated with the given accountId | return null when fails*/
	public static Admin getAdminByAccountId(int accountId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AdminDAO !");
			return null; // Failed to get a connection instance
		}	
		
		// Select Query
		String query = "SELECT * FROM admin WHERE accountId = ?";
		
		Admin admin = new Admin();
		
		try {
			// create the prepared statement
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, Integer.toString(accountId));
			
			// process the results
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				admin.setAdminId(rs.getInt("adminId"));
				admin.setFirstName(rs.getString("firstName"));
				admin.setLastName(rs.getString("lastName"));
				admin.setNationalIdNumber(rs.getString("nationalIdNumber"));
				admin.setPhoneNumber(rs.getString("phoneNumber"));
				admin.setRole(rs.getString("role"));
				Address address;
				if (rs.getInt("addressId") != 0) {
					 address = AddressDAO.getAddressById(rs.getInt("addressId"));
					 admin.setAddress(address);
				}else {
					// The address filed was null
					admin.setAddress(null);
				}
				
			}else {
				// No admin was fetched
				return null;
			}
			rs.close();
			pstmt.close();
			
			
		} catch (SQLException e) {
			// Failed to fetch the admin's data
			e.printStackTrace();
			return null;
		}
		
		return admin;
	}
	
	
	/**
	 * Retrun a list of all admins stored in the database | null when fails.
	 * Note: the lsit may be empty*/
	public static List<Admin> getAllAdmins(){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AdminDAO !");
			return null; // Failed to get a connection instance
		}	
		
		// Select Query
		String query = "SELECT * FROM admin";
		
		List<Admin> admins = new ArrayList<>();
		
		try {
			// create the prepared statement
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			// process the results
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setFirstName(rs.getString("firstName"));
				admin.setLastName(rs.getString("lastName"));
				admin.setNationalIdNumber(rs.getString("nationalIdNumber"));
				admin.setPhoneNumber(rs.getString("phoneNumber"));
				admin.setRole(rs.getString("role"));
				Address address;
				if (rs.getInt("addressId") != 0) {
					 address = AddressDAO.getAddressById(rs.getInt("addressId"));
					 admin.setAddress(address);
				}else {
					// The address filed was null
					admin.setAddress(null);
				}
				admins.add(admin);
				
			}
			rs.close();
			pstmt.close();
			
			
		} catch (SQLException e) {
			// Failed to fetch the admin's data
			e.printStackTrace();
			return null;
		}
		return admins;
	}
	
	/**
	 * Return the list of all admins with the given role | null when fails*/
	public static List<Admin> getAdminsByRole(String role){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AdminDAO !");
			return null; // Failed to get a connection instance
		}
		
		// Select Query
		String query = "SELECT * FROM admin WHERE role = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, role);
			ResultSet rs = ps.executeQuery();
			List<Admin> admins = new ArrayList<>();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setFirstName(rs.getString("firstName"));
				admin.setLastName(rs.getString("lastName"));
				admin.setNationalIdNumber(rs.getString("nationalIdNumber"));
				admin.setPhoneNumber(rs.getString("phoneNumber"));
				admin.setRole(rs.getString("role"));
				Address address;
				if (rs.getInt("addressId") != 0) {
					 address = AddressDAO.getAddressById(rs.getInt("addressId"));
					 admin.setAddress(address);
				}else {
					// The address filed was null
					admin.setAddress(null);
				}
				admins.add(admin);
			}
			return admins;
		} catch (Exception e) {
			// Failed to get all admins by role
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return the role of the admin who has the given accountId. | null when not found*/
	public static String getAdminRoleByAccountId(int accountId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AdminDAO !");
			return null; // Failed to get a connection instance
		}
		// Query
		String query = "SELECT role FROM admin WHERE adminId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getString("role");
			return null;
		} catch (Exception e) {
			// Failed to get role
			e.printStackTrace();
			return null;
		}
	}
	
}
