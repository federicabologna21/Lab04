package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	
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
}
