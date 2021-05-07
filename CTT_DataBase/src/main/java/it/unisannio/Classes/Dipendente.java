package it.unisannio.Classes;

import java.io.PrintStream;
import java.util.Date;

import it.unisannio.Constants.Constants;
import it.unisannio.TipiAggiuntivi.Cdf;
import it.unisannio.TipiAggiuntivi.RuoloDipendente;

public class Dipendente {
	
	private final Cdf cdf;
	private final String nome;
	private final String cognome;
	private final Date dataDiNascita;
	private final RuoloDipendente ruolo;
	private final String username;
	private String password;
	
	public Dipendente(Cdf cdf, String nome, String cognome, Date dataDiNascita, RuoloDipendente ruolo, String username, String password) {
		assert cdf!= null && nome != null && cognome != null && dataDiNascita != null && ruolo != null && username != null && password != null;
		this.cdf = cdf;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.ruolo = ruolo;
		this.username = username;
		this.password = password;
	}	
	
	public Cdf getCdf() {
		return cdf;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public String getDataDiNascitaS() {
		return Constants.sdf.format(dataDiNascita);
	}
	
	public RuoloDipendente getRuolo() {
		return ruolo;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return cdf + " - nome: " + nome + " - cognome: " + cognome + " - dataDiNascita: " + dataDiNascita + " - ruolo: " + ruolo  + " - username: " + username + " - password: " + password; 
	}
	
	public boolean equals(Object o) {
		return ((Dipendente) o).getCdf().toString().equals(this.getCdf().toString());
	}
	
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Codice Fiscale: "+this.cdf);
		ps.println("Nome: "+this.nome);
		ps.println("Cognome: "+this.cognome);
		ps.println("DataDiNascita: "+getDataDiNascitaS());
		ps.println("Ruolo: "+this.ruolo);
		ps.println("Username: "+this.username);
		ps.println("Password: "+this.password);
	}
}