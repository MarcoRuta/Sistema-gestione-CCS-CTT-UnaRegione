package it.unisannio.ingegneriaDelSoftware.Classes.Beans;


import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;

import java.time.LocalDate;
import java.util.Objects;

public class SaccaBean {
	
	

	private SerialeBean seriale;
	private GruppoSanguigno gruppo;
	private LocalDate dataProduzione;
	private LocalDate dataScadenza;
	private boolean prenotato;



	/**@param gs il gruppo sanguigno della sacca
	 * @param dataProduzione  la data di produzione della sacca
	 * @param dataScadenza  la data di scadenza della sacca
	 * @param prenotato indica se la sacca è stata prenotata o meno, di default è false
	 * @param ser  il seriale della sacca
	 * @throws IllegalArgumentException se la data di scadenza è precedente a quella di produzione
	 * */
	public SaccaBean(SerialeBean ser, GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza, boolean prenotato) throws IllegalArgumentException{
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";
		assert ser!= null: "Il seriale non puo essere null";
		if (dataScadenza.isBefore(dataProduzione))throw new IllegalArgumentException( "La data di produzione non può essere precedente a quella di scadenza");
		if(dataScadenza.isBefore(LocalDate.now()))	throw new IllegalArgumentException("La sacca è gia scaduta");
		if (dataProduzione.isAfter(LocalDate.now())) throw new IllegalArgumentException("Non puoi inserire una sacca non ancora prodotta");

		this.seriale = ser;
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}





	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SaccaBean sacca = (SaccaBean) o;
		return seriale.equals(sacca.seriale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(seriale);
	}

	@Override
	public String toString() {
		return "Sacca{" +
				"seriale=" + seriale +
				", gruppo=" + gruppo +
				", dataProduzione=" + dataProduzione +
				", dataScadenza=" + dataScadenza +
				", prenotato=" + prenotato +
				'}';
	}
	
	public SerialeBean getSeriale() {
		return seriale;
	}

	public void setSeriale(SerialeBean seriale) {
		this.seriale = seriale;
	}

	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}

	public void setGruppoSanguigno(GruppoSanguigno gruppo) {
		this.gruppo = gruppo;
	}

	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}

	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public boolean isPrenotato() {
		return prenotato;
	}

	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}
	
}