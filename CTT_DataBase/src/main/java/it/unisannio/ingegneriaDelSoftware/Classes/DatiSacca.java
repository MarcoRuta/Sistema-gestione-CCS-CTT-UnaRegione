package it.unisannio.ingegneriaDelSoftware.Classes;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class DatiSacca {
	/**
	seriale della sacca, è univoco non puo essere modificato una volta creata la sacca*/
	private final Seriale seriale;
	/** gruppo sanguigno della sacca, non puo essere modificato una volta creata la sacca*/
	private final GruppoSanguigno gruppo;
	/** data di arrivo della sacca, viene aggiunta quando la sacca è inserita nel magazzino, non puo essere modificata*/
	private final LocalDate dataArrivo;
	/** data in cui la sacca viene affidata ad un ente esterno*/
	private Optional<LocalDate> dataAffidamento;
	/**ente da cui proviene la sacca, nel momento in cui la sacca è creata lo si conosce e non puo essere modificato*/
	private final String enteDonatore;
	/**colui che richiede la sacca, viene aggiunto nel momento in cui la sacca è affidata*/
	private Optional<String> enteRichiedente;
	/**l'indirizzo di colui che richiede la sacca, viene aggiunto nel momento in cui la sacca è affidata*/
	private Optional<String> indirizzoEnte;
	

	/** costruttore invocato quando la sacca è inserita nel Magazzino
	 * @param dataArrivo  data in cui la sacca è arrivata in magazzino, non può essere temporalmente dopo la data di affidamento
	 * @param enteDonatore  ente da cui proviene la sacca
	 * @param seriale seriale della sacca
	 * @param gruppoSanguigno gruppo sanguigno della sacca
	 * @param dataAffidamento  data nella quale viene affidata la sacca. Se la sacca non è stata affidata può essere settato a null. Non puo essere inferiore alla data di arrivo
	 * @param enteRichiedente  ente che ha richiesto la saccca. Se la sacca non è stata affidata può essere settato a null*/
	public DatiSacca(Seriale seriale, GruppoSanguigno gruppoSanguigno, LocalDate dataArrivo, LocalDate dataAffidamento, String enteDonatore, String enteRichiedente, String indirizzoEnte) {
		assert seriale != null: "Il seriale non può essere nullo";
		assert gruppoSanguigno!= null: "Il gruppo sanguigno non puo essere nullo";
		assert dataArrivo != null: "La data di arrivo non può essere nulla";
		assert enteDonatore != null: "L'ente donatore non può essre nullo";

		this.seriale = seriale;
		this.gruppo = gruppoSanguigno;
		this.dataArrivo = dataArrivo;
		this.dataAffidamento = Optional.ofNullable(dataAffidamento); //se null, nel db compare lo stesso una data ma è 01-01-0001
		this.enteDonatore = enteDonatore;
		this.enteRichiedente = Optional.ofNullable(enteRichiedente);
		this.indirizzoEnte = Optional.ofNullable(indirizzoEnte);

	}

	/**@return  il seriale della sacca*/
	public Seriale getSeriale() {
		return seriale;
	}

	/**@return  il gruppo sanguigno della sacca*/
	public GruppoSanguigno getGruppoSanguigno() {
		return gruppo;
	}

	/**@return  la data di arrivo della sacca*/
	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	/**@return  restituisce la data di affidamento se presente, altrimenti restituisce una data con giorno,mese,anno settati a 0*/
	public LocalDate getDataAffidamento() {
		return dataAffidamento.isPresent()? dataAffidamento.get(): LocalDate.of(1,1,1);
	}

	public String getEnteDonatore() {
		return enteDonatore;
	}

	/**@return  restituisce l'ente richiedente se presente, altrimenti restituisce una stringa vuota*/
	public String getEnteRichiedente() {
		return enteRichiedente.isPresent()?enteRichiedente.get():"";
	}
	
	/**@return  restituisce l'indirizzo dell'ente richiedente se presente, altrimenti restituisce una stringa vuota*/
	public String getIndirizzoEnte() {
		return indirizzoEnte.isPresent()?indirizzoEnte.get():"";
	}
	
	/**@param dataAffidamento  data in cui e stata affidata la sacca, non può essere null e non puo essere inferiore alla data di arrivo*/
	public void setDataAffidamento(LocalDate dataAffidamento) {
		assert dataAffidamento != null: "La data di affidamento non può essere null";
		assert !(dataAffidamento.isBefore(this.dataArrivo)):"La data di arrivo non puo essere inferiore alla data di affidamento";
		this.dataAffidamento = Optional.of(dataAffidamento);
	}
	
	/**@param enteRichiedente  l'ente che ha richiesto la sacca. Non può essere null*/
	public void setEnteRichiedente(String enteRichiedente) {
		assert enteRichiedente != null: "l'ente richiedente non può essere null";
		this.enteRichiedente = Optional.of(enteRichiedente);
	}
	

	/**@param indirizzoEnte l'indirizzo dell'ente che ha richiesto la sacca. Non può essere null*/
	public void setIndirizzoEnte(String indirizzoEnte) {
		assert indirizzoEnte != null: "L'indirizzo dell'ente non puo essere null";
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

	/**@param ps stream su cui stampare etichetta della sacca.*/
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

	/**@return etichetta  con i dati della sacca*/
	public String getEtichettaDatiSacca() {
		return 	"Gruppo sanguigno: "	+this.gruppo.toString()+ "\n"
				+"Data di in ingresso: "+this.dataArrivo+"\n"
				+"Data di affidamento: "+this.dataAffidamento.get()+"\n"
				+"Ente richiedente: "	+this.enteRichiedente.get()+"\n"
				+"Ente donatore: "		+this.enteDonatore+"\n"
				+"Indirizzo Ente: "		+this.indirizzoEnte.get();
	}


}