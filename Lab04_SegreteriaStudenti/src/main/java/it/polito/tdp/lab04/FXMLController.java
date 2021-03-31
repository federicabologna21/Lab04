/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.sun.scenario.effect.Blend.Mode;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	
	private Model model;
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCorsi"
    private ComboBox<Corso> boxCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscritti"
    private Button btnCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="txtInserisci"
    private TextField txtInserisci; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private CheckBox btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader
    
    
    /***
     * PUNTO 4 
     * RICERCA CORSI A CUI E' ISCRITTO UNO STUDENTE
     */
    
    @FXML
    void CercaCorsi(ActionEvent event) {
    	
    	String matricolaStringa = txtInserisci.getText();
    	Integer matricola;
    	Studente studente = null;
    	
    	try {
    		matricola =Integer.parseInt(matricolaStringa); // TRASFORMO MATRICOLA IN INT 
    		studente = model.getStudente(matricola);
    		
    		// CONTROLLO SE STUDENTE NON E' PRESENTE 
    		if (studente == null) {
    			txtRisultato.appendText(" Errore: lo studente "+ studente+" non è presente");
    			return;
    		}
    		
    	}catch(NumberFormatException e) { // TRASFORMAZIONE IN INTERO NON ANDATA A BUON FINE
    		txtRisultato.setText("Errore: la matricola deve essere un numero");
    		return;
    		
    	} catch(NullPointerException ne) { // UTENTE NON HA INSERITO MATRICOLA
    		txtRisultato.setText("Errore: caratteri vuoti, devi inserire un numero");
    		return;	
    	}
    	txtRisultato.setText(this.model.getCorsiStudente(matricola));

    }

    /***
     * PUNTO 3
     * ELENCO STUDENTI ISCRITTI A UN CORSO SELEZIONATO 
     */
    @FXML
    void CercaIscrittiCorso(ActionEvent event) {
    	
    	// PRENDO IL CORSO SELEZIONATO NELLA COMBO-BOX
    	Corso scelta = boxCorsi.getValue();
    	
    	// CONTROLLO SE NON E' STATO SELEZIONATO NESSUN CORSO
    	if (scelta == null) {
    		txtRisultato.setText("Selezionare un corso");
    		return;
    	}
    	txtRisultato.setText(this.model.getStudentiIscritti(scelta));

    }

    /***
     * PUNTO 2
     * CHECK VERDE CHE AGGIUNGE NOME E COGNOME PER LA MATRICOLA SCRITTA
     */
    @FXML
    void doCheck(ActionEvent event) {
    	
    	Studente studente=null;
    	String matricolaStringa= txtInserisci.getText();
    	Integer matricola;
    	
    	try {
    		
    	 matricola=Integer.parseInt(matricolaStringa);
    	 studente=model.getStudente(matricola);
    		
    	 // CONTROLLO SE STUDENTE NON E' PRESENTE 
    	 if(studente==null) {
    			txtRisultato.appendText("Nessuno studente presente");
    			return ;
    		}
    		

    		
    	} catch(NumberFormatException e) {// TRASFROMAZIONE INT NON ANDATA A BUON FINE
    		txtRisultato.setText("Errore: la matricola deve essere un numero");
    		return;
    	} catch(NullPointerException ne) { // UTENTE NON HA SCRITTO MATRICOLA
    		txtRisultato.setText("Errore: caratteri vuoti, devi inserire un numero");
    		return;
    		
    	}
   
    
	     txtNome.setText(studente.getNome());
	     txtCognome.setText(studente.getCognome());
     
     
    }
    
  
    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	// CONTROLLO SUL BOX DEL CORSO SCELTO
    	Corso scelta = boxCorsi.getValue();
    	
    	if (scelta == null) {
    		txtRisultato.setText("Selezionare un corso");
    		return;
    	}
    	
    	// CONTROLLO SULLA MATRICOLA INSERITA 
    	String matricolaStringa = txtInserisci.getText();
    	Integer matricola;
    	Studente studente = null;
    	
    	try {
    		matricola =Integer.parseInt(matricolaStringa);
    		studente = model.getStudente(matricola);
    		
    		if (studente == null) {
    			txtRisultato.appendText(" Errore: lo studente "+ studente+" non è presente");
    			return;
    		}
    		
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Errore: la matricola deve essere un numero");
    		return;
    		
    	} catch(NullPointerException ne) {
    		txtRisultato.setText("Errore: caratteri vuoti, devi inserire un numero");
    		return;	
    	}
    	
    	// --> SE MI RITROVO QUA TUTTO E' STATO INSERITO CORRETTAMENTE 
    	
    	// SE LO STUDENTE E' GIA' ISCRITTO (METODO A TRUE)
    	if (this.model.cercaStudenteInCorso(studente, scelta)) {
    		txtRisultato.setText("Lo studente con matricola '"+studente.getMatricola()+"' è già iscritto a questo corso");
    	}
    	// SE LO STUDENTE NON E' ANCORA ISCRITTO (METODO A FALSE) LO ISCRIVO
    	else {
    		boolean iscrittoSuccesso = this.model.inscriviStudenteACorso(studente, scelta);
    		if (iscrittoSuccesso) {
    			txtRisultato.setText("Lo studete con matricola '"+studente.getMatricola()+"' è stato iscritto con successo al corso '"+scelta.getNome()+"'");
    		}
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	// RIPULISCO TUTTI I CAMPI 
    	txtRisultato.clear();
    	txtInserisci.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	boxCorsi.getItems().clear();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	for(Corso c: model.getTuttiICorsi()) {
    		
    		boxCorsi.getItems().add(c);
    	}
    }

	
		
}