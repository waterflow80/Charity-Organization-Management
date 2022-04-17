package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates a unique instance of the database connection.
 * Uses the Singleton Design Pattern.
 * */
public class DBConnect {
	private static Connection connection;
	private final String DB_URL = "jdbc:mysql://localhost:3306/charity_organization";
	private final String MYSQL_USER = "root";
	private final String MYSQL_PASSWORD = "ubuntudb";
	
	private DBConnect() throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		connection = DriverManager.getConnection(DB_URL, MYSQL_USER, MYSQL_PASSWORD);
	}
	
	public static Connection getInstance() {
		if (connection == null) {
			try {
				new DBConnect();
			}catch (SQLException e) {
				System.out.println("--"+e.getMessage());
			}
		}
		return connection;
	}
}
