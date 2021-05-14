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
	 * Metodo Costruttore dell'oggetto Dipendente
	 * 
	 * @param cdf  il codice fiscale del dipendente
	 * @param cognome  il cognome del del dipendente
	 * @param dataDiNascita  la data di nascita del dipendente
	 * @param nome  il nome del dipendente
	 * @param password  la password del dipendente sul sistema CTT
	 * @param username  l'username del dipendente sul sistema CTT
	 * @param ruolo  il ruolo del dipendente nel centro CTT
	 */
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
	
	/**
	 * Metodo che restituisce il codice ficale di un dipendente
	 * 
	 * @return codice fiscale
	 *
	 */
	public Cdf getCdf() {
		return cdf;
	}

	/**
	 * Metodo che restituisce il nome del dipendente
	 * 
	 * @return nome
	 *
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che restituisce il cognome del dipendente
	 * 
	 * @return cognome
	 *
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Metodo che restituisce la data di nascita del dipendente
	 * 
	 * @return data di nascita
	 *
	 */
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * Metodo che restituisce il ruolo del dipendente
	 * 
	 * @return ruolo
	 *
	 */
	public RuoloDipendente getRuolo() {
		return ruolo;
	}

	/**
	 * Metodo che restituisce l'username del dipendente
	 * 
	 * @return username
	 *
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Metodo che restituisce la password del dipendente
	 * 
	 * @return password
	 *
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo che permette di settare la password del dipendente
	 * @param password La nuova password 
	 *
	 */
	public void setPassword(String password) {
		assert password!= null: "La password del dipendente non può essere null";
		this.password = password;
	}

	/**
	 * Metodo che stampa le informazioni di un dipendente 
	 * 
	 * @param ps  stream di output su cui stampare i dati del dipendente
	 * 
	 */
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Codice Fiscale: "+this.cdf);
		ps.println("Nome: "+this.nome);
		ps.println("Cognome: "+this.cognome);
		ps.println("DataDiNascita: "+this.dataDiNascita);
		ps.println("Ruolo: "+this.ruolo);
		ps.println("Username: "+this.username);
		ps.println("Password: "+this.password);
	}

	/**
	 * Metodo che concatena in un'unica stringa le informazioni del dipendente
	 *  * 
	 * @return La stringa concatenata
	 * 
	 */
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

	/**
	 * Metodo che verifica l'uguaglianza tra due dipendenti
	 * 
	 * @return Un boolean true o false a seconda dell'esito del confronto
	 *
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dipendente that = (Dipendente) o;
		return cdf.equals(that.cdf);
	}

	/**
	 * Metodo che calcola l'hashcode di un dipendente
	 * 
	 * @return Un intero pari all'hashcode generato
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cdf);
	}
}