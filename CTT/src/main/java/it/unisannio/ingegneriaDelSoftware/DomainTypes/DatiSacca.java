package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class DatiSacca {
	
	/**Seriale della Sacca, è univoco non puo essere modificato una volta creata la Sacca*/
	private final Seriale seriale;	
	/** Gruppo sanguigno della Sacca, non puo essere modificato una volta creata la Sacca*/
	private final GruppoSanguigno gruppo;	
	/** Data di arrivo della Sacca, viene aggiunta quando la Sacca è inserita nel magazzino, non puo essere modificata*/
	private final LocalDate dataArrivo;	
	/** Data in cui la Sacca viene affidata ad un ente esterno*/
	private Optional<LocalDate> dataAffidamento;	
	/**Ente da cui proviene la Sacca, nel momento in cui la Sacca è creata lo si conosce e non puo essere modificato*/
	private final String enteDonatore;	
	/**Colui che richiede la Sacca, viene aggiunto nel momento in cui la Sacca è affidata*/
	private Optional<String> enteRichiedente;	
	/**L'indirizzo di colui che richiede la Sacca, viene aggiunto nel momento in cui la Sacca è affidata*/
	private Optional<String> indirizzoEnte;
	

	/** Metodo costruttore invocato quando la Sacca è inserita nel Magazzino
	 * @param dataArrivo Data in cui la Sacca è arrivata in magazzino, non può essere temporalmente dopo la data di affidamento
	 * @param enteDonatore Ente da cui proviene la Sacca
	 * @param seriale Seriale della Sacca
	 * @param gruppoSanguigno Gruppo sanguigno della Sacca
	 * @param dataAffidamento Data nella quale viene affidata la Sacca. Se la Sacca non è stata affidata può essere settato a null. Non può essere inferiore alla data di arrivo
	 * @param enteRichiedente Ente che ha richiesto la Sacca. Se la Sacca non è stata affidata può essere settato a null
	 * @throws IllegalArgumentException se la data di affidamento è precedente a quella di arrivo*/
	public DatiSacca(Seriale seriale, GruppoSanguigno gruppoSanguigno, LocalDate dataArrivo, LocalDate dataAffidamento, String enteDonatore, String enteRichiedente, String indirizzoEnte) throws AssertionError,IllegalArgumentException{

		assert seriale != null: "Il seriale non può essere nullo";
		assert gruppoSanguigno!= null: "Il gruppo sanguigno non può essere nullo";
		assert dataArrivo != null: "La data di arrivo non può essere nulla";
		assert enteDonatore != null: "L'ente donatore non può essere nullo";
		if(dataAffidamento != null && dataAffidamento.isBefore(dataArrivo) && !dataAffidamento.isEqual(dataArrivo))
			throw new IllegalArgumentException("La data di affidamento non può essere precedente a quella di arrivo");
		
		this.seriale = seriale;
		this.gruppo = gruppoSanguigno;
		this.dataArrivo = dataArrivo;
		this.dataAffidamento = Optional.ofNullable(dataAffidamento);
		this.enteDonatore = enteDonatore;
		this.enteRichiedente = Optional.ofNullable(enteRichiedente);
		this.indirizzoEnte = Optional.ofNullable(indirizzoEnte);
	}

	
	/**Restituisce il seriale della Sacca
	 * @return seriale il seriale della Sacca*/
	public Seriale getSeriale() {
		return seriale;
	}

	
	/**Restituisce il gruppo sanguigno della Sacca
	 * @return il gruppo sanguigno della Sacca*/
	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}

	
	/**Restituisce la data di arrivo della Sacca
	 * @return la data di arrivo della Sacca*/
	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	
	/**Restituisce la data di affidamento se presente, altrimenti restituisce una data con giorno, mese, anno settati a 0
	 * @return dataAffidamento data di affidamento se presente, altrimenti restituisce una data con giorno, mese, anno settati a 0*/
	public Optional<LocalDate> getDataAffidamento() {
		return dataAffidamento;
	}

	
	/**Restituisce l'ente donatore della Sacca
	 * @return enteDonatore l'ente donatore della Sacca*/
	public String getEnteDonatore() {
		return enteDonatore;
	}

	
	/**Restituisce l'ente richiedente se presente, altrimenti restituisce una stringa vuota
	 * @return L'ente richiedente se presente, altrimenti restituisce una stringa vuota*/
	public String getEnteRichiedente() {
		return enteRichiedente.isPresent()?enteRichiedente.get():"";
	}
	
	
	/**Restituisce l'indirizzo dell'ente richiedente se presente, altrimenti restituisce una stringa vuota
	 * @return l'indirizzo dell'ente o una stringa vuota
	 */
	public String getIndirizzoEnte() {
		return indirizzoEnte.isPresent()?indirizzoEnte.get():"";
	}
	
	
	/**Imposta la data di affidamento
	 * @param dataAffidamento Data in cui è stata affidata la Sacca, non può essere null e non può essere precedente alla data di arrivo
	 * @throws IllegalArgumentException Quando la data di affidamento precede alla data di arrivo
	 */
	public void setDataAffidamento(LocalDate dataAffidamento) throws  IllegalArgumentException, AssertionError{
		assert dataAffidamento != null: "La data di affidamento non può essere null";
		if (dataAffidamento.isBefore(this.dataArrivo)) throw  new IllegalArgumentException("La data di arrivo non può essere inferiore alla data di affidamento");
		this.dataAffidamento = Optional.of(dataAffidamento);
	}
	
	
	/**Imposta l'ente richiedente
	 * @param enteRichiedente l'ente che ha richiesto la Sacca. Non può essere null
	 */
	public void setEnteRichiedente(String enteRichiedente)throws AssertionError{
		assert enteRichiedente != null: "L'ente richiedente non può essere null";
		this.enteRichiedente = Optional.of(enteRichiedente);
	}
	

	/**Modifica l'indirizzo dell'ente
	 * @param indirizzoEnte l'indirizzo dell'ente che ha richiesto la Sacca. Non può essere null.
	 */
	public void setIndirizzoEnte(String indirizzoEnte) throws AssertionError{
		assert indirizzoEnte != null: "L'indirizzo dell'ente non può essere null";
		this.indirizzoEnte = Optional.of(indirizzoEnte);
	}
	
	
	@Override
	public String toString() {
		return "DatiSacca{" +
				"seriale=" + seriale +
				", gruppo=" + gruppo +
				", dataArrivo=" + dataArrivo +
				", dataAffidamento=" + dataAffidamento +
				", enteDonatore='" + enteDonatore + '\'' +
				", enteRichiedente=" + enteRichiedente +
				", indirizzoEnte=" + indirizzoEnte +
				'}';
	}

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DatiSacca datiSacca = (DatiSacca) o;
		return seriale.equals(datiSacca.seriale);
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(seriale);
	}

	
	/**Stampa le informazioni di un DatiSacca 
	 * @param ps stream di output su cui stampare i DatiSacca 
	 */
	public void print(PrintStream ps) {
		ps.println("\n##################################");
		ps.println("Seriale: "+this.seriale.getSeriale());
		ps.println("Gruppo sanguigno: "+this.gruppo);
		ps.println("Data di ingresso: "+this.dataArrivo);
		ps.println("Data di affidamento: "+ (this.dataAffidamento.isPresent()?this.dataAffidamento.get():""));
		ps.println("Ente donatore: "+this.enteDonatore);
		ps.println("Ente richiedente: "+(this.enteRichiedente.isPresent()?this.enteRichiedente.get():""));
		ps.println("Indirizzo Ente: "+(this.indirizzoEnte.isPresent()?this.indirizzoEnte.get():""));
	}

}