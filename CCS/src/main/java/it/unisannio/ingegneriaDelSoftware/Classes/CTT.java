package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.util.Objects;
import java.lang.Math;

public class CTT{

	private final CTTName denominazione;
	private final String telefono;
	private final String email;
	private final CTTPosition posizione;
	

	/**Metodo Costruttore di CTT
	 * @param provincia la provincia in cui è situato il CTT
	 * @param città la città in cui è situato il CTT
	 * @param telefono il numero di telefono del CTT
	 * @param indirizzo l'indirizzo del CTT
	 * @param email l'email ufficiale del CTT
	 * @param latitudine
	 * @param longitudine
	 */
	public CTT(String provincia, String città, String telefono, String indirizzo, String email, double latitudine, double longitudine) {
		assert telefono != null: "il telefono non può essere null";
		assert email != null: "l'e_mail non può essere null";
		if (telefono.length()!=10 || !(telefono.matches("^[0-9]*$"))) throw new IllegalArgumentException("Formato del numero di telefono non valido");
		if (!email.matches("^(.+)@(.+)$")) throw new IllegalArgumentException("Formato dell'email non valido");

		this.posizione = new CTTPosition(provincia,città,indirizzo,latitudine,longitudine);
		this.denominazione = new CTTName();
		this.telefono = telefono;
		this.email = email;	
	}

	/**Metodo Costruttore di CTT
	 * @param denominazione il nome del CTT
	 * @param provincia la provincia in cui è situato il CTT
	 * @param città la città in cui è situato il CTT
	 * @param telefono il numero di telefono del CTT
	 * @param indirizzo l'indirizzo del CTT
	 * @param email l'email ufficiale del CTT
	 * @param latitudine
	 * @param longitudine
	 */
	public CTT(CTTName denominazione,String provincia, String città, String telefono, String indirizzo, String email, double latitudine, double longitudine) {
		assert telefono != null: "il telefono non può essere null";
		assert email != null: "l'e_mail non può essere null";
		if (telefono.length()!=10 || !(telefono.matches("^[0-9]*$"))) throw new IllegalArgumentException("Formato del numero di telefono non valido");
		if (!email.matches("^(.+)@(.+)$")) throw new IllegalArgumentException("Formato dell'email non valido");

		this.posizione = new CTTPosition(provincia,città,indirizzo,latitudine,longitudine);
		this.denominazione = denominazione;
		this.telefono = telefono;
		this.email = email;
	}


	/**Metodo costruttore di CTT con CTTPosition
	 * @param denominazione  il nome del CTT
	 * @param telefono il numero di telefono del CTT
	 * @param email l'email ufficiale del CTT
	 * @param posizione la posizione del CTT
	 */ 
	public CTT(CTTName denominazione, String telefono, String email, CTTPosition posizione) {
		assert denominazione != null: "La denominazione non può essere null";
		assert telefono != null: "Il telefono non può essere null";
		assert email != null: "L'email non può essere null";
		if (telefono.length()!=10 || !(telefono.matches("^[0-9]*$"))) throw new IllegalArgumentException("Formato del numero di telefono non valido");
		if (!email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) throw new IllegalArgumentException("Formato dell'email non valido");

		this.denominazione = denominazione;
		this.telefono = telefono;
		this.email = email;
		this.posizione = posizione;
	}



	/**Stampa le informazioni di un CTT
	 * @param ps stream di output su cui stampare i dati del CTT
	 */
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Denominazione: "+ this.denominazione.getCttname());
		ps.println("Telefono: "+ this.telefono);
		ps.println("email: "+ this.email);
		this.getPosizione().print(ps);
	}

	
	/**Calcola la distanza tra due punti applicando la formula dell'emisenoverso
	 * @param CTT Il ctt da cui s vuole calcolare la distanza rispetto a quello attuale
	 * @return La distanza tra i due CTT
	 */
	public double distanzaDalCtt(CTT c){
		double distanza = -1;
		final int R = 6371;  
		double distanzalat = Math.toRadians(c.getPosizione().getLatitudine() - this.getPosizione().getLatitudine());
	    double distanzalon = Math.toRadians(c.getPosizione().getLongitudine() - this.getPosizione().getLongitudine());
	    
	    Double x = Math.sin(distanzalat / 2) * Math.sin(distanzalat / 2) + Math.cos(Math.toRadians(this.getPosizione().getLatitudine())) * Math.cos(Math.toRadians(c.getPosizione().getLatitudine())) * Math.sin(distanzalon / 2) * Math.sin(distanzalon / 2);
		Double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
		distanza = R*y;
		return distanza;
	}


	
	/**Restituisce il recapito telefonico del CTT
	 * @return Telefono Numero di telefono del CTT
	 */
	public String getTelefono() {
		return telefono;
	}
	
	
	/**Restituisce l'email del CTT
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}
	
	
	/**Restituisce l'ubicazione del CTT
	 * @return Ubicazione
	 */
	public CTTPosition getPosizione() {
		return posizione;
	}

	/**Restituisce la denominazione del CTT
	 * @return denominazione
	 */
	public CTTName getDenominazione() {
		return denominazione;
	}

	@Override
	public String toString() {
		return "CTT{" +
				"denominazione=" + denominazione +
				", telefono='" + telefono + '\'' +
				", email='" + email + '\'' +
				", posizione=" + posizione +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CTT ctt = (CTT) o;
		return Objects.equals(denominazione, ctt.denominazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(denominazione);
	}
}