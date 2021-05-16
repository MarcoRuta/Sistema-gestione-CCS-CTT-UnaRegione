package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.time.LocalDate;
import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
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

	
	/**Aggiunge una Sacca al database delle sacche solo se essa non è gia presente nel
	 * DB delle sacche
	 * @param s Sacca da aggiungere al db
	 */
	public void createSacca(Sacca s) {
		assert !(this.containsSacca(s.getSeriale())):"Sacca gia presente nel DB";
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
		assert !(this.containsDatiSacca(ds.getSeriale())):"Dati Sacca già presente nel DB";
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

	
	/**Rimuove una Sacca dal DataBase identificata tramite il Seriale
	 * @param ser Seriale della sacca da rimuovere dal db delle sacche
	 */
	public void removeSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
		    
		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale())))
		    collection.deleteOne(current);
	}

	
	/**Restituisce una Sacca ricercata sul database dalle Sacche tramite il Seriale
	 * @param ser Seriale della Sacca da ricercare
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 */
	public Sacca getSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
		Sacca s = null;
		
		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE, ser.getSeriale()))) {
			s = new Sacca( new Seriale(current.getString(Constants.ELEMENT_SERIALE)),
				GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
				DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAPRODUZIONE)),
				DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATASCADENZA)),
				current.getBoolean(Constants.ELEMENT_PRENOTATO));
		}
		return s;	
	}

	
	/**Restituisce i dati di una Sacca ricercata sul database delle Sacche tramite il Seriale
	 * @param ser Seriale della Sacca da ricercare
	 * @return null se la Sacca non è stata trovata; DatiSacca se essa è stata trovata
	 */
	public DatiSacca getDatiSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		DatiSacca ds = null;
		
		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale()))) {
			ds = new DatiSacca(new Seriale(current.getString(Constants.ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAARRIVO)),
					DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAAFFIDAMENTO)),
					current.getString(Constants.ELEMENT_ENTEDONATORE),
					current.getString(Constants.ELEMENT_ENTERICHIEDENTE),
					current.getString(Constants.ELEMENT_INDIRIZZOENTE));
		}
		return ds;	
	}
	
	
	/** Restituisce una lista contenente tutte le Sacche presenti in magazzino
	 * @return la lista delle Sacche presenti in magazzino
	 */
	public List<Sacca> getListaSacche(){
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
        List<Sacca> listaSacche = new ArrayList<Sacca>();
        
        for (Document current : collection.find())
                listaSacche.add(this.getSacca(new Seriale(current.getString(Constants.ELEMENT_SERIALE))));
        return listaSacche;
	}
	
	
	/**Restituisce la lista dei datiSacche presenti nel database
	 * @return la lista dei DatiSacca che sono presenti nel database
	 */
	public List<DatiSacca> getListaDatiSacche() {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
	    List<DatiSacca> datiSacche = new ArrayList<DatiSacca>();
	   
	    for (Document current : collection.find())
	    	datiSacche.add(this.getDatiSacca(new Seriale(current.getString(Constants.ELEMENT_SERIALE))));
		return datiSacche;		
	}
	  	
	
	/**Cambia lo stato di prenotazione di una Sacca identificata tramite il Seriale
	 * @param seriale Seriale della Sacca da ricercare
	 */
	public void setPrenotatoSacca(Seriale seriale) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
		
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_PRENOTATO, true));
	}
	
	
	/** Modifica il parametro dataArrivo di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della sacca da modificare
	 * @param dataArrivo Data di arrivo aggiornata
	 */
	public void setDataArrivoDatiSacca(Seriale seriale, LocalDate dataArrivo) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_DATAARRIVO, dataArrivo));
	}
    	
	
	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della Sacca da modificare
	 * @param enteRichiedente Ente a cui sarà affidata la Sacca
	 */
	public void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_ENTERICHIEDENTE, enteRichiedente));
	}

	/**Modifica il parametro indirizzoEnte di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della Sacca da modificare
	 * @param indirizzoEnte indirizzo dell'ente a cui sarà affidata la Sacca
	 */
	public void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzoEnte) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_INDIRIZZOENTE, indirizzoEnte));
	}
	
	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della Sacca da ricercare
	 * @param dataAffidamento Data in cui è stata affidata la Sacca
	 */
	public void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_DATAAFFIDAMENTO, DateUtil.convertLocalDateToDate(dataAffidamento)));
	}

	
	/**Aggiungere un Dipendente al database dei Dipendenti solo se esso non è stato gia aggiunto
	 * @param d Dipendente da aggiungere al database dei Dipendenti
	 */
	public void addDipendente(Dipendente d) {
		assert !(this.containsDipendente(d.getCdf())):"Dipendente già presente nel DB";
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
	
	
	/**Elimina un Dipendente al database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da eliminare
	 */
	public void removeDipendente(Cdf cdf) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	    
	    for (Document current : collection.find(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale())))
	    	collection.deleteOne(current);
	}	
	
	
	/**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
	 * @param cdf cdf del dipendente che si sta ricercando
	 * @return il Dipendente cercato
	 */
	public Dipendente getDipendente(Cdf cdf) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	        
	    for (Document current : collection.find(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale()))) {
	        		  Dipendente dip = new Dipendente(new Cdf(current.getString(Constants.ELEMENT_CDF)),
	    				current.getString(Constants.ELEMENT_NOME),
	    				current.getString(Constants.ELEMENT_COGNOME),
	    				DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
	    				RuoloDipendente.valueOf(current.getString(Constants.ELEMENT_RUOLO)),
	    				current.getString(Constants.ELEMENT_USERNAME),
	    				current.getString(Constants.ELEMENT_PASSWORD));
	    		return dip;
	    	}
	    	
		return null;
	}

	/**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
	 * @param username Username del Dipendente
	 * @param password Password del Dipendente
	 * @return il Dipendente cercato
	 */
	public Dipendente getDipendente(String username, String password) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	        
	    for (Document current : collection.find(eq(Constants.ELEMENT_USERNAME, username))) {
	    	if(password.equals(current.getString(Constants.ELEMENT_PASSWORD))) {
	    		Dipendente dip = new Dipendente(new Cdf(current.getString(Constants.ELEMENT_CDF)),
	    				current.getString(Constants.ELEMENT_NOME),
	    				current.getString(Constants.ELEMENT_COGNOME),
	    				DateUtil.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
	    				RuoloDipendente.valueOf(current.getString(Constants.ELEMENT_RUOLO)),
	    				current.getString(Constants.ELEMENT_USERNAME),
	    				current.getString(Constants.ELEMENT_PASSWORD));
	    		return dip;
	    	}
	    }		
		return null;
	}



	/**Restituisce la lista dei Dipendenti del CTT presenti nel database
	 * @return la lista dei Dipendenti del CTT
	 */
	public List<Dipendente> getListaDipendenti() {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
        List<Dipendente> dipendenti = new ArrayList<Dipendente>();

        for (Document current : collection.find())
               dipendenti.add(this.getDipendente(new Cdf(current.getString(Constants.ELEMENT_CDF))));
        return dipendenti;
    }

	/**@param seriale il seriale della sacca che si vuole cercare
     * @return true se la sacca è contenuta*/
    public boolean containsSacca(Seriale seriale){
        Sacca unSacca = this.getSacca(seriale);
        return unSacca != null?true:false;
    }
    
    
    /**@param seriale il seriale della sacca che si vuole cercare
     * @return true se i dati sacca sono contenuti*/
    public boolean containsDatiSacca(Seriale seriale){
        DatiSacca unDatiSacca = this.getDatiSacca(seriale);
        return unDatiSacca != null?true:false;
    }


    /**@param cdf il codice fiscale del dipendente che si vuole cercare
     * @return true se la sacca è contenuta*/
    public boolean containsDipendente(Cdf cdf){
        Dipendente unDipendente = this.getDipendente(cdf);
        return unDipendente != null?true:false;
    }

}