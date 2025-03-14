package it.polito.tdp.lab04.model;

	public class Studente {
	
		// CREO LA CLASSE DEFINENDO GLI ATTRIBUTI,
		// IL COSTRUTTORE, I METODI GETTER E SETTER
		// I METODI HASCODE E EQUALS (SOLO PER LA CHIAVE!- matricola)
		// IL METODO TOSTRING (LO MODIFICO A SECONDA DELLE NECESSITA')
		
	
		private Integer matricola;
		private String cognome;
		private String nome;
		private String CDS;
		
		
		
		public Studente(Integer matricola) {
			super();
			this.matricola = matricola;
		}
	
		public Studente(Integer matricola, String cognome, String nome, String cDS) {
			super();
			this.matricola = matricola;
			this.cognome = cognome;
			this.nome = nome;
			CDS = cDS;
		}
	
		public Integer getMatricola() {
			return matricola;
		}
	
		public void setMatricola(Integer matricola) {
			this.matricola = matricola;
		}
	
		public String getCognome() {
			return cognome;
		}
	
		public void setCognome(String cognome) {
			this.cognome = cognome;
		}
	
		public String getNome() {
			return nome;
		}
	
		public void setNome(String nome) {
			this.nome = nome;
		}
	
		public String getcDS() {
			return CDS;
		}
	
		public void setCDS(String cDS) {
			CDS = cDS;
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Studente other = (Studente) obj;
			if (matricola == null) {
				if (other.matricola != null)
					return false;
			} else if (!matricola.equals(other.matricola))
				return false;
			return true;
		}
	
		@Override
		public String toString() {
			return nome+" "+cognome;
		}
			
}