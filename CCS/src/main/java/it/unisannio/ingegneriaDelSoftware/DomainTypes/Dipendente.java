package it.unisannio.ingegneriaDelSoftware.DomainTypes;

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

	/**Metodo Costruttore dell'oggetto Dipendente
	 * @param cdf Codice fiscale del dipendente
	 * @param nome Nome del dipendente
	 * @param cognome Cognome del del dipendente
	 * @param dataDiNascita Data di nascita del dipendente
	 * @param ruolo Ruolo del dipendente nel centro CTT
	 * @param password Password del dipendente sul sistema CTT
	 * @param username Username del dipendente sul sistema CTT
	 */
	public Dipendente(Cdf cdf, String nome, String cognome, LocalDate dataDiNascita, RuoloDipendente ruolo, String username, String password) {
		assert cdf!= null: "Il codice fiscale del dipendente non può essere null";
		assert nome != null: "Il nome del dipendente non puo essere null";
		assert cognome != null: "Il cognome del dipendente non può essere null";
		assert dataDiNascita != null: "La data di nascita del dipendente non può essere null";
		assert ruolo != null: "Il ruolo del dipendente non può essere null";
		assert username != null: "L'username del dipendente non può essere null";
		assert password != null: "La password del dipendente non può essere null";

		if(dataDiNascita.getYear()>LocalDate.now().getYear()) throw new IllegalArgumentException("La data di nascita non può essere successiva a quella odierna");
		if (dataDiNascita.isAfter(LocalDate.now())) throw new IllegalArgumentException("La data di nascita non può essere successiva a quella odierna");
		if (!dataDiNascita.isBefore(LocalDate.now())) throw new IllegalArgumentException("La data di nascita non può essere successiva o coincidente a quella odierna");
		if (!dataDiNascita.isBefore(LocalDate.now().minusYears(18))) throw new IllegalArgumentException(" il Dipendente deve avere almeno 18 anni");
		if(dataDiNascita.isBefore(LocalDate.now().minusYears(80))) throw  new IllegalArgumentException("Dipendente troppo anziano, inserisci una data di nascita valida");
		if(!password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})"))
			throw new IllegalArgumentException("La password deve contenere almeno un numero, una lettera minuscola e una lettera Maiuscola, inoltre deve essere di 8 caratteri");

		this.cdf = cdf;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.ruolo = ruolo;
		this.username = username;
		this.password = password;
	}	
	
	/**Restituisce il codice fiscale di un Dipendente
	 * @return codice fiscale Il codice fiscale di un Dipendente
	 */
	public Cdf getCdf() {
		return cdf;
	}

	/**Restituisce il nome del Dipendente
	 * @return nome Il nome del Dipendente
	 */
	public String getNome() {
		return nome;
	}

	/**Restituisce il cognome del Dipendente
	 * @return cognome Il cognome del Dipendente
	 */
	public String getCognome() {
		return cognome;
	}

	/**Restituisce la data di nascita del Dipendente
	 * @return datadiNascita La data di nascita del Dipendente
	 */
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	/**Restituisce il ruolo del Dipendente
	 * @return ruolo Il ruolo del Dipendente
	 */
	public RuoloDipendente getRuolo() {
		return ruolo;
	}

	/**Restituisce l'username del Dipendente
	 * @return username L'username del Dipendente
	 */
	public String getUsername() {
		return username;
	}

	/**Restituisce la password del Dipendente
	 * @return password La password del Dipendente
	 */
	public String getPassword() {
		return password;
	}

	/**Setta la nuova password del Dipendente
	 *@param password La nuova password del Dipendente sul sistema CTT che deve contenere almeno un numero
	 */
	public void setPassword(String password) {
		assert password!= null: "La password del dipendente non può essere null";
		if(!password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})"))
			throw new IllegalArgumentException("La password deve contenere almeno un numero, deve essere di 8 caratteri, ed avere almeno una lettera grande");		
		this.password = password;
	}

	/**Stampa le informazioni di un Dipendente
	 * @param ps stream di output su cui stampare i dati del Dipendente 
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