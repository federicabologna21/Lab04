package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
    
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);	
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
		String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE codins = ?";
		Corso c = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				c= new Corso (corso.getCodins(),rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd") );
			}
			conn.close();
		
		}
		 catch(SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
		  }	
		
	}
		

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public String getStudentiIscrittiAlCorso(Corso corso) {
		
		String sql = "SELECT i.matricola, i.codins, s.cognome, s.nome "
				+ "FROM corso c, iscrizione i, studente s "
				+ "WHERE c.codins = i.codins AND i.codins = ? AND i.matricola = s.matricola";
		
		String studentiIscrittiCorso = "";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				studentiIscrittiCorso += rs.getInt("matricola")+" "+rs.getString("cognome")+" "+rs.getString("nome") + "\n";
				
			}
			conn.close();
		
		}
		 catch(SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
		  }	
		return studentiIscrittiCorso;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}