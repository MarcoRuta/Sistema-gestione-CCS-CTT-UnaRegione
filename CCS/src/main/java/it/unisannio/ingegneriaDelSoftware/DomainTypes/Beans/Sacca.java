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

	/**Metodo costruttore senza argomenti
	 */
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

	/**Restituisce il seriale della sacca
	 * @return seriale seriale della sacca
	 */
	public Seriale getSeriale() {
		return seriale;
	}

	/**Modifica il seriale della acca
	 * @param seriale Seriale della acca
	 */
	public void setSeriale(Seriale seriale) {
		this.seriale = seriale;
	}
	
	/**Restituisce il gruppo sanguigno della sacca
	 * @return gruppo Gruppo sanguigno della sacca
	 */
	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}

	/**Modifica il gruppo sanguigno della Sacca
	 * @param gruppo Gruppo sanguigno della sacca
	 */
	public void setGruppoSanguigno(GruppoSanguigno gruppo) {
		this.gruppo = gruppo;
	}

	/**Restituisce la data produzione della Sacca
	 * @return dataProduzione Data produzione della sacca
	 */
	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	/**Modifica la data di produzione della Sacca
	 * @param dataProduzione Data produzione della sacca
	 */
	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}

	/**Restituisce la data di scadenza della Sacca
	 * @return dataScadenza Data scadenza della sacca
	 */
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	/**Modifica la data di scadenza della Sacca
	 * @param dataScadenza Data scadenza della sacca
	 */
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**Restituisce lo stato della Sacca
	 * @return prenotato Lo stato della sacca(true o false)
	 */
	public boolean isPrenotato() {
		return prenotato;
	}

	/**Modifica lo stato della Sacca
	 * @param prenotato Lo stato della sacca(true o false)
	 */
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}	
}