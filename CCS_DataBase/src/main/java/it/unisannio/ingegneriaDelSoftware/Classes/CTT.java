package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.util.Objects;
import java.lang.Math;

public class CTT{
	
	private final Integer numero;
	private String denominazione;
	private final String telefono;
	private final String e_mail;
	private final CTTPosition posizione;
	
	
	/**
	 * Metodo costruttore di CTT
	 * 
	 * @param numero  l'identificativo del CTT
	 * @param denominazione  il nome del CTT
	 * @param provincia la provincia in cui è  situato il CTT
	 * @param citta la citta in cui è situato il CTT
	 * @param indirizzo l'indirizzo del CTT
	 * @param telefono il numero di telefono del CTT
	 * @param e_mail l'email ufficiale del CTT
	 * 
	 * */
	public CTT(Integer numero, String denominazione, String provincia, String citta, String telefono, String indirizzo, String e_mail, double latitudine, double longitudine) {
		assert numero != null: "Il numero del CTT non puo essere null";
		assert denominazione != null: "La denominazione non puo essere null";
		assert telefono != null: "il telefono non puo essere null";
		assert e_mail != null: "l'e_mail non puo essere null";
		
		this.posizione = new CTTPosition(provincia,citta,indirizzo,latitudine,longitudine);
		
		this.numero = numero;
		this.denominazione = denominazione;
		this.telefono = telefono;
		this.e_mail = e_mail;
	
	}


	/**
	 * Metodo che stampa le informazioni di un CTT
	 * 
	 * @param ps  stream di output su cui stampare i dati del CTT
	 * 
	 */
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Numero: "+ this.numero);
		ps.println("Denominazione: "+ this.denominazione);
		ps.println("Telefono: "+ this.telefono);
		ps.println("e-mail: "+ this.e_mail);
		this.getPosizione().print(ps);
	}

	/**
	 * Metodo che verifica l'uguaglianza tra due CTT
	 * 
	 * @return Un boolean true o false a seconda dell'esito del confronto
	 *
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CTT ctt = (CTT) o;
		return numero.equals(ctt.numero);
	}

	/**
	 * Metodo che calcola l'hashcode di un CTT
	 * 
	 * @return Un intero pari all'hashcode generato
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	/**
	 * Metodo che concatena in un'unica stringa le informazioni di un CTT
	 *  * 
	 * @return La stringa concatenata
	 * 
	 */
	@Override
	public String toString() {
		return "CTT{" +
				"numero=" + numero +
				", denominazione=" + denominazione +
				", telefono=" + telefono +
				", e_mail=" + e_mail +
				posizione.toString() +
				'}';
	}
	
   /**
	 * Metodo che calcola la distanza tra due punti applicando la formula dell'emisenoverso
	 * 
	 * @param CTT Il ctt da cui voglio calcolare la distanza rispetto a quello attuale
	 * @return La distanza tra i due CTT
	 * 
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

	/**
	 * Metodo che restituisce l'identificativo del CTT
	 * 
	 * @return  identificativo
	 *
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Metodo che restituisce la denominazione del CTT
	 * 
	 * @return  Denominazione
	 *
	 */
	public String getDenominazione() {
		return denominazione;
	}
	
	
	/**
	 * Metodo che restituisce il recapito telefonico del CTT
	 * 
	 * @return  Telefono
	 *
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Metodo che restituisce l'email del CTT
	 * 
	 * @return  Email
	 *
	 */
	public String getEmail() {
		return e_mail;
	}
	
	/**
	 * Metodo che restituisce l'ubicazione del CTT
	 * 
	 * @return  Ubicazione
	 *
	 */
	public CTTPosition getPosizione() {
		return posizione;
	}
	
	

	
}