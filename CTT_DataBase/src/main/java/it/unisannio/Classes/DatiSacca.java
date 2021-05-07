package it.unisannio.Classes;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;

import it.unisannio.Constants.Constants;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.Seriale;

public class DatiSacca {
	
	private final Seriale seriale;
	private final GruppoSanguigno gruppo;
	private Date dataArrivo; 		/*Unico attributo che non viene settato da RicercaSacca, ma da AggiuntaSaccaMagazzino (non so se è vera ancora sta cosa)*/	
	private Date dataAffidamento;
	private final String enteDonatore;
	private String enteRichiedente;

	public DatiSacca(Seriale ser, GruppoSanguigno gs, Date dataArrivo, Date dataAffidamento, String enteDonatore, String enteRichiedente) {
		assert ser != null && gs!= null && dataArrivo != null && dataAffidamento != null && enteDonatore != null && enteRichiedente != null;
		this.seriale = ser;
		this.gruppo = gs;
		this.dataArrivo = dataArrivo;
		this.dataAffidamento = dataAffidamento;
		this.enteDonatore = enteDonatore;
		this.enteRichiedente = enteRichiedente;
	}	
	
	public DatiSacca(Seriale ser, GruppoSanguigno gs, Date dataArrivo, String enteDonatore) {
		assert ser != null && gs!= null && dataArrivo != null && enteDonatore != null;
		this.seriale = ser;
		this.gruppo = gs;
		this.dataArrivo = dataArrivo;
		this.dataAffidamento = null;
		this.enteDonatore = enteDonatore;
		this.enteRichiedente = null;
	}
	
	public Seriale getSeriale() {
		return seriale;
	}

	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}
	
	public Date getDataArrivo() {
		return dataArrivo;
	}
	
	public String getDataArrivoS() {
		return Constants.sdf.format(dataArrivo);
	}
	
	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}
	
	public void setDataArrivoS(String dataArrivoS) throws ParseException {
		this.dataArrivo = Constants.sdf.parse(dataArrivoS);
	}
	
	public Date getDataAffidamento() {
		return dataAffidamento;
	}

	public void setDataAffidamento(Date dataAffidamento) {
		this.dataAffidamento = dataAffidamento;
	}
		
	public String getDataUscitaS() {
		return Constants.sdf.format(dataAffidamento);
	}
	
	public String getEnteDonatore() {
		return enteDonatore;
	}
	
	public String getEnteRichiedente() {
		return enteRichiedente;
	}

	public void setEnteRichiedente(String enteRichiedente) {
		this.enteRichiedente = enteRichiedente;
	}
	
	public String toString() {
		return seriale + " - Gruppo sanguigno: " + gruppo + " - dataArrivo: " + dataArrivo + " - dataAffidamento: " + dataAffidamento + " - enteDonatore: " + enteDonatore + " - enteRichiedente: " + enteRichiedente; 
	}
	
	public String toStringPerEtichetta() {
		return " Gruppo sanguigno: " + gruppo + " Data di arrivo: " + dataArrivo + " - Data di affidamento: " + dataAffidamento + " - Ente donatore: " + enteDonatore + " - Ente richiedente: " + enteRichiedente; 
	}
	
	public boolean equals(Object o) {
		return ((Sacca) o).getSeriale().toString().equals(this.getSeriale().toString());
	}
	
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Seriale: "+this.seriale);
		ps.println("Gruppo sanguigno: "+this.gruppo);
		ps.println("Data di ingresso: "+getDataArrivoS());
		ps.println("Data di uscita: "+getDataUscitaS());
		ps.println("Ente donatore: "+this.enteDonatore);
		ps.println("Ente richiedente: "+this.enteRichiedente);
	}
}