package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;

public class Sacca {
	
	private final Seriale seriale;
	private final GruppoSanguigno gruppo;
	private final LocalDate dataProduzione;
	private final LocalDate dataScadenza;
	private boolean prenotato;

	/**@param gs il gruppo sanguigno della sacca
	 * @param dataProduzione  la data di produzione della sacca
	 * @param dataScadenza  la data di scadenza della sacca
	 * @param prenotato indica se la sacca è stata prenotata o meno, di default è false*/
	public Sacca(GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza, boolean prenotato) {
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";

		this.seriale = new Seriale();
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}

	/**@param gs il gruppo sanguigno della sacca
	 * @param dataProduzione  la data di produzione della sacca
	 * @param dataScadenza  la data di scadenza della sacca
	 */
	public Sacca(GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza) {
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";

		this.seriale = new Seriale();
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = false;
	}

	/**@param gs il gruppo sanguigno della sacca
	 * @param dataProduzione  la data di produzione della sacca
	 * @param dataScadenza  la data di scadenza della sacca
	 * @param prenotato indica se la sacca è stata prenotata o meno, di default è false
	 * @param ser  il seriale della sacca*/
	public Sacca(Seriale ser, GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza, boolean prenotato) {
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";
		assert ser!= null: "Il seriale non puo essere null";

		this.seriale = ser;
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}

	/**@param gs il gruppo sanguigno della sacca
	 * @param dataProduzione  la data di produzione della sacca
	 * @param dataScadenza  la data di scadenza della sacca
	 * @param ser  il seriale della sacca*/
	public Sacca(Seriale ser, GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza) {
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";
		assert ser!= null: "Il seriale non puo essere null";

		this.seriale = ser;
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = false;
	}

	public Sacca() {
		this.seriale = null;
		this.gruppo = null;
		this.dataProduzione = null;
		this.dataScadenza = null;
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
		ps.println("Seriale: "+this.seriale.getSeriale());
		ps.println("Gruppo sanguigno: "+this.gruppo);
		ps.println("Data produzione: "+this.dataProduzione);
		ps.println("Data di scadenza: "+this.dataScadenza);
		ps.println("Prenotata: "+isPrenotato());
	}

	/**@return etichetta  con i dati della sacca*/
	public String getEtichettaSacca() {
		return "Seriale: "+this.seriale+ "\n"
			+"Gruppo sanguigno: "+this.gruppo+ "\n"
			+"Data produzione: "+this.dataProduzione+"\n"
			+"Data di scadenza: "+this.dataScadenza;
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