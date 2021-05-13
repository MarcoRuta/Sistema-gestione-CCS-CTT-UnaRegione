package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.util.Objects;
import java.lang.Math;

public class CTT{
	
	private final Integer numero;
	private String denominazione;
	private final String provincia;
	private final String città;
	private final String indirizzo;
	private final String telefono;
	private final String e_mail;
	private final double latitudine;
	private final double longitudine;
	

	/**Metodo costruttore di CTT, accetta tutti i parametri
	 * 
	 * @param numero il numero identificativo del CTT
	 * @param denominazione  il nome del CTT
	 * @param provincia la provincia in cui è situato il CTT
	 * @param città la città in cui è situato il CTT
	 * @param indirizzo l'indirizzo del CTT
	 * @param telefono il numero di telefono del CTT
	 * @param e_mail l'email ufficiale del CTT
	 * @param latitudine la latitudine del CTT
	 * @param longitudine la longitudine del CTT
	 * 
	 * 
	 * */
	public CTT(Integer numero, String denominazione, String provincia, String città, String telefono, String indirizzo, String e_mail, double latitudine, double longitudine) {
		assert numero != null: "Il numero del CTT non può essere null";
		assert denominazione != null: "La denominazione non può essere null";
		assert provincia != null: "la provincia non può essere null";
		assert città != null: "la città non può essere null";
		assert indirizzo != null: "l'indirizzo non può essere null";
		assert telefono != null: "il telefono non può essere null";
		assert e_mail != null: "l'e_mail non può essere null";
		assert latitudine != 0: "la latitudine non può essere null";
		assert longitudine != 0: "la longitudine non può essere null";
		

		this.numero = numero;
		this.denominazione = denominazione;
		this.provincia = provincia;
		this.città = città;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.e_mail = e_mail;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
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
		ps.println("Provincia: "+ this.provincia);
		ps.println("Città: "+ this.città);
		ps.println("Indirizzo: "+ this.indirizzo);
		ps.println("Telefono: "+ this.telefono);
		ps.println("e-mail: "+ this.e_mail);
		ps.println("latitudine: "+ this.latitudine);
		ps.println("longitutidine: "+ this.longitudine);
		
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
				", provincia=" + provincia +
				", città=" + città +
				", indirizzo=" + indirizzo +
				", telefono=" + telefono +
				", e_mail=" + e_mail +
				", latitudine=" + latitudine +
				", longitudine=" + longitudine +
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
		double distanzalat = Math.toRadians(c.getLatitudine()-this.latitudine);
	    double distanzalon = Math.toRadians(c.getLongitudine() - this.longitudine);
	    
	    Double x = Math.sin(distanzalat / 2) * Math.sin(distanzalat / 2) + Math.cos(Math.toRadians(this.latitudine)) * Math.cos(Math.toRadians(c.latitudine)) * Math.sin(distanzalon / 2) * Math.sin(distanzalon / 2);
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
	
	/**@return la provincia del CTT*/
	public String getProvincia() {
		return provincia;
	}
	
	/**@return la città del CTT*/
	public String getCittà() {
		return città;
	}
	
	/**@return l'indirizzo del CTT*/
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**@return il numero di telefono del CTT*/
	public String getTelefono() {
		return telefono;
	}
	
	/**@return l'email del CTT*/
	public String getEmail() {
		return e_mail;
	}
	
	/**@return la latitudine del CTT*/
	public double getLatitudine() {
		return latitudine;
	}
	
	/**@return la longitudine del CTT*/
	public double getLongitudine() {
		return longitudine;
	}
	
	

	
}