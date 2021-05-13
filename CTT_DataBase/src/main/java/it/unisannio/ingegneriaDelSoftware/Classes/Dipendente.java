package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;

public class Dipendente {
	
	private final Cdf cdf;
	private final String nome;
	private final String cognome;
	private final LocalDate dataDiNascita;
	private final RuoloDipendente ruolo;
	private final String username;
	private String password;

	/**
	 * @param cdf  il codice fiscale del dipendente
	 * @param cognome  il cognome del del dipendente
	 * @param dataDiNascita  la data di nascita del dipendente
	 * @param nome  il nome del dipendente
	 * @param password  la password del dipendente sul sistema CTT
	 * @param username  l'username del dipendente sul sistema CTT
	 * @param ruolo  il ruolo del dipendente nel centro CTT*/
	public Dipendente(Cdf cdf, String nome, String cognome, LocalDate dataDiNascita, RuoloDipendente ruolo, String username, String password) {
		assert cdf!= null: "Il codice fiscale del dipendente non può essere null";
		assert nome != null: "Il nome del dipendente non puo essere null";
		assert cognome != null: "Il cognome del dipendente non può essere null";
		assert dataDiNascita != null: "La data di nascita del dipendente non può essere null";
		assert ruolo != null: "Il ruolo del dipendente non può essere null";
		assert username != null: "L'username del dipendente non può essere null";
		assert password != null: "La password del dipendente non può essere null";

		this.cdf = cdf;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.ruolo = ruolo;
		this.username = username;
		this.password = password;
	}	
	/**@return il codice fiscale del dipendente*/
	public Cdf getCdf() {
		return cdf;
	}

	/**@return il nome del dipendente*/
	public String getNome() {
		return nome;
	}

	/**@return il cognome del dipendente*/
	public String getCognome() {
		return cognome;
	}

	/**@return la data di nascita del dipendente*/
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	/**@return il ruolo del dipendente*/
	public RuoloDipendente getRuolo() {
		return ruolo;
	}

	/**@return l'username del dipendente sul sistema CTT*/
	public String getUsername() {
		return username;
	}

	/**@return la password del dipendente sul sistem CTT*/
	public String getPassword() {
		return password;
	}

	/**@param password  la password del dipendente sul sistema CTT*/
	public void setPassword(String password) {
		assert password!= null: "La password del dipendente non può essere null";
		this.password = password;
	}

	/**@param ps stream di output su cui stampare i dati del dipendente*/
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Codice Fiscale: "+this.cdf.getCodiceFiscale());
		ps.println("Nome: "+this.nome);
		ps.println("Cognome: "+this.cognome);
		ps.println("DataDiNascita: "+this.dataDiNascita);
		ps.println("Ruolo: "+this.ruolo);
		ps.println("Username: "+this.username);
		ps.println("Password: "+this.password);
	}

	@Override
	public String toString() {
		return "Dipendente{" +
				"cdf=" + cdf +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", dataDiNascita=" + dataDiNascita +
				", ruolo=" + ruolo +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dipendente that = (Dipendente) o;
		return cdf.equals(that.cdf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdf);
	}
}