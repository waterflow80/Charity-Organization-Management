package exceptions;

import java.sql.SQLException;

public class DBConnectionFailedException extends SQLException {

	public DBConnectionFailedException() {
		super();
		System.out.println("Cannot Connect to the database !");
	}

	public DBConnectionFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
