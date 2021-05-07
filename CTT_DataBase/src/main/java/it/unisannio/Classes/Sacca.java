package it.unisannio.Classes;

import java.io.PrintStream;
import java.util.Date;

import it.unisannio.Constants.Constants;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.Seriale;

public class Sacca {
	
	private final Seriale seriale;
	private final GruppoSanguigno gruppo;
	private final Date dataProduzione;
	private final Date dataScadenza;
	private boolean prenotato;

	public Sacca(GruppoSanguigno gs, Date dataProduzione, Date dataScadenza, boolean prenotato) {
		assert gs != null && dataProduzione != null && dataScadenza != null;
		this.seriale = new Seriale();
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}	
	
	public Sacca(Seriale ser, GruppoSanguigno gs, Date dataProduzione, Date dataScadenza, boolean prenotato) {
		assert ser!= null && gs != null && dataProduzione != null && dataScadenza != null;
		this.seriale = ser;
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}
	
	public Seriale getSeriale() {
		return this.seriale;
	}
	
	public GruppoSanguigno getGruppoSanguigno() {
		return this.gruppo;
	}
	
	public Date getDataScadenza() {
		return dataScadenza;
	}
	
	public String getDataScadenzaS() {
		return Constants.sdf.format(dataScadenza);
	}

	public Date getDataProduzione() {
		return dataProduzione;
	}

	public String getDataProduzioneS() {
		return Constants.sdf.format(dataProduzione);
	}	
	
	public boolean isPrenotato() {
		return prenotato;
	}
	
	public void setPrenotato() {
		this.prenotato = true;
	}
	
	public String toString() {
		return seriale + " - Gruppo sanguigno: " + gruppo + " - Data di scadenza: " + dataScadenza + " - Data di produzione: " + dataProduzione + " - Prenotata: " + prenotato; 
	}
	
	public String toStringPerEtichetta() {
		return seriale + " - Gruppo sanguigno: " + gruppo + " - Data di scadenza: " + dataScadenza + " - Data di produzione: " + dataProduzione; 
	}
	
	
	public boolean equals(Object o) {
		return ((Sacca) o).getSeriale().toString().equals(this.getSeriale().toString());
	}
	
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Seriale: "+this.seriale);
		ps.println("Gruppo sanguigno: "+this.gruppo);
		ps.println("Data produzione: "+getDataProduzioneS());
		ps.println("Data di scadenza: "+getDataScadenzaS());
		ps.println("Prenotata: "+isPrenotato());
	}
}