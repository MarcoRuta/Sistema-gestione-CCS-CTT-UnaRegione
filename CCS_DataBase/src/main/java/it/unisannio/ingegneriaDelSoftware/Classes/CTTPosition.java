package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;

public class CTTPosition {
		
	private final String provincia;
	private final String città;
	private final String indirizzo;
	private final double latitudine;
	private final double longitudine;

	/**
	 * Metodo costruttore di CTTPosition
	 * 
	 * @param provincia la provincia di appartenenza del CTT
	 * @param città  la città di appartenenza del CTT
	 * @param indirizzo l'indirizzo di appartenenza del CTT
	 * @param latitudine la latitudine del CTT
	 * @param longitudine la longitudine del CTT
	 * 
	 * */
	public CTTPosition(String provincia, String città, String indirizzo, double latitudine, double longitudine) {
		assert provincia != null: "la provincia non può essere null";
		assert città != null: "la città non può essere null";
		assert indirizzo != null: "l'indirizzo non può essere null";
		assert latitudine != 0: "la latitudine non può essere null";
		assert longitudine != 0: "la longitudine non può essere null";
	
		this.provincia = provincia;
		this.città = città;
		this.indirizzo = indirizzo;
		this.latitudine = latitudine;
		this.longitudine = longitudine;	
	}
	
	/**
	 * Metodo che concatena in un'unica stringa le informazioni della posizione del CTT
	 *  * 
	 * @return La stringa concatenata
	 * 
	 */
	@Override
	public String toString() {
		return "CTTPosition{" +
				", provincia=" + provincia +
				", città=" + città +
				", indirizzo=" + indirizzo +
				", latitudine=" + latitudine +
				", longitudine=" + longitudine +
				'}';
	}

	/**
	 * Metodo che stampa le informazioni della posizione del CTT
	 * 
	 * @param ps  stream di output su cui stampare i dati della posizione del CTT
	 * 
	 */
	public void print(PrintStream ps) {
		ps.println("Provincia: "+ this.provincia);
		ps.println("Città: "+ this.città);
		ps.println("Indirizzo: "+ this.indirizzo);
		ps.println("latitudine: "+ this.latitudine);
		ps.println("longitutidine: "+ this.longitudine);
		
	}
	
	/**
	 * Metodo che restituisce la provincia del CTT
	 * 
	 * @return provincia
	 *
	 */
	public String getProvincia() {
		return provincia;
	}
	
	/**
	 * Metodo che restituisce la città del CTT
	 * 
	 * @return città
	 *
	 */
	public String getCittà() {
		return città;
	}
	
	/**
	 * Metodo che restituisce l'indirizzo del CTT
	 * 
	 * @return  indirizzo
	 *
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * Metodo che restituisce la latitudine del CTT
	 * 
	 * @return latitudine
	 *
	 */
	public double getLatitudine() {
		return latitudine;
	}
	
	/**
	 * Metodo che restituisce la longitudine del CTT
	 * 
	 * @return longitudine
	 *
	 */
	public double getLongitudine() {
		return longitudine;
	}
}
