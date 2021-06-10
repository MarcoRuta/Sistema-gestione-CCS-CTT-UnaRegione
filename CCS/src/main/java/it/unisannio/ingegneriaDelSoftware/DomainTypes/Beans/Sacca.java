package it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans;


import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;

import java.time.LocalDate;
import java.util.Objects;

public class Sacca {
	
	private Seriale seriale;
	private GruppoSanguigno gruppo;
	private LocalDate dataProduzione;
	private LocalDate dataScadenza;
	private boolean prenotato;


	/**Metodo costruttore di SaccaBean
	 * @param ser il seriale della Sacca
	 * @param gs il gruppo sanguigno della Sacca
	 * @param dataProduzione  la data di produzione della Sacca
	 * @param dataScadenza la data di scadenza della Sacca
	 * @param prenotato indica se la Sacca è stata prenotata o meno, di default è false
	 * @throws IllegalArgumentException se la data di scadenza è precedente a quella di produzione, se la sacca è già scaduta, o se la sacca non èancora stata prodotta
	 */
	public Sacca(Seriale ser, GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza, boolean prenotato) throws IllegalArgumentException{
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";
		assert ser!= null: "Il seriale non puo essere null";
		if (dataScadenza.isBefore(dataProduzione))throw new IllegalArgumentException( "La data di produzione non può essere precedente a quella di scadenza");
		if (dataProduzione.isAfter(LocalDate.now())) throw new IllegalArgumentException("Non puoi inserire una sacca non ancora prodotta");

		this.seriale = ser;
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}

	public Sacca() {
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
	
	
	/**Restituisce il seriale della Sacca
	 * @return seriale
	 */
	public Seriale getSeriale() {
		return seriale;
	}

	/**Modifica il Seriale della Sacca
	 * @param seriale
	 */
	public void setSeriale(Seriale seriale) {
		this.seriale = seriale;
	}

	
	/**Restituisce il GruppoSanguigno della Sacca
	 * @return gruppo
	 */
	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}

	/**Modifica il GruppoSanguigno della Sacca
	 * @param gruppo
	 */
	public void setGruppoSanguigno(GruppoSanguigno gruppo) {
		this.gruppo = gruppo;
	}

	
	/**Restituisce la DataProduzione della Sacca
	 * @return dataProduzione
	 */
	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	
	/**Modifica la data di produzione della Sacca
	 * @param dataProduzione
	 */
	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}

	
	/**Restituisce la data di scadenza della Sacca
	 * @return dataScadenza
	 */
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	
	/**Modifica la data di scadenza della Sacca
	 * @param dataScadenza
	 */
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**Restituisce lo stato della Sacca
	 * @return prenotato
	 */
	public boolean isPrenotato() {
		return prenotato;
	}

	
	/**Modifica lo stato della Sacca da false a true
	 * @param prenotato
	 */
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}	
}