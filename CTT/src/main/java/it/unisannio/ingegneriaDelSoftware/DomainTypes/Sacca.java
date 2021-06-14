package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;

public class Sacca {
	
	/**Seriale, codice identificativo univoco di una Sacca di sangue */
	private final Seriale seriale;
	/**Gruppo sanguigno della Sacca di sangue*/
	private final GruppoSanguigno gruppo;
	/**Data di produzione della Sacca di sangue*/
	private final LocalDate dataProduzione;
	/**Data di scadenza della Sacca di sangue*/
	private final LocalDate dataScadenza;
	/**Indicatore dello stato di una Sacca di sangue*/
	private boolean prenotato;


	/**Metodo costruttore di Sacca senza conoscere il Seriale
	 * @param gs il gruppo sanguigno della Sacca
	 * @param dataProduzione la data di produzione della Sacca
	 * @param dataScadenza la data di scadenza della Sacca
	 * @throws IllegalArgumentException se la data di scadenza è precedente a quella di produzione
	 */
	public Sacca(GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza) throws IllegalArgumentException{
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";
		if (dataScadenza.isBefore(dataProduzione))throw new IllegalArgumentException( "La data di produzione non può essere precedente a quella di scadenza");
		if (dataProduzione.isAfter(LocalDate.now())) throw new IllegalArgumentException("Non puoi inserire una sacca non ancora prodotta");

		this.seriale = new Seriale();
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = false;
	}

	/**Metodo costruttore di Sacca
	 * @param gs il gruppo sanguigno della Sacca
	 * @param dataProduzione la data di produzione della Sacca
	 * @param dataScadenza la data di scadenza della Sacca
	 * @param prenotato indica se la Sacca è stata prenotata o meno, di default è false
	 * @param ser il seriale della Sacca
	 * @throws IllegalArgumentException se la data di scadenza è precedente a quella di produzione
	 */
	public Sacca(Seriale ser, GruppoSanguigno gs, LocalDate dataProduzione, LocalDate dataScadenza, boolean prenotato) throws IllegalArgumentException{
		assert gs != null: "Il gruppo sanguigno non può essere null";
		assert dataProduzione != null: "La data di produzione non può essere null";
		assert dataScadenza != null: "la data di scadenza non può essere null";
		assert ser!= null: "Il seriale non può essere null";
		if (dataScadenza.isBefore(dataProduzione))throw new IllegalArgumentException( "La data di produzione non può essere precedente a quella di scadenza");
		if (dataProduzione.isAfter(LocalDate.now())) throw new IllegalArgumentException("Non puoi inserire una sacca non ancora prodotta");

		this.seriale = ser;
		this.gruppo = gs;
		this.dataProduzione = dataProduzione;
		this.dataScadenza = dataScadenza;
		this.prenotato = prenotato;
	}


	/**Restituisce il Seriale della Sacca
	 * @return il seriale della Sacca
	 */
	public Seriale getSeriale() {
		return this.seriale;
	}

	
	/**Restituisce il gruppo sanguigno della Sacca
	 * @return il gruppo sanguigno della Sacca
	 */
	public GruppoSanguigno getGruppoSanguigno() {
		return this.gruppo;
	}

	
	/**Restituisce la data di scadenza della Sacca
	 * @return la data di scadenza della Sacca
	 */
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	
	/**Restituisce la data di produzione della Sacca
	 * @return la data di produzione della Sacca
	 */
	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	
	/**Controlla se la Sacca è prenotata
	 * @return true se la sacca è prenotata, false altrimenti
	 */
	public boolean isPrenotato() {
		return prenotato;
	}

	
	/**Modifica lo stato di una Sacca
	 */
	public void setPrenotato() {
		this.prenotato = true;
	}


	/**Stampa le informazioni di una Sacca 
	 * @param ps stream di output su cui stampare i dati della Sacca 
	 */
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Seriale: "+this.seriale.getSeriale());
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