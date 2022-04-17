package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Account;
import entities.Admin;
import utils.DBConnect;

/**
 * Handles the account's data from and to the database*/
public class AccountDAO {
	
	public static Connection connection = DBConnect.getInstance();
	
	
	/**
	 * Save the account to the database. Return the generated key (id)| 0 when fails*/
	public static int addAccount(Account account) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AccountDAO !");
			return 0; // Failed to get a connection instance
		}	
		
		//Query
		String query = "INSERT INTO account(email, password) VALUES (?,?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, account.getEmail());
			ps.setString(2, account.getPassword());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int accountId = rs.getInt(1);
				// Saving the associated Admin
				int adminId = AdminDAO.addAdmin(account.getAdmin(), accountId);
				if (adminId == 0) {
					System.out.println("Cannot save the account's admin !");
				}
				return accountId;
			}
			return 0; // No key was generated :(
			
		} catch (Exception e) {
			// Failed to save the account
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Return the Account object who has the given id*/
	public static Account getAccountById(int accountId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AccountDAO !");
			return null; // Failed to get a connection instance
		}	
		
		Account account = new Account();
		
		// Select Query
		String query = "SELECT * FROM account WHERE accountId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(accountId));
			
			// Process the results
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				// We need to get the associated admin
				Admin admin = AdminDAO.getAdminByAccountId(accountId);
				account.setAccountId(accountId);
				account.setAdmin(admin);
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
			}else {
				// No account was fetched
				return null;
			}
			
		} catch (Exception e) {
			System.out.println("getAccountById: ");
			e.printStackTrace();
			return null;
		}
		
		return account;
	}
	
	/**
	 * Delete the account who has the given id*/
	public static boolean deleteAccountById(int accountId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the AccountDAO !");
			return false; // Failed to get a connection instance
		}
		
		//Query
		String query = "DELETE FROM account WHERE accountId = ?";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, accountId);
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// Cannot delete the account
			e.printStackTrace();
			return false;
		}
	}
}
