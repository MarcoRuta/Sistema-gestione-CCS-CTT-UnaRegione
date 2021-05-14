package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.time.LocalDate;
import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;


public class MyMongoDataManager implements DataManager {
	

	
	private MongoClient mongoClient;
	
	public MyMongoDataManager() {
		mongoClient = new MongoClient();
	}

	public void dropDB() {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        database.drop();
        mongoClient.close();
    }

	
	/**Aggiunge una Sacca al database delle sacche
	 * @param s Sacca da aggiungere al db
	 */
	public void createSacca(Sacca s) {
	    MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
	    
	    Document sacca = new Document(Constants.ELEMENT_SERIALE, s.getSeriale().getSeriale())
                .append(Constants.ELEMENT_GRUPPO, s.getGruppoSanguigno().toString())
                .append(Constants.ELEMENT_DATAPRODUZIONE, DateConverter.convertLocalDateToDate(s.getDataProduzione()))
                .append(Constants.ELEMENT_DATASCADENZA, DateConverter.convertLocalDateToDate(s.getDataScadenza()))
                .append(Constants.ELEMENT_PRENOTATO, s.isPrenotato());
	    collection.insertOne(sacca);
	}

	
	/**Aggiunge un DatiSacca al database dei datiSacca
	 * @param ds datiSacca da aggiungere al db
	 */
	public void createDatiSacca(DatiSacca ds) {
	    MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
	    
	    Document datiSacca = new Document(Constants.ELEMENT_SERIALE, ds.getSeriale().getSeriale())
	    		.append(Constants.ELEMENT_GRUPPO, ds.getGruppoSanguigno().toString())
                .append(Constants.ELEMENT_DATAARRIVO, DateConverter.convertLocalDateToDate(ds.getDataArrivo()))
                .append(Constants.ELEMENT_DATAAFFIDAMENTO, DateConverter.convertLocalDateToDate(ds.getDataAffidamento()))
                .append(Constants.ELEMENT_ENTEDONATORE, ds.getEnteDonatore())
                .append(Constants.ELEMENT_ENTERICHIEDENTE, ds.getEnteRichiedente());
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

	
	/**Restituisce una Sacca ricercata sul database dalle sacche tramite il Seriale
	 * @param ser Seriale della sacca da ricercare
	 * @return null se la sacca non è stata trovata; la sacca se essa è stata trovata
	 */
	public Sacca getSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
		
		Sacca s = null;
		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE, ser.getSeriale())))
			s = new Sacca( new Seriale(current.getString(Constants.ELEMENT_SERIALE)),
				GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
					DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAPRODUZIONE)),
					DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATASCADENZA)),
				current.getBoolean(Constants.ELEMENT_PRENOTATO));
			return s;

	}

	
	/**Restituisce i dati di una Sacca ricercata sul database delle sacche tramite il Seriale
	 * @param ser Seriale della sacca da ricercare
	 * @return null se la sacca non è stata trovata; DatiSacca se essa è stata trovata
	 */
	public DatiSacca getDatiSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		DatiSacca ds = null;
		for (Document current : collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale())))
			ds = new DatiSacca(new Seriale(current.getString(Constants.ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
					DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAARRIVO)),
					DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAAFFIDAMENTO)),
					current.getString(Constants.ELEMENT_ENTEDONATORE),
					current.getString(Constants.ELEMENT_ENTERICHIEDENTE));
			return ds;
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
		
	/**Restituisce la lista dei datiSacche presenti nel database
	 * @return la lista dei dati sacca che sono presenti nel database
	 */
	public List<DatiSacca> getListaDatiSacche() {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
	    List<DatiSacca> datiSacche = new ArrayList<DatiSacca>();
	   
	    for (Document current : collection.find()) {
	       
			DatiSacca ds = new DatiSacca(new Seriale(current.getString(Constants.ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
					DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAARRIVO)),
					DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAAFFIDAMENTO)),
					current.getString(Constants.ELEMENT_ENTEDONATORE),
					current.getString(Constants.ELEMENT_ENTERICHIEDENTE));
			
	    	datiSacche.add(ds);
	    	
		}	    
		return datiSacche;
		
	}
	
	/** Restituisce una lista contenente tutte le Sacche presenti in magazzino
	 * @return la lista delle Sacche presenti in magazzino
	 */
	public List<Sacca> getListaSacche(){
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_SACCHE);
        List<Sacca> listaSacche = new ArrayList<Sacca>();
        
        List<Document> sacche = collection.find().into(new ArrayList<Document>());
        
        
        for (Document current : sacche){
                Sacca s = new Sacca(new Seriale(current.getString(Constants.ELEMENT_SERIALE)),
                        GruppoSanguigno.valueOf(current.getString(Constants.ELEMENT_GRUPPO)),
                        DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATAPRODUZIONE)),
                        DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATASCADENZA)),
                        current.getBoolean(Constants.ELEMENT_PRENOTATO));
                listaSacche.add(s);
            }
        return listaSacche;
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

	
	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della Sacca da ricercare
	 * @param dataAffidamento Data in cui è stata affidata la Sacca
	 */
	public void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(Constants.ELEMENT_DATAAFFIDAMENTO,DateConverter.convertLocalDateToDate(dataAffidamento)));

	}

	
	/**Aggiungere un Dipendente al database dei Dipendenti
	 * @param d Dipendente da aggiungere al database dei Dipendenti
	 */
	public void addDipendente(Dipendente d) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	    
	    Document unDipendente = new Document(Constants.ELEMENT_CDF, d.getCdf().getCodiceFiscale())
                .append(Constants.ELEMENT_NOME, d.getNome())
                .append(Constants.ELEMENT_COGNOME, d.getCognome())
                .append(Constants.ELEMENT_DATADINASCITA, DateConverter.convertLocalDateToDate(d.getDataDiNascita()))
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
	    				DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
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

        for (Document current : collection.find()) {
                Dipendente dip = new Dipendente(new Cdf(current.getString(Constants.ELEMENT_CDF)),
                        current.getString(Constants.ELEMENT_NOME),
                        current.getString(Constants.ELEMENT_COGNOME),
                        DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
                        RuoloDipendente.valueOf(current.getString(Constants.ELEMENT_RUOLO)),
                        current.getString(Constants.ELEMENT_USERNAME),
                        current.getString(Constants.ELEMENT_PASSWORD));
               dipendenti.add(dip);
        }
        mongoClient.close();
        return dipendenti;
    }

}