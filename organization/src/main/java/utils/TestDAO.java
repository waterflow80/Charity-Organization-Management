package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
	// Connection
		private static Connection conn = DBConnect.getInstance();
	    
		        
		public static int save(Personne personne) {
			int personneId = 0;
			PreparedStatement pstmt = null;
		    ResultSet rs = null;
	        
	        try {
	            String sql = "INSERT INTO personne (prenom, nom) VALUES (?, ?)";
	        	
	        	pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        	
	        	pstmt.setString(1, personne.getFirstName());
	        	pstmt.setString(2, personne.getLastName());
	        	pstmt.executeUpdate();
	        	
	            // 4- Recupérer l'Id généré par le SGBD
	        	rs = pstmt.getGeneratedKeys();
	            
	            if(rs.next())
	            	personneId = rs.getInt(1);
	            
	        }catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
			return personneId;
		}
		
		public static List<Personne> findAll(){
			
			Statement stmt = null;
		    ResultSet rs = null;
		    
			List<Personne> personnes = new ArrayList<>();

	        String SQL = "SELECT * FROM personne";
	        try {
	        	stmt = conn.createStatement();
	            rs = stmt.executeQuery(SQL);

	            while (rs.next()) {

	            	int id = rs.getInt(1);
	                String firstName = rs.getString(2);
	                String lastName = rs.getString(3);

	                Personne peronne = new Personne(id, firstName, lastName);
	                personnes.add(peronne);
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	        return personnes;
		}
}
