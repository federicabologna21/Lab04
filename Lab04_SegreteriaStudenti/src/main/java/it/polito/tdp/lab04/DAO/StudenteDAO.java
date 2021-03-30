package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	public Studente getStudente(Integer matricola) {
		
		String sql="SELECT * "
				+ "FROM Studente "
				+ "WHERE Matricola = ?";
		
		
		Studente s=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
		
	 s= new Studente(matricola, rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
			}
		
			conn.close();
		
		}
		 catch(SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
		  }	
		
		return s;
	}
}
