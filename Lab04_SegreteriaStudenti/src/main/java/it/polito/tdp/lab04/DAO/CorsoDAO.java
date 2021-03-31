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
	 * OTTENGO TUTTI I CORSI NEL DATABASE
	 */
	public List<Corso> getTuttiICorsi() {

		// CREO SQL 
		final String sql = "SELECT * FROM corso";

		// CREO ELEMENTO CHE DEVO RITORNARE - IN QUESTO CASO LIST
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
    
			ResultSet rs = st.executeQuery();

			// QUANDO HO UNA LISTA O UNA STRING CON 
			// TANTI ELEMENTI METTO WHILE 
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
	 * DATO UN CODICE DI INSEGAMENTO OTTENGO UN CORSO 
	 */
	public void getCorso(Corso corso) {
		
		// SCRIVO LA QUERY
		String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE codins = ?";
		
		// CREO ELEMENTO 
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
	 * DATO UN CORSO OTTENGO TUTTI GLI STUDENTI ISCRITTI
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
			
			
			// DEVO RESTITUIRE UNA STRINGA DI ELEMENTI
			// QUINDI METTO WHILE
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
	 * DATA UNA MATRICOLA E UN CORSO CERCO SE LO STUDENTE E' ISCRITTO AL CORSO 
	 */
	public boolean cercaStudenteInCorso (Studente studente, Corso corso) {
		
		// MI CREO QUELLO CHE DEVO RITORNARE
		boolean isIscritto = false;
		
		// DEFINISCO LA QUERY
		String sql = "SELECT i.codins, c.nome, i.matricola, s.nome, s.cognome "
				+ "FROM corso c, iscrizione i, studente s "
				+ "WHERE i.codins = c.codins AND i.matricola = s.matricola AND s.matricola = ? AND i.codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			// HO DUE '?' QUINDI MI FACCIO DUE st.set 
			st.setInt(1,  studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			// HO UN SOLO ELEMENTO DA VERIFICARE 
			// QUINDI METTO IF
			if(rs.next()) {
				isIscritto = true;
			}
			conn.close();
		}catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
	  }	
		
		return isIscritto;
	}
	
	
	
	/*
	 * DATA UNA MATRICOLA E UN CODICE DI INSEGNAMENTO, ISCRIVO LO STUDENTE AL CORSO
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		// QUERY PER INSERIRE UN ELEMENTO NEL DATABASE
		String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?, ?)";
		
		boolean iscrittoCorrettamente = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,  studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			
			// COMANDO PER INSERIRE ELEMENTO NEL DATABASE!
			// E' UN INT !!!
			int rs = st.executeUpdate();
			
			// EFFETTUO SEMPRE IL CONTROLLO 
			// SE E' =1 ALLORA L'INSERIMENTO
			// E' ANDATO A BUON FINE
			if (rs == 1) {
				iscrittoCorrettamente = true;
			}
			
			conn.close();
		}catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}	
		
		// ritorna true se l'iscrizione e' avvenuta con successo
		return iscrittoCorrettamente ;
	}
	
	

}