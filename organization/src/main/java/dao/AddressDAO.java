package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Address;
import utils.DBConnect;

public class AddressDAO {
	
	public static Connection connection = DBConnect.getInstance();
	
	
	/**
	 * Return the Address object relative to the given id*/
	public static Address getAddressById(int id) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AddressDAO !");
			return null;
		}
		
		Address address = new Address();
		
		/**The select query*/
		String query = "SELECT * FROM address WHERE addressId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(id));
			//Process the results 
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				address.setAddressId(id);
				address.setCountry(rs.getString("country"));
				address.setCity(rs.getString("city"));
				address.setStreet(rs.getString("street"));
				address.setZipCode(rs.getString("zipCode"));
			}else {
				// No address found
				return null;
			}
			rs.close();
			ps.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return address;
    }
	
	/**
	 * Save the address to the database and return the id created | 0 when fails*/
	public static int addAddress(Address address) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AddressDAO !");
			return 0;
		}
		
		int addressId = 0;
		
		// Query
		String query = "INSERT INTO address(country, city,street, zipCode) VALUES(?,?,?,?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, address.getCountry());
			ps.setString(2, address.getCity());
			ps.setString(3, address.getStreet());
			ps.setString(4, address.getZipCode());
			//Process the results 
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				addressId = rs.getInt(1);
			}
			
		} catch (Exception e) {
			// Failed to save the address
			e.printStackTrace();
			return 0;
		}
		return addressId;
	}
}
