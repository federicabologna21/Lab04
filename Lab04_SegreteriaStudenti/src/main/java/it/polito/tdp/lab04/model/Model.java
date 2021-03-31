package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	// IN 'MODEL' INVOCO TUTTI I METODI SCRITTI 
	// NELLE CLASSI DAO CHE MI SERVONO NEL CONTROLLER
	
	private CorsoDAO corsodao;
	private StudenteDAO studentedao;

	public Model() {
		corsodao = new CorsoDAO();
		studentedao= new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsodao.getTuttiICorsi();
	}
	
	public Studente getStudente(Integer matricola) {
		return studentedao.getStudente(matricola);
	}
	
	public String getStudentiIscritti(Corso corso) {
		return corsodao.getStudentiIscrittiAlCorso(corso);
	}
	
	public String getCorsiStudente (Integer matricola) {
		return studentedao.getCorsiStudente(matricola);
	}
	
	public boolean cercaStudenteInCorso (Studente studente, Corso corso) {
		return corsodao.cercaStudenteInCorso(studente, corso);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsodao.inscriviStudenteACorso(studente, corso);
	}
}
