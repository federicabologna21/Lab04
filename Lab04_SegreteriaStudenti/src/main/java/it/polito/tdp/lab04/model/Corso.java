package it.polito.tdp.lab04.model;

public class Corso {

	// CREO LA CLASSE DEFINENDO GLI ATTRIBUTI,
	// IL COSTRUTTORE, I METODI GETTER E SETTER
	// I METODI HASCODE E EQUALS (SOLO PER LA CHIAVE!- Codins)
	// IL METODO TOSTRING (LO MODIFICO A SECONDA DELLE NECESSITA')
	

	//creo questa classe dopo aver creato la CorsoDAO
	/**
	 * CHIAMO I NOMI COME LE COLONNE DEL DATABASE
	 */
	
	private String codins;
	private Integer crediti;
	private String nome;
	private Integer pd;
	
	public Corso(String codins, Integer crediti, String nome, Integer pd) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}

	public String getCodins() {
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public Integer getCrediti() {
		return crediti;
	}

	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPd() {
		return pd;
	}

	public void setPd(Integer pd) {
		this.pd = pd;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
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
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	// MODIFICO CON SOLO NOME PERCHE' 
	// MI SERVE SOLO QUESTO
	@Override
	public String toString() {
		return nome;
	}
	
}