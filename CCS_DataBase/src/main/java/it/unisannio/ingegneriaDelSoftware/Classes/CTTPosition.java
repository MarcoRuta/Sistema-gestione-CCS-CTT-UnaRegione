package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class CTTPosition {
		
	@BsonProperty(useDiscriminator = true)
	private final String provincia;
	@BsonProperty(useDiscriminator = true)
	private final String citta;
	@BsonProperty(useDiscriminator = true)
	private final String indirizzo;
	@BsonProperty(useDiscriminator = true)
	private final double latitudine;
	@BsonProperty(useDiscriminator = true)
	private final double longitudine;

	/**
	 * Metodo costruttore di CTTPosition
	 * 
	 * @param provincia la provincia di appartenenza del CTT
	 * @param citta  la citta di appartenenza del CTT
	 * @param indirizzo l'indirizzo di appartenenza del CTT
	 * @param latitudine la latitudine del CTT
	 * @param longitudine la longitudine del CTT
	 * 
	 * */
	@BsonCreator
	public CTTPosition(@BsonProperty("provincia")String provincia,
						@BsonProperty("citta")String citta,
						@BsonProperty("indirizzo")String indirizzo,
						@BsonProperty("latitudine")double latitudine,
						@BsonProperty("longitudine")double longitudine) {
		assert provincia != null: "la provincia non può essere null";
		assert citta != null: "la citta non può essere null";
		assert indirizzo != null: "l'indirizzo non può essere null";
		assert latitudine != 0: "la latitudine non può essere null";
		assert longitudine != 0: "la longitudine non può essere null";
	
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
				", citta=" + citta +
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
		ps.println("Citta: "+ this.citta);
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
