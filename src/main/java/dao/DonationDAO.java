package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Donation;
import entities.Donor;
import javafx.css.PseudoClass;
import utils.DBConnect;

/**
 * Handles the donation's data from and to the database*/
public class DonationDAO {
	private static Connection connection = DBConnect.getInstance();
	
	
	/**
	 * Save the donor to the database. Return the generated donor's id | 0 when fails*/
	  public static int addDonor(Donor donor, int donationId) { 
		  if (connection == null) {
		  System.out.println("Cannont connect to the DB from the DonationDAO !");
		  return 0; // Failed to get a connection instance 
		  } 
		  // Query 
		  String query = "INSERT INTO donor(donationId, firstName, lastName, nationalIdNumber, position) VALUES (?,?,?,?,?)";
		  try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, donationId);
			ps.setString(2, donor.getFirstName());
			ps.setString(3, donor.getLastName());
			ps.setString(4, donor.getNationalIdNumber());
			ps.setString(5, donor.getPosition());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			// Failed to insert donor
			return 0;
		} catch (Exception e) {
			// Failed it execute query
			e.printStackTrace();
			return 0;
		}
	  }
	 
	  /**
	   * Return the donor who has the given donorId | null when fails*/
	  public static Donor getDonorByid(int donorId) {
		  if (connection == null) {
				System.out.println("Cannont connect to the DB from the DonationDAO !");
				return null; // Failed to get a connection instance
			}
		  //Query
		  String query = "SELECT * FROM donor WHERE donorId = ?";
		  try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, donorId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Donor donor = new Donor();
				donor.setDonorId(donorId);
				donor.setFirstName(rs.getString("firstName"));
				donor.setLastName(rs.getString("lastName"));
				donor.setNationalIdNumber(rs.getString("nationalIdNumber"));
				donor.setPosition(rs.getString("position"));
				return donor;
			}
			// No row was selected
			return null;
		} catch (Exception e) {
			// Failed to fetch donor
			e.printStackTrace();
			return null;
		}
	  }
	
	  /**
	   * Return the donor associated with the given donationId | null when fails*/
	  public static Donor getDonorByDonationId(int donationId) {
		  if (connection == null) {
				System.out.println("Cannont connect to the DB from the DonationDAO !");
				return null; // Failed to get a connection instance
			}
		  //Query
		  String query = "SELECT * FROM donor WHERE donationId = ?";
		  try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, donationId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Donor donor = new Donor();
				donor.setDonorId(rs.getInt("donorId"));
				donor.setFirstName(rs.getString("firstName"));
				donor.setLastName(rs.getString("lastName"));
				donor.setNationalIdNumber(rs.getString("nationalIdNumber"));
				donor.setPosition(rs.getString("position"));
				return donor;
			}
			// No row was selected
			return null;
		} catch (Exception e) {
			// Failed to fetch donor
			e.printStackTrace();
			return null;
		}
	  }
	  
	  /**
	   * Return a list of all donors | null when fails*/
	  public static List<Donor> getAllDonors(){
		  if (connection == null) {
				System.out.println("Cannont connect to the DB from the DonationDAO !");
				return null; // Failed to get a connection instance
			}
		  // Query
		  String query = "SELECT * FROM donor";
		  try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<Donor> donors = new ArrayList<>();
			while (rs.next()) {
				Donor donor = new Donor();
				donor.setDonorId(rs.getInt("donorId"));
				donor.setFirstName(rs.getString("firstName"));
				donor.setLastName(rs.getString("lastName"));
				donor.setNationalIdNumber(rs.getString("nationalIdNumber"));
				donor.setPosition(rs.getString("position"));
				donors.add(donor);
			}
			return donors;
		} catch (Exception e) {
			// Failed to fetch donors
			e.printStackTrace();
			return null;
		}
	  }
	  
	/**
	 * Save the donation to the database. Return the generated donation's id | 0 when fails*/
	public static int addDonation(Donation donation) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the DonationDAO !");
			return 0; // Failed to get a connection instance
		}
		// Query 
		String query = "INSERT INTO donation(amountOfMoney, date) VALUES (?,?)";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, donation.getAmountOfMoney());
			ps.setDate(2, Date.valueOf(donation.getDate()));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int donationId = rs.getInt(1);
				// Now inserting the donation's donor
				if (addDonor(donation.getDonor(), donationId) == 0)
					System.out.println("Failed to save the donor for donationId : " + donationId);
				return donationId;
				
			}
			// Failed to insert donation
			return 0;
		} catch (Exception e) {
			// Failed to save donation
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Return the donation who has the given id | null when fails*/
	public static Donation getDonationById(int donationId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the DonationDAO !");
			return null; // Failed to get a connection instance
		}
		 // Query
		 String query = "SELECT * FROM donation WHERE donationId = ?";
		 try {
			// create the prepared statement
			 PreparedStatement ps = connection.prepareStatement(query);
			 ps.setInt(1, donationId);
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
				 Donation donation = new Donation();
				 donation.setDonationId(donationId);
				 donation.setAmountOfMoney(rs.getFloat("amountOfMoney"));
				 donation.setDate(rs.getDate("date").toLocalDate());
				 // Getting the associated donor
				 Donor donor = getDonorByDonationId(donationId);
				 donation.setDonor(donor);
				 return donation;
			 }
			 // No row was selected
			 return null;
		} catch (Exception e) {
			// Failed to execute query
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return a list of all donations*/
	public static List<Donation> getAllDonations(){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the DonationDAO !");
			return null; // Failed to get a connection instance
		}
		 // Query
		 String query = "SELECT * FROM donation";
		 try {
			// create the prepared statement
			 PreparedStatement ps = connection.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();
			 List<Donation> donations = new ArrayList<>();
			 while(rs.next()) {
				 Donation donation = new Donation();
				 donation.setDonationId(rs.getInt("donationId"));
				 donation.setAmountOfMoney(rs.getFloat("amountOfMoney"));
				 donation.setDate(rs.getDate("date").toLocalDate());
				 // Getting the associated donor
				 Donor donor = getDonorByDonationId(rs.getInt("donationId"));
				 donation.setDonor(donor);
				 donations.add(donation);
			 }
			 return donations;
		} catch (Exception e) {
			// Failed to fetch all donations
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delete the donation that has the given donationId.
	 * The associated donor will be automatically deleted by mysql server (ON DELETE CASCADE)*/
	public static boolean deleteDonationById(int donationId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the DonationDAO !");
			return false; // Failed to get a connection instance
		}
		//Query
		String query = "DELETE FROM donation WHERE donationId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, donationId);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// Failed to delete donation
			e.printStackTrace();
			return false;
		}
		
	}
	
}
