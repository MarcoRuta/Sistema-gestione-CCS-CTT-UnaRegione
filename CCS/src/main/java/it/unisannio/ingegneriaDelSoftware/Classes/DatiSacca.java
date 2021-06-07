package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class DatiSacca {
	
	/**Seriale della Sacca, è univoco non puo essere modificato una volta creata la Sacca*/
	private String seriale;
	/** Gruppo sanguigno della Sacca, non puo essere modificato una volta creata la Sacca*/
	private GruppoSanguigno gruppo;
	/** Data di arrivo della Sacca, viene aggiunta quando la Sacca è inserita nel magazzino, non puo essere modificata*/
	private LocalDate dataArrivo;
	/** Data in cui la Sacca viene affidata ad un ente esterno*/
	private Optional<LocalDate> dataAffidamento;
	/**Ente da cui proviene la Sacca, nel momento in cui la Sacca è creata lo si conosce e non puo essere modificato*/
	private String enteDonatore;
	/**Colui che richiede la Sacca, viene aggiunto nel momento in cui la Sacca è affidata*/
	private Optional<String> enteRichiedente;
	/**L'indirizzo di colui che richiede la Sacca, viene aggiunto nel momento in cui la Sacca è affidata*/
	private Optional<String> indirizzoEnte;

	public DatiSacca() {
	}

	/** Metodo costruttore invocato quando la Sacca è inserita nel Magazzino
	 * @param dataArrivo Data in cui la Sacca è arrivata in magazzino, non può essere temporalmente dopo la data di affidamento
	 * @param enteDonatore Ente da cui proviene la Sacca
	 * @param seriale Seriale della Sacca
	 * @param gruppoSanguigno Gruppo sanguigno della Sacca
	 * @param dataAffidamento Data nella quale viene affidata la Sacca. Se la Sacca non è stata affidata può essere settato a null. Non può essere inferiore alla data di arrivo
	 * @param enteRichiedente Ente che ha richiesto la Sacca. Se la Sacca non è stata affidata può essere settato a null
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


	public String getSeriale() {
		return seriale;
	}

	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

	public GruppoSanguigno getGruppo() {
		return gruppo;
	}

	public void setGruppo(GruppoSanguigno gruppo) {
		this.gruppo = gruppo;
	}

	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(LocalDate dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public Optional<LocalDate> getDataAffidamento() {
		return dataAffidamento;
	}

	public void setDataAffidamento(Optional<LocalDate> dataAffidamento) {
		this.dataAffidamento = dataAffidamento;
	}

	public String getEnteDonatore() {
		return enteDonatore;
	}

	public void setEnteDonatore(String enteDonatore) {
		this.enteDonatore = enteDonatore;
	}

	public Optional<String> getEnteRichiedente() {
		return enteRichiedente;
	}

	public void setEnteRichiedente(Optional<String> enteRichiedente) {
		this.enteRichiedente = enteRichiedente;
	}

	public Optional<String> getIndirizzoEnte() {
		return indirizzoEnte;
	}

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