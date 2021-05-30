package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;

public class Sacca {
	
	

	private  Seriale seriale;
	private  GruppoSanguigno gruppo;
	private  LocalDate dataProduzione;
	private  LocalDate dataScadenza;
	private boolean prenotato;


	/**@param gs il gruppo sanguigno della sacca
	 * @param dataProduzione  la data di produzione della sacca
	 * @param dataScadenza  la data di scadenza della sacca
	 * @param prenotato indica se la sacca è stata prenotata o meno, di default è false
	 * @param ser  il seriale della sacca
	 * @throws IllegalArgumentException se la data di scadenza è precedente a quella di produzione
	 * */
	public Sacca(Seriale ser, GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza, boolean prenotato) throws IllegalArgumentException{
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

	public GruppoSanguigno getGruppo() {
		return gruppo;
	}



	public void setGruppoSanguigno(GruppoSanguigno gruppo) {
		this.gruppo = gruppo;
	}



	public void setSeriale(Seriale seriale) {
		this.seriale = seriale;
	}



	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}



	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}



	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}

	/**@return  il seriale della sacca*/
	public Seriale getSeriale() {
		return this.seriale;
	}

	/**@return il gruppo sanguigno della sacca*/
	public GruppoSanguigno getGruppoSanguigno() {
		return this.gruppo;
	}

	/**@return la data di scadenza della sacca*/
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	/**@return la data di produzione della sacca*/
	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	/**@return true se la sacca è prenotata, false altrimenti*/
	public boolean isPrenotato() {
		return prenotato;
	}

	/**@return cambio lo stato di una sacca*/
	public void setPrenotato() {
		this.prenotato = true;
	}


	/**@param ps  stream di output su cui stampare i dati della sacca*/
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Seriale: "+this.seriale);
		ps.println("Gruppo sanguigno: "+this.gruppo);
		ps.println("Data produzione: "+this.dataProduzione);
		ps.println("Data di scadenza: "+this.dataScadenza);
		ps.println("Prenotata: "+isPrenotato());
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sacca sacca = (Sacca) o;
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
}