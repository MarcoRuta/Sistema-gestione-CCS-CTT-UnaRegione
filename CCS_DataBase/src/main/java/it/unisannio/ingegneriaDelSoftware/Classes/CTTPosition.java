package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;

public class CTTPosition {
		
	private final String provincia;
	private final String citta;
	private final String indirizzo;
	private final double latitudine;
	private final double longitudine;

	/**
	 * Metodo costruttore di CTTPosition
	 * 
	 * @param provincia la provincia di appartenenza del CTT
	 * @param citta la citta di appartenenza del CTT
	 * @param indirizzo l'indirizzo di appartenenza del CTT
	 * @param latitudine la latitudine del CTT
	 * @param longitudine la longitudine del CTT
	 * 
	 * */
	public CTTPosition(String provincia, String citta, String indirizzo, double latitudine, double longitudine) {
		assert provincia != null: "la provincia non puo essere null";
		assert citta != null: "la citta non puo essere null";
		assert indirizzo != null: "l'indirizzo non puo essere null";
		assert latitudine != 0: "la latitudine non puo essere null";
		assert longitudine != 0: "la longitudine non puo essere null";
	
		this.provincia = provincia;
		this.citta = citta;
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
				", città=" + citta +
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
		ps.println("Città: "+ this.citta);
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
	 * Metodo che restituisce la citta del CTT
	 * 
	 * @return citta
	 *
	 */
	public String getCitta() {
		return citta;
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
