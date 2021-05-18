package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.time.LocalDate;
import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import it.unisannio.ingegneriaDelSoftware.Exceptions.DatiSaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;


public class MongoDataManager implements DataManager {

	private static MongoClient mongoClient;

	static{
		mongoClient = new MongoClient();
	}

	public void dropDB() {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		database.drop();
		mongoClient.close();
	}


	/**Aggiunge una Sacca al database delle sacche solo se essa non è gia presente nel DB delle sacche
	 * @param s Sacca da aggiungere al db
	 */
	public void createSacca(Sacca s){
		if (this.containsSacca(s.getSeriale())) throw new AssertionError("Sacca gia presente nel DB");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);

		Document sacca = new Document(Constants.ELEMENT_SERIALE, s.getSeriale().getSeriale())
				.append(Constants.ELEMENT_GRUPPO, s.getGruppoSanguigno().toString())
				.append(Constants.ELEMENT_DATAPRODUZIONE, DateUtil.convertLocalDateToDate(s.getDataProduzione()))
				.append(Constants.ELEMENT_DATASCADENZA, DateUtil.convertLocalDateToDate(s.getDataScadenza()))
				.append(Constants.ELEMENT_PRENOTATO, s.isPrenotato());
		collection.insertOne(sacca);
	}


	/**Aggiunge un DatiSacca al database dei datiSacca solo se il dati sacca non è gia presente
	 * @param ds datiSacca da aggiungere al db
	 */
	public void createDatiSacca(DatiSacca ds) {
		if(this.containsDatiSacca(ds.getSeriale())) throw new AssertionError("Dati Sacca già presente nel DB");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);

		Document datiSacca = new Document(Constants.ELEMENT_SERIALE, ds.getSeriale().getSeriale())
				.append(Constants.ELEMENT_GRUPPO, ds.getGruppoSanguigno().toString())
				.append(Constants.ELEMENT_DATAARRIVO, DateUtil.convertLocalDateToDate(ds.getDataArrivo()))
				.append(Constants.ELEMENT_DATAAFFIDAMENTO, DateUtil.convertLocalDateToDate(ds.getDataAffidamento()))
				.append(Constants.ELEMENT_ENTEDONATORE, ds.getEnteDonatore())
				.append(Constants.ELEMENT_ENTERICHIEDENTE, ds.getEnteRichiedente())
				.append(Constants.ELEMENT_INDIRIZZOENTE, ds.getIndirizzoEnte());
		collection.insertOne(datiSacca);
	}


	/**Rimuove una Sacca dal DataBase identificata tramite il Seriale, solo se essa è gia presente
	 * @param ser Seriale della sacca da rimuovere dal db delle sacche
	 */
	public void removeSacca(Seriale ser){
		if(!this.containsSacca(ser)) throw new AssertionError("Non è possibile rimuovere una sacca non presente nel DB");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);

		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale())))
			collection.deleteOne(current);
	}


	/**Restituisce una Sacca ricercata sul database dalle Sacche tramite il Seriale
	 * @param ser Seriale della Sacca da ricercare
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 * @throws SaccaNotFoundException
	 */
	public Sacca getSacca(Seriale ser) throws SaccaNotFoundException {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);

		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE, ser.getSeriale()))) {
			Sacca s = new Sacca(Seriale.getSeriale(current.getString(Constants.ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAPRODUZIONE)),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATASCADENZA)),
					current.getBoolean(Constants.ELEMENT_PRENOTATO));
			return s;
		}
		throw new SaccaNotFoundException("La sacca ricercata"+ ser.getSeriale() +"non è stata trovata");
	}


	/**Restituisce i dati di una Sacca ricercata sul database delle Sacche tramite il Seriale
	 * @param ser Seriale della Sacca da ricercare
	 * @return null se la Sacca non è stata trovata; DatiSacca se essa è stata trovata
	 * @throws DatiSaccaNotFoundException
	 */
	public DatiSacca getDatiSacca(Seriale ser) throws DatiSaccaNotFoundException{
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);

		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale()))) {
			DatiSacca ds = new DatiSacca(Seriale.getSeriale(current.getString(Constants.ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAARRIVO)),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAAFFIDAMENTO)),
					current.getString(Constants.ELEMENT_ENTEDONATORE),
					current.getString(Constants.ELEMENT_ENTERICHIEDENTE),
					current.getString(Constants.ELEMENT_INDIRIZZOENTE));
			return ds;
		}
		throw new DatiSaccaNotFoundException("I DatiSacca ricercati"+ ser.getSeriale() +"non sono stati trovati");

	}


	/** Restituisce una lista contenente tutte le Sacche presenti in magazzino
	 * @return la lista delle Sacche presenti in magazzino
	 */
	public List<Sacca> getListaSacche(){
		try {
			MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
			List<Sacca> listaSacche = new ArrayList<Sacca>();

			for (Document current : collection.find())
				listaSacche.add(this.getSacca(Seriale.getSeriale(current.getString(Constants.ELEMENT_SERIALE))));
			return listaSacche;
		}catch(SaccaNotFoundException e){
			//do nothing
			//in questo caso l'eccezione non puo mai verificarsi.
			return null;
		}
	}


	/**Restituisce la lista dei datiSacche presenti nel database
	 * @return la lista dei DatiSacca che sono presenti nel database
	 */
	public List<DatiSacca> getListaDatiSacche(){
		try {
			MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
			List<DatiSacca> datiSacche = new ArrayList<DatiSacca>();

			for (Document current : collection.find())
				datiSacche.add(this.getDatiSacca(Seriale.getSeriale(current.getString(Constants.ELEMENT_SERIALE))));
			return datiSacche;
		}catch(DatiSaccaNotFoundException e){
			//do nothing
			//in questo caso l'eccezione non puo mai verificarsi.
			return null;
		}
	}


	/**Cambia lo stato di prenotazione di una Sacca identificata tramite il Seriale solo se essa esiste
	 * @param seriale Seriale della Sacca da ricercare
	 */
	public void setPrenotatoSacca(Seriale seriale){
		if (!this.containsSacca(seriale)) throw new AssertionError("Non puoi aggiornare lo stato di una sacca che non esiste");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);

		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_PRENOTATO, true));
	}


	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
	 * @param seriale Seriale della Sacca da modificare
	 * @param enteRichiedente Ente a cui sarà affidata la Sacca
	 */
	public void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente) {
		if(!this.containsDatiSacca(seriale)) throw new AssertionError("Non puoi cambiare l'ente richiedente di una sacca non presente nel DB");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_ENTERICHIEDENTE, enteRichiedente));
	}

	/**Modifica il parametro indirizzoEnte di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
	 * @param seriale Seriale della Sacca da modificare
	 * @param indirizzoEnte indirizzo dell'ente a cui sarà affidata la Sacca
	 */
	public void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzoEnte) {
		if(!this.containsDatiSacca(seriale)) throw new DatiSaccaNotFoundException("Non puoi cambiare l'indirizzo dell'ente richiedente di una sacca non presente nel DB");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_INDIRIZZOENTE, indirizzoEnte));
	}

	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se il DatiSacca esiste
	 * @param seriale Seriale della Sacca da ricercare
	 * @param dataAffidamento Data in cui è stata affidata la Sacca
	 */
	public void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) {
		if (!this.containsDatiSacca(seriale)) throw new DatiSaccaNotFoundException("Non puoi cambiare la data di affidamento di una sacca non presente nel DB");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_DATAAFFIDAMENTO, DateUtil.convertLocalDateToDate(dataAffidamento)));
	}

	/**Modifica la password di un Dipendente all'interno del DB solo se esso esiste
	 * @param password la nuova passworda da aggiungere
	 * @param cdf  il codice fiscale del Dipendente di cui si vuole aggiornare la password
	 * */
	public void setPassword(Cdf cdf, String password) throws DipendenteNotFoundException {
		if (!this.containsDipendente(cdf)) throw new AssertionError( "Non puoi cambiare la password di un utente non esistente");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
		collection.updateOne(
				eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale()),
				Updates.set(Constants.ELEMENT_PASSWORD, password ));
	}


	/**Aggiungere un Dipendente al database dei Dipendenti solo se esso non è stato gia aggiunto
	 * @param d Dipendente da aggiungere al database dei Dipendenti
	 */
	public void addDipendente(Dipendente d){
		if (this.containsDipendente(d.getCdf())) throw new AssertionError("Dipendente gia presente");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);

		Document unDipendente = new Document(Constants.ELEMENT_CDF, d.getCdf().getCodiceFiscale())
				.append(Constants.ELEMENT_NOME, d.getNome())
				.append(Constants.ELEMENT_COGNOME, d.getCognome())
				.append(Constants.ELEMENT_DATADINASCITA, DateUtil.convertLocalDateToDate(d.getDataDiNascita()))
				.append(Constants.ELEMENT_RUOLO, d.getRuolo().toString())
				.append(Constants.ELEMENT_USERNAME, d.getUsername())
				.append(Constants.ELEMENT_PASSWORD, d.getPassword());
		collection.insertOne(unDipendente);
	}


	/**Elimina un Dipendente al database dei Dipendenti solo se siste
	 * @param cdf Codice fiscale del Dipendente da eliminare
	 */
	public void removeDipendente(Cdf cdf){
		if (!this.containsDipendente(cdf)) throw new AssertionError("Dipendente non presente nel db");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);

		for (Document current : collection.find(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale())))
			collection.deleteOne(current);
	}


	/**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
	 * @param cdf cdf del dipendente che si sta ricercando
	 * @return il Dipendente cercato
	 */
	public Dipendente getDipendente(Cdf cdf) throws DipendenteNotFoundException {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);

		for (Document current : collection.find(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale()))) {
			Dipendente dip = new Dipendente(Cdf.getCDF(current.getString(Constants.ELEMENT_CDF)),
					current.getString(Constants.ELEMENT_NOME),
					current.getString(Constants.ELEMENT_COGNOME),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
					RuoloDipendente.valueOf(current.getString(Constants.ELEMENT_RUOLO)),
					current.getString(Constants.ELEMENT_USERNAME),
					current.getString(Constants.ELEMENT_PASSWORD));
			return dip;
		}

		throw new DipendenteNotFoundException("Impossibile trovare il dipedenten associato al cdf: "+ cdf.getCodiceFiscale());
	}


	/**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
	 * @param username Username del Dipendente
	 * @param password Password del Dipendente
	 * @return il Dipendente cercato
	 */
	public Dipendente getDipendente(String username, String password) throws DipendenteNotFoundException{
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);

		for (Document current : collection.find(eq(Constants.ELEMENT_USERNAME, username)))
			if(password.equals(current.getString(Constants.ELEMENT_PASSWORD)))
				return this.getDipendente(Cdf.getCDF(current.getString(Constants.ELEMENT_CDF)));
		throw new DipendenteNotFoundException("Impossibile trovare il dipedenten associato a username: "+username +" password:"+password);
	}


	/**Restituisce la lista dei Dipendenti del CTT presenti nel database
	 * @return la lista dei Dipendenti del CTT
	 */
	public List<Dipendente> getListaDipendenti(){
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
		List<Dipendente> dipendenti = new ArrayList<Dipendente>();

		for (Document current : collection.find())
			dipendenti.add(this.getDipendente(Cdf.getCDF(current.getString(Constants.ELEMENT_CDF))));
		return dipendenti;
	}


	/**@param seriale il seriale della sacca che si vuole cercare
	 * @return true se la sacca è contenuta
	 */
	public boolean containsSacca(Seriale seriale) {
		try {
			Sacca unSacca = this.getSacca(seriale);
			return unSacca != null?true:false;
		}catch(SaccaNotFoundException e) {
			return false;
		}

	}


	/**@param seriale il seriale della sacca che si vuole cercare
	 * @return true se i dati sacca sono contenuti*/
	public boolean containsDatiSacca(Seriale seriale){
		try {
			DatiSacca unDatiSacca = this.getDatiSacca(seriale);
			return unDatiSacca != null?true:false;
		}catch(DatiSaccaNotFoundException e) {
			return false;
		}

	}


	/**@param cdf il codice fiscale del dipendente che si vuole cercare
	 * @return true se la sacca è contenuta*/
	public boolean containsDipendente(Cdf cdf){
		try {
			Dipendente unDipendente = this.getDipendente(cdf);
			return unDipendente != null?true:false;
		}catch (DipendenteNotFoundException e) {
			return false;
		}
	}
}