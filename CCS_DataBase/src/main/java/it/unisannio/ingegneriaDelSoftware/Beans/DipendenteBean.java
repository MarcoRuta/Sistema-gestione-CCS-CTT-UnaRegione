package it.unisannio.ingegneriaDelSoftware.Beans;

import java.time.LocalDate;


/**A simple Bean Object usato per salvare i dati nel MongoDB in maniera più agevole*/
public class DipendenteBean {
	private String cdf;
	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;
	private String ruolo;
	private String username;
	private String password;
	
	public DipendenteBean(String cdf, String nome, String cognome, LocalDate dataDiNascita, String ruolo,
			String username, String password) {
		this.cdf = cdf;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.ruolo = ruolo;
		this.username = username;
		this.password = password;
	}
	
	public DipendenteBean(){
	}

	public String getCdf() {
		return cdf;
	}

	public void setCdf(String cdf) {
		this.cdf = cdf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
