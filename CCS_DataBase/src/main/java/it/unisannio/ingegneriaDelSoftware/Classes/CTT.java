package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.util.Objects;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.lang.Math;

public class CTT{
	
	@BsonProperty(useDiscriminator = true)
	private final Integer numero;
	@BsonProperty(useDiscriminator = true)
	private final String denominazione;
	@BsonProperty(useDiscriminator = true)
	private final String telefono;
	@BsonProperty(useDiscriminator = true)
	private final String email;
	@BsonProperty(useDiscriminator = true)
	private final CTTPosition posizione;
	

	/**
	 * Metodo costruttore di CTT
	 * 
	 * @param numero  l'identificativo del CTT
	 * @param denominazione  il nome del CTT
	 * @param provincia la provincia in cui è situato il CTT
	 * @param città la città in cui è situato il CTT
	 * @param indirizzo l'indirizzo del CTT
	 * @param telefono il numero di telefono del CTT
	 * @param e_mail l'email ufficiale del CTT
	 * @param latitudine
	 * @param longitudine
	 * 
	 * */
	public CTT(Integer numero, String denominazione, String provincia, String città, String telefono, String indirizzo, String email, double latitudine, double longitudine) {
		assert numero != null: "Il numero del CTT non può essere null";
		assert denominazione != null: "La denominazione non può essere null";
		assert telefono != null: "il telefono non può essere null";
		assert email != null: "l'e_mail non può essere null";
		
		this.posizione = new CTTPosition(provincia,città,indirizzo,latitudine,longitudine);
		
		this.numero = numero;
		this.denominazione = denominazione;
		this.telefono = telefono;
		this.email = email;
	
	}
	
	
	/**
	 * Metodo costruttore di CTT
	 * 
	 * @param numero  l'identificativo del CTT
	 * @param denominazione  il nome del CTT
	 * @param telefono il numero di telefono del CTT
	 * @param e_mail l'email ufficiale del CTT
	 * @param latitudine
	 * @param longitudine
	 * */
	@BsonCreator
	public CTT(@BsonProperty("numero")Integer numero, 
			@BsonProperty("denominazione")String denominazione, 
			@BsonProperty("telefono")String telefono, 
			@BsonProperty("email")String email, 
			@BsonProperty("posizione")CTTPosition posizione) {
		assert numero != null: "Il numero del CTT non può essere null";
		assert denominazione != null: "La denominazione non può essere null";
		assert telefono != null: "il telefono non può essere null";
		assert email != null: "l'e_mail non può essere null";
		
		this.numero = numero;
		this.denominazione = denominazione;
		this.telefono = telefono;
		this.email = email;
		this.posizione = posizione;
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
		ps.println("email: "+ this.email);
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
				", email=" + email +
				", "+posizione.toString() +
				'}';
	}
	
	
	/*
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
		return email;
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