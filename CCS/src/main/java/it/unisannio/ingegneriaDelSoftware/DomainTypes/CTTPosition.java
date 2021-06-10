package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.PrintStream;

public class CTTPosition {
		
	private final String provincia;
	private final String citta;
	private final String indirizzo;
	private final double latitudine;
	private final double longitudine;

	/**Metodo costruttore di CTTPosition
	 * @param provincia Provincia di appartenenza del CTT
	 * @param citta Città di appartenenza del CTT
	 * @param indirizzo Indirizzo di appartenenza del CTT
	 * @param latitudine Latitudine del CTT
	 * @param longitudine Longitudine del CTT
	 */
	public CTTPosition(String provincia, String citta, String indirizzo, double latitudine, double longitudine) {
		assert provincia != null: "la provincia non può essere null";
		assert citta != null: "la città non può essere null";
		assert indirizzo != null: "l'indirizzo non può essere null";
		assert latitudine != 0: "la latitudine non può essere null";
		assert longitudine != 0: "la longitudine non può essere null";

		if (provincia.length()!= 2) throw new IllegalArgumentException("La provincia deve essere di due caratteri");
		if (! (provincia.toUpperCase().matches("^[A-Z]*$"))) throw new IllegalArgumentException("La provincia deve contenere solo lettere");
		if (latitudine < -90 || latitudine > 90 || longitudine < -180 || longitudine > 180) throw new IllegalArgumentException("la longitudine o la latitudine è errata");

		this.provincia = provincia.toUpperCase();
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.latitudine = latitudine;
		this.longitudine = longitudine;	
	}

	@Override
	public String toString() {
		return "CTTPosition{" +
				", provincia=" + provincia +
				", citta=" + citta +
				", indirizzo=" + indirizzo +
				", latitudine=" + latitudine +
				", longitudine=" + longitudine +
				'}';
	}

	/**Stampa le informazioni della posizione del CTT
	 * @param ps stream di output su cui stampare i dati della posizione del CTT
	 */
	public void print(PrintStream ps) {
		ps.println("Provincia: "+ this.provincia);
		ps.println("Citta: "+ this.citta);
		ps.println("Indirizzo: "+ this.indirizzo);
		ps.println("latitudine: "+ this.latitudine);
		ps.println("longitutidine: "+ this.longitudine);
		
	}
	
	/**Restituisce la provincia del CTT
	 * @return provincia La provincia del CTT
	 */
	public String getProvincia() {
		return provincia;
	}
	
	/**Restituisce la città del CTT
	 * @return città La città del CTT
	 */
	public String getCitta() {
		return citta;
	}
	
	/**Restituisce l'indirizzo del CTT
	 * @return indirizzo Indirizzo del CTT
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**Restituisce la latitudine del CTT
	 * @return latitudine Latitudine del CTT
	 */
	public double getLatitudine() {
		return latitudine;
	}
	
	/**Restituisce la longitudine del CTT
	 * @return longitudine Longitudine del CTT
	 */
	public double getLongitudine() {
		return longitudine;
	}
}