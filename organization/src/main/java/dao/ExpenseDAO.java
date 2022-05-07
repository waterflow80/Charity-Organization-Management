package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import entities.Expense;
import utils.DBConnect;

/**
 * Handles the expenses' data from and to the database*/
public class ExpenseDAO {
	private static Connection connection = DBConnect.getInstance();
	
	/**
	 * Save the expense in the database. Return the generated key (id) | 0 when fails*/
	public static int addExpense(Expense expense, int missionId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the ExpenseDAO !");
			return 0; // Failed to get a connection instance
		}
		
		//Query
		String query = "INSERT INTO expense(amountOfMoney, missionId) VALUES(?, ?)";
		
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, expense.getAmountOfMoney());
			ps.setInt(2, missionId);
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			// No key was returned
			return 0;
		} catch (Exception e) {
			// Cannot insert expense
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Return the expense associated with the given expenseId | null when fails*/
	public static Expense getExpenseById(int expenseId) {
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the ExpenseDAO !");
			return null; // Failed to get a connection instance
		}
		//Query
		String query = "SELECT * FROM expense WHERE expenseId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, expenseId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Expense expense = new Expense();
				expense.setExpenseId(rs.getInt("expenseId"));
				expense.setAmountOfMoney(rs.getFloat("amountOfMoney"));
				return expense;
			}
			// No expense was selected
			return null;
		} catch (Exception e) {
			// Failed to fetch expense
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return the expense associated with the given missionId | null when fails.*/
	public static Expense getExpenseByMissionId(int missionId){
		if (connection == null) {
			System.out.println("Cannont connect to the DB from the ExpenseDAO !");
			return null; // Failed to get a connection instance
		}
		// Query
		String query = "SELECT * FROM expense WHERE missionId = ?";
		try {
			// create the prepared statement
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, missionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Expense expense = new Expense();
				expense.setExpenseId(rs.getInt("expenseId"));
				expense.setAmountOfMoney(rs.getFloat("amountOfMoney"));
				return expense;
			}
			// No expense was selected
			return null;
			
		} catch (Exception e) {
			// Failed to fetch expense
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
