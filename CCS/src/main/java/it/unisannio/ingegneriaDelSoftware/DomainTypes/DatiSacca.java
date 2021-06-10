package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class DatiSacca {

	private String seriale;
	private GruppoSanguigno gruppo;
	private LocalDate dataArrivo;
	private Optional<LocalDate> dataAffidamento;
	private String enteDonatore;
	private Optional<String> enteRichiedente;
	private Optional<String> indirizzoEnte;

	public DatiSacca() {
	}

	/** Metodo costruttore invocato quando la Sacca è inserita nel Magazzino
	 * @param seriale Seriale della Sacca
	 * @param gruppoSanguigno Gruppo sanguigno della Sacca
	 * @param dataArrivo Data in cui la Sacca è arrivata in magazzino, non può essere temporalmente dopo la data di affidamento
	 * @param dataAffidamento Data nella quale viene affidata la Sacca. Se la Sacca non è stata affidata può essere settato a null. Non può essere inferiore alla data di arrivo
	 * @param enteDonatore Ente da cui proviene la Sacca
	 * @param enteRichiedente Ente che ha richiesto la Sacca. Se la Sacca non è stata affidata può essere settato a null
	 * @param indirizzoEnte Indirizzo dell'ente richiedente
	 * @throws IllegalArgumentException se la data di affidamento è precedente a quella di arrivo*/
	public DatiSacca(String seriale, GruppoSanguigno gruppoSanguigno, LocalDate dataArrivo, LocalDate dataAffidamento, String enteDonatore, String enteRichiedente, String indirizzoEnte) throws AssertionError,IllegalArgumentException{

		this.seriale = seriale;
		this.gruppo = gruppoSanguigno;
		this.dataArrivo = dataArrivo;
		this.dataAffidamento = Optional.ofNullable(dataAffidamento);
		this.enteDonatore = enteDonatore;
		this.enteRichiedente = Optional.ofNullable(enteRichiedente);
		this.indirizzoEnte = Optional.ofNullable(indirizzoEnte);
	}

	/**Ritorna il seriale della sacca
	 * @return seriale Il seriale della sacca
	 */
	public String getSeriale() {
		return seriale;
	}

	/** Modifica il seriale della sacca
	 * @param seriale Il seriale della sacca
	 */
	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

	/**Ritorna il gruppo sanguigno della sacca
	 * @return gruppo Il gruppo sanguigno della sacca
	 */
	public GruppoSanguigno getGruppo() {
		return gruppo;
	}

	/** Modifica il gruppo sanguigno della sacca
	 * @param gruppo Il gruppo sanguigno della sacca
	 */
	public void setGruppo(GruppoSanguigno gruppo) {
		this.gruppo = gruppo;
	}

	/**Ritorna la data di arrivo della sacca
	 * @return dataArrivo La data di arrivo della sacca
	 */
	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	/** Modifica la data di arrivo della sacca
	 * @param dataArrivo La data di arrivo della sacca
	 */
	public void setDataArrivo(LocalDate dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	/**Ritorna la data di affidamento della sacca
	 * @return dataAffidamento La data di affidamento della sacca
	 */
	public Optional<LocalDate> getDataAffidamento() {
		return dataAffidamento;
	}

	/** Modifica la data di affidamento della sacca
	 * @param dataAffidamento La data di affidamento della sacca
	 */
	public void setDataAffidamento(Optional<LocalDate> dataAffidamento) {
		this.dataAffidamento = dataAffidamento;
	}

	/**Ritorna l'ente donatore della sacca
	 * @return enteDonatore L'ente donatore della sacca
	 */
	public String getEnteDonatore() {
		return enteDonatore;
	}

	/** Modifica l'ente donatore della sacca
	 * @param enteDonatore L'ente donatore della sacca
	 */
	public void setEnteDonatore(String enteDonatore) {
		this.enteDonatore = enteDonatore;
	}

	/**Ritorna l'ente richiedente della sacca
	 * @return enteRichiedente L'ente richiedente della sacca
	 */
	public Optional<String> getEnteRichiedente() {
		return enteRichiedente;
	}

	/** Modifica l'ente richiedente della sacca
	 * @param enteRichiedente L'ente richiedente della sacca
	 */
	public void setEnteRichiedente(Optional<String> enteRichiedente) {
		this.enteRichiedente = enteRichiedente;
	}

	/**Ritorna l'indirizzo dell'ente richiedente della sacca
	 * @return indirizzoEnte L'indirizzo dell'ente richiedente della sacca
	 */
	public Optional<String> getIndirizzoEnte() {
		return indirizzoEnte;
	}

	/** Modifica l'indirizzo dell'ente richiedente della sacca
	 * @param indirizzoEnte L'indirizzo dell'ente richiedente della sacca
	 */
	public void setIndirizzoEnte(Optional<String> indirizzoEnte) {
		this.indirizzoEnte = indirizzoEnte;
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
		ps.println("Seriale: "+this.seriale);
		ps.println("Gruppo sanguigno: "+this.gruppo);
		ps.println("Data di ingresso: "+this.dataArrivo);
		ps.println("Data di affidamento: "+ (this.dataAffidamento.isPresent()?this.dataAffidamento.get():""));
		ps.println("Ente donatore: "+this.enteDonatore);
		ps.println("Ente richiedente: "+(this.enteRichiedente.isPresent()?this.enteRichiedente.get():""));
		ps.println("Indirizzo Ente: "+(this.indirizzoEnte.isPresent()?this.indirizzoEnte.get():""));
	}

	/**Restituisce l'etichetta DatiSacca
	 * @return etichetta con i DatiSacca
	 */
	public String getEtichettaDatiSacca() {
		return   "Data di ingresso: "+this.dataArrivo+"\n"
				+"Data di affidamento: "+this.dataAffidamento.get()+"\n"
				+"Ente donatore: "		+this.enteDonatore + "\n";
	}
}