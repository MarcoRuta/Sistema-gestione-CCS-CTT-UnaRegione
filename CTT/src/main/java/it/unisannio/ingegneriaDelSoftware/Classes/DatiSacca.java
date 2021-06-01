package it.unisannio.ingegneriaDelSoftware.Classes;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class DatiSacca {

	private final Seriale seriale;
	private final GruppoSanguigno gruppo;
	private final LocalDate dataArrivo;
	private Optional<LocalDate> dataAffidamento;
	private final String enteDonatore;
	private Optional<String> enteRichiedente;
	private Optional<String> indirizzoEnte;
	
	/** 
	 * Metodo costruttore invocato quando la sacca è inserita nel Magazzino
	 * @param dataArrivo  data in cui la sacca è arrivata in magazzino, non può essere temporalmente dopo la data di affidamento
	 * @param enteDonatore  ente da cui proviene la sacca
	 * @param seriale seriale della sacca
	 * @param gruppoSanguigno gruppo sanguigno della sacca
	 * @param dataAffidamento  data nella quale viene affidata la sacca. Se la sacca non è stata affidata può essere settato a null. Non puo essere inferiore alla data di arrivo
	 * @param enteRichiedente  ente che ha richiesto la saccca. Se la sacca non è stata affidata può essere settato a null
	 * @throws IllegalArgumentException se la data di affidamento è precedente a quella di arrivo
	 */
	public DatiSacca(Seriale seriale, GruppoSanguigno gruppoSanguigno, LocalDate dataArrivo, LocalDate dataAffidamento, String enteDonatore, String enteRichiedente, String indirizzoEnte) throws AssertionError,IllegalArgumentException{
		assert seriale != null: "Il seriale non può essere nullo";
		assert gruppoSanguigno!= null: "Il gruppo sanguigno non puo essere nullo";
		assert dataArrivo != null: "La data di arrivo non può essere nulla";
		assert enteDonatore != null: "L'ente donatore non può essere nullo";
		if(dataAffidamento != null && dataAffidamento.isBefore(dataArrivo))
		throw new IllegalArgumentException("La data di affidamento non puo essere precedente a quella di arrivo");
		this.seriale = seriale;
		this.gruppo = gruppoSanguigno;
		this.dataArrivo = dataArrivo;
		this.dataAffidamento = Optional.ofNullable(dataAffidamento); //se null, nel db compare lo stesso una data ma è 01-01-0001
		this.enteDonatore = enteDonatore;
		this.enteRichiedente = Optional.ofNullable(enteRichiedente);
		this.indirizzoEnte = Optional.ofNullable(indirizzoEnte);
	}

	/**
	 * Metodo che ritorna il seriale della sacca
	 *@return  il seriale della sacca
	 */
	public Seriale getSeriale() {
		return seriale;
	}

	/**
	 * Metodo che ritorna il gruppo sanguigno della sacca
	 *@return  il gruppo sanguigno della sacca
	 */
	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}

	/**
	 * Metodo che ritorna la data di arrivo della sacca
	 *@return  la data di arrivo della sacca
	 */
	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	/**
	 * Metodo che ritorna la data di affidamento se presente
	 *@return  restituisce la data di affidamento se presente, altrimenti restituisce una data con giorno,mese,anno settati a 0
	 */
	public Optional<LocalDate> getDataAffidamento() {
		return dataAffidamento;
	}

	/**
	 * Metodo che ritorna l'ente donatore della sacca
	 *@return  l'ente donatore della sacca
	 */
	public String getEnteDonatore() {
		return enteDonatore;
	}

	/**
	 * Metodo che ritorna l'ente richiedente se presente
	 *@return l'ente richiedente se presente, altrimenti restituisce una stringa vuota
	 */
	public String getEnteRichiedente() {
		return enteRichiedente.isPresent()?enteRichiedente.get():"";
	}
	
	/**
	 * Metodo che ritorna l'indirizzo dell'ente richiedente se presente
	 *@return  restituisce l'indirizzo dell'ente richiedente se presente, altrimenti restituisce una stringa vuota
	 */
	public String getIndirizzoEnte() {
		return indirizzoEnte.isPresent()?indirizzoEnte.get():"";
	}
	
	/**
	 * Metodo che setta la data di affidamento
	 *@param dataAffidamento data in cui e stata affidata la sacca, non può essere null e non puo essere inferiore alla data di arrivo
	 * @throws IllegalArgumentException quando la data di affidamento è precedente alla data di arrivo
	 */
	public void setDataAffidamento(LocalDate dataAffidamento) throws  IllegalArgumentException, AssertionError{
		assert dataAffidamento != null: "La data di affidamento non può essere null";
		if (dataAffidamento.isBefore(this.dataArrivo)) throw  new IllegalArgumentException("La data di arrivo non puo essere inferiore alla data di affidamento");
		this.dataAffidamento = Optional.of(dataAffidamento);
	}
	
	/**
	 * Metodo che setta l'ente richiedente
	*@param enteRichiedente  l'ente che ha richiesto la sacca, non può essere null
	*/
	public void setEnteRichiedente(String enteRichiedente)throws AssertionError{
		assert enteRichiedente != null: "l'ente richiedente non può essere null";
		this.enteRichiedente = Optional.of(enteRichiedente);
	}
	
	/**
	 * Metodo che setta l'indirizzo ente
	 *@param indirizzoEnte l'indirizzo dell'ente che ha richiesto la sacca, non può essere null
	 */
	public void setIndirizzoEnte(String indirizzoEnte) throws AssertionError{
		assert indirizzoEnte != null: "L'indirizzo dell'ente non puo essere null";
		this.indirizzoEnte = Optional.of(indirizzoEnte);
	}
	
	/**
	 * Metodo che ritorna una stringa concatenata dei valori espressi
	 * @return La stringa concatenata
 	 */
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

	/**
	 * Metodo che verifica l'uguaglianza tra due oggetti
	 * @param o oggetto del testing
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DatiSacca datiSacca = (DatiSacca) o;
		return seriale.equals(datiSacca.seriale);
	}

	/**
	 * Metodo per la creazione dell'hashcode
	 * @return hashcode dell'oggetto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(seriale);
	}

	/*
	 *Metodo per la stampa dell'etichetta della sacca 
	 *@param ps stream su cui stampare
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

	/*
	 *Metodo che ritorna l'etichetta dei dati sacca 
	 *@return etichetta  con i dati della sacca
	 */
	public String getEtichettaDatiSacca() {
		return  "Data di ingresso: "+this.dataArrivo+"\n"
				+"Data di affidamento: "+this.dataAffidamento.get()+"\n"
				+"Ente donatore: "		+this.enteDonatore + "\n";
	}
}