package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;

public class CTTPosition {
		
	private final String provincia;
	private final String città;
	private final String indirizzo;
	private final double latitudine;
	private final double longitudine;

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

	/**metodo di stampa per la posizione del CTT
	 * 
	 * @param ps  stream di output su cui stampare i dati della sacca
	 * 
	 */
	
	public void print(PrintStream ps) {
		ps.println("Provincia: "+ this.provincia);
		ps.println("Città: "+ this.città);
		ps.println("Indirizzo: "+ this.indirizzo);
		ps.println("latitudine: "+ this.latitudine);
		ps.println("longitutidine: "+ this.longitudine);
		
	}
	
	public String getProvincia() {
		return provincia;
	}
	public String getCittà() {
		return città;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public double getLatitudine() {
		return latitudine;
	}
	public double getLongitudine() {
		return longitudine;
	}
}
