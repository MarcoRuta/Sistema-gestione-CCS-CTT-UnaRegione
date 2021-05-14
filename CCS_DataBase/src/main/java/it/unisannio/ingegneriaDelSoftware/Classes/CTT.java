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
	
	

	/**Metodo costruttore di CTT, accetta tutti i parametri
	 * 
	 * @param numero il numero identificativo del CTT
	 * @param denominazione  il nome del CTT
	 * @param provincia la provincia in cui è situato il CTT
	 * @param città la città in cui è situato il CTT
	 * @param indirizzo l'indirizzo del CTT
	 * @param telefono il numero di telefono del CTT
	 * @param e_mail l'email ufficiale del CTT
	 * 
	 * */
	public CTT(Integer numero, String denominazione, String provincia, String città, String telefono, String indirizzo, String e_mail, double latitudine, double longitudine) {
		assert numero != null: "Il numero del CTT non può essere null";
		assert denominazione != null: "La denominazione non può essere null";
		assert telefono != null: "il telefono non può essere null";
		assert e_mail != null: "l'e_mail non può essere null";
		
		this.posizione = new CTTPosition(provincia,città,indirizzo,latitudine,longitudine);
		
		this.numero = numero;
		this.denominazione = denominazione;
		this.telefono = telefono;
		this.e_mail = e_mail;
	
	}


	/**metodo di stampa per i CTT
	 * 
	 * @param ps  stream di output su cui stampare i dati della sacca
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CTT ctt = (CTT) o;
		return numero.equals(ctt.numero);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

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
	
	/**Questo metodo calcola la distanza tra due punti applicando la formula dell'emisenoverso, per fornire un'informazione abbastanza affidabile per calcolare la distanza tra due CTT
	 * 
	 * @param ctt il ctt da cui voglio calcolare la distanza rispetto a quello attuale
	 * @return distance la distanza tra i due CTT
	 * 
	 */
	public double distanzaDalCtt(CTT c){
		double distanza = -1;
		final int R = 6371;  //raggio della terra
		double distanzalat = Math.toRadians(c.getPosizione().getLatitudine() - this.getPosizione().getLatitudine());
	    double distanzalon = Math.toRadians(c.getPosizione().getLongitudine() - this.getPosizione().getLongitudine());
	    
	    Double x = Math.sin(distanzalat / 2) * Math.sin(distanzalat / 2) + Math.cos(Math.toRadians(this.getPosizione().getLatitudine())) * Math.cos(Math.toRadians(c.getPosizione().getLatitudine())) * Math.sin(distanzalon / 2) * Math.sin(distanzalon / 2);
		Double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
		distanza = R*y;
		return distanza;
	}

	/**@return il numero del CTT*/
	public int getNumero() {
		return numero;
	}
	
	/**@return la denominazione del CTT*/
	public String getDenominazione() {
		return denominazione;
	}
	
	
	/**@return il numero di telefono del CTT*/
	public String getTelefono() {
		return telefono;
	}
	
	/**@return l'email del CTT*/
	public String getEmail() {
		return e_mail;
	}
	

	public CTTPosition getPosizione() {
		return posizione;
	}
	
	

	
}