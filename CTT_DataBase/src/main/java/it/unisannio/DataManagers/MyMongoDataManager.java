package it.unisannio.DataManagers;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import it.unisannio.Classes.DatiSacca;
import it.unisannio.Classes.Dipendente;
import it.unisannio.Classes.Sacca;
import it.unisannio.Interfaces.DataManager;
import it.unisannio.TipiAggiuntivi.Cdf;
import it.unisannio.TipiAggiuntivi.GruppoSanguigno;
import it.unisannio.TipiAggiuntivi.RuoloDipendente;
import it.unisannio.TipiAggiuntivi.Seriale;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

public class MyMongoDataManager implements DataManager {
	
//	private static final String TAG_DB = "db_mongo_name";
//	private static final String TAG_HOST = "db_mongo_host";
//	private static final String TAG_PORT = "db_mongo_port";
	
	private static final String COLLECTION_SACCHE = "SACCHE";
	private static final String ELEMENT_SERIALE = "seriale";
	private static final String ELEMENT_GRUPPO = "gruppo";
	private static final String ELEMENT_DATAPRODUZIONE = "dataProduzione";
	private static final String ELEMENT_DATASCADENZA = "dataScadenza";
	private static final String ELEMENT_PRENOTATO = "prenotato";
	
	private static final String COLLECTION_DATISACCHE = "DATISACCHE";
	private static final String ELEMENT_DATAARRIVO = "dataArrivo";
	private static final String ELEMENT_DATAAFFIDAMENTO = "dataAffidamento";
	private static final String ELEMENT_ENTEDONATORE = "enteDonatore";
	private static final String ELEMENT_ENTERICHIEDENTE = "enteRichiedente";
	
	private static final String COLLECTION_DIPENDENTI = "DIPENDENTI";
	private static final String ELEMENT_CDF = "cdf";
	private static final String ELEMENT_NOME = "nome";
	private static final String ELEMENT_COGNOME = "cognome";
	private static final String ELEMENT_DATADINASCITA = "dataDiNascita";
	private static final String ELEMENT_RUOLO = "ruolo";
	private static final String ELEMENT_USERNAME = "username";
	private static final String ELEMENT_PASSWORD = "password";

//    private static final String username = "root";
//    private static final String password = "gcRoot";
//    private static final String MONGO_CREATE_DB = "USE DATABASE IF NOT EXISTS ";
	
	private final String db;
//	private String host;
//	private String port;
	private final MongoClientURI connectionString;
	
/*	public MyMongoDataManager() {
		Properties loadProps = new Properties();
	    try {
			loadProps.loadFromXML(new FileInputStream("localsettings/db_settings.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    String host = loadProps.getProperty(TAG_HOST);
	    String port = loadProps.getProperty(TAG_PORT);
	    db = loadProps.getProperty(TAG_DB);
	    connectionString = new MongoClientURI("mongodb://"+host+":"+port);
	}
	*/
	
	public MyMongoDataManager() {
		this.connectionString = new MongoClientURI(
			    "mongodb+srv://laroccam99:0Mongodb0@cluster0.l6uc6.mongodb.net/jolly?retryWrites=true&w=majority");
		this.db = "DB1";
	}
	
/*	public void createDB() {
		// TODO Auto-generated method stub	
	}*/
	
	public void dropDB() {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		database.drop();
		mongoClient.close();
	}

	public void createSacca(Sacca s) {
//		assert(s!=null) throw new SaccaNullException();
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	    
	    Document sacca = new Document(ELEMENT_SERIALE, s.getSeriale().toString())
                .append(ELEMENT_GRUPPO, s.getGruppoSanguigno().toString())
                .append(ELEMENT_DATAPRODUZIONE, s.getDataProduzione().toString())
                .append(ELEMENT_DATASCADENZA, s.getDataScadenza())
                .append(ELEMENT_PRENOTATO, false);
	    collection.insertOne(sacca);
	    mongoClient.close();		
	}

	public void createDatiSacca(DatiSacca ds) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
	    
	    Document datiSacca = new Document(ELEMENT_SERIALE, ds.getSeriale().toString())
	    		.append(ELEMENT_GRUPPO, ds.getGruppoSanguigno().toString())
                .append(ELEMENT_DATAARRIVO, ds.getDataArrivo().toString())
                .append(ELEMENT_DATAAFFIDAMENTO, ds.getDataAffidamento().toString())
                .append(ELEMENT_ENTEDONATORE, ds.getEnteDonatore())
                .append(ELEMENT_ENTERICHIEDENTE, ds.getEnteRichiedente());
	    collection.insertOne(datiSacca);
	    mongoClient.close();
	}

//rimuove dal DB attivo la Sacca dopo che gli passi solo il seriale	
	public void removeSacca(Seriale ser) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		    
		for (Document current : collection.find(eq(ELEMENT_SERIALE,ser.toString()))) {
		    collection.deleteOne(current);
		}
		mongoClient.close();
	}
		
	public Sacca getSacca(Seriale ser) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		Sacca s = null;
		for (Document current : collection.find(eq(ELEMENT_SERIALE,ser.toString()))) {
			s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
					GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)),  
//					(Date)current.get(ELEMENT_DATAPRODUZIONE), 			/*non funziona*/
					current.get(ELEMENT_DATAPRODUZIONE, Date.class),	/*metodo alternativo per non usare il cast, non funziona lo stesso*/
					current.getDate(ELEMENT_DATASCADENZA),
					current.getBoolean(ELEMENT_PRENOTATO));
			mongoClient.close();
			return s;
		}
		mongoClient.close();
		return null;
	}
		
	public Sacca getSaccaPerRicerca(GruppoSanguigno gs, Date dataArrivoMassima) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		Sacca s=null;
		Sacca selez = null;
		Date dataScadenzaImminente = null; /*potrebbe dare problemi con after, nel caso possiamo impostare una data lontana 01-01-2999 */ 
		
		for (Document current : collection.find(eq(ELEMENT_GRUPPO,gs.toString()))) {
			if(current.getBoolean(ELEMENT_PRENOTATO)==false) { //ricerca solo su sacche non prenotate
				Date dataScadenza = current.getDate(ELEMENT_DATASCADENZA);
//se la Sacca (dello stesso GruppoSanguigno) scade prima della dataArrivoMassima ed è quella che scade per prima, viene selezionata			
				if(dataScadenzaImminente.after(dataScadenza) && dataScadenza.before(dataArrivoMassima)) {
					dataScadenzaImminente = dataScadenza;
					s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
							GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
							current.getDate(ELEMENT_DATAPRODUZIONE),
							current.getDate(ELEMENT_DATASCADENZA),
							current.getBoolean(ELEMENT_PRENOTATO));
					selez = s;
				}	
			}	
		}
		mongoClient.close();
		selez.setPrenotato();
		return selez;
	}
	
	public Sacca getSaccaCompatibilePerRicerca(GruppoSanguigno gs, Date dataArrivoMassima) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		Sacca s = null;
		Sacca selez = null;
		Date dataScadenzaImminente = null;
		Iterator<GruppoSanguigno> i = GruppoSanguigno.puoRicevereDa(gs);
		
		while(i.hasNext()) {
			GruppoSanguigno grs = i.next();
			for (Document current : collection.find(eq(ELEMENT_GRUPPO,grs.toString()))) {
				if(current.getBoolean(ELEMENT_PRENOTATO)==false) { //ricerca solo su sacche non prenotate
					Date dataScadenza = current.getDate(ELEMENT_DATASCADENZA);
					
					if(dataScadenzaImminente.after(dataScadenza) && dataScadenza.before(dataArrivoMassima)) {
						dataScadenzaImminente = dataScadenza;
						s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
							GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
							current.getDate(ELEMENT_DATAPRODUZIONE),
							current.getDate(ELEMENT_DATASCADENZA),
							current.getBoolean(ELEMENT_PRENOTATO));
						selez = s;
					}
				}
			}
		}
		mongoClient.close();
		selez.setPrenotato();
		return selez;
	}

	public DatiSacca getDatiSacca(Seriale ser) {	
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		
		for (Document current : collection.find(eq(ELEMENT_SERIALE,ser.toString()))) {
			DatiSacca ds = new DatiSacca(new Seriale(current.getString(ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
					current.getDate(ELEMENT_DATAARRIVO), 
					current.getDate(ELEMENT_DATAAFFIDAMENTO),
					current.getString(ELEMENT_ENTEDONATORE),
					current.getString(ELEMENT_ENTERICHIEDENTE));
			mongoClient.close();
			return ds;
		}
		mongoClient.close();
		return null;
	}
	
	@SuppressWarnings("null")
	public List<Sacca> getSaccaEntroScadenza() {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		
		List<Sacca> saccheInScadenza = null;		
		List<Document> sacche = collection.find().into(new ArrayList<Document>());
		Date oggi = new Date();
		long dataScadenza = oggi.getTime() + 259200000;

		for (Document current : sacche){
			if(current.getDate(ELEMENT_DATASCADENZA).getTime()<(dataScadenza)) {
				Sacca s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
						GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)),  
						current.getDate(ELEMENT_DATAPRODUZIONE),
						current.getDate(ELEMENT_DATASCADENZA),
						current.getBoolean(ELEMENT_PRENOTATO));
				saccheInScadenza.add(s);
			}						
		}
		mongoClient.close();
		return saccheInScadenza;
	}
	
	public List<Sacca> listaSaccheGS(GruppoSanguigno g){		
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	
	    List<Sacca> sacche = new ArrayList<Sacca>();
	    
		for (Document current : collection.find(eq(ELEMENT_GRUPPO,g.toString()))) {
			Sacca s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
					GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
					current.getDate(ELEMENT_DATAPRODUZIONE), 
					current.getDate(ELEMENT_DATASCADENZA),
					current.getBoolean(ELEMENT_PRENOTATO));
		    sacche.add(s);
		}
		mongoClient.close();	
		return sacche;
	}

	public void setDataArrivoDatiSacca(DatiSacca ds, Date dataArrivo) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		collection.findOneAndUpdate(eq(ELEMENT_SERIALE, ds.getSeriale().toString()), eq(ELEMENT_DATAARRIVO, dataArrivo.toString()));
		mongoClient.close();
	}

	public void setEnteRichiedenteDatiSacca(DatiSacca ds, String enteRichiedente) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		collection.findOneAndUpdate(eq(ELEMENT_SERIALE, ds.getSeriale().toString()), eq(ELEMENT_ENTERICHIEDENTE, enteRichiedente));	
		mongoClient.close();
	}

	public void setDataAffidamentoDatiSacca(DatiSacca ds, Date dataAffidamento) {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase(db);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		collection.findOneAndUpdate(eq(ELEMENT_SERIALE, ds.getSeriale().toString()), eq(ELEMENT_DATAAFFIDAMENTO, dataAffidamento));
		mongoClient.close();
	}

	public void addDipendente(Dipendente d) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    
	    Document unDipendente = new Document(ELEMENT_CDF, d.getCdf().toString())
                .append(ELEMENT_NOME, d.getNome())
                .append(ELEMENT_COGNOME, d.getCognome())
                .append(ELEMENT_DATADINASCITA, d.getDataDiNascita().toString())
                .append(ELEMENT_RUOLO, d.getRuolo().toString())
                .append(ELEMENT_USERNAME, d.getUsername())
                .append(ELEMENT_PASSWORD, d.getPassword());
	    collection.insertOne(unDipendente);
	    mongoClient.close();
	}
	
	public void removeDipendente(Cdf cdf) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    
	    for (Document current : collection.find(eq(ELEMENT_CDF,cdf.toString()))) {
	    	collection.deleteOne(current);
	    }
	    mongoClient.close();
	}

	public Dipendente getDipendente(String username, String password) {
//da testare, sia per RuoloDipendente, sia per controllare che la collezione non sia vuota all'inserimento di un username non previsto		
	    MongoCollection<Document> collection = this.setup(COLLECTION_DIPENDENTI);
	        
	    for (Document current : collection.find(eq(ELEMENT_USERNAME, username))) {
	    	if(password.equals(current.getString(ELEMENT_PASSWORD))) {
	    		Dipendente dip = new Dipendente(new Cdf(current.getString(ELEMENT_CDF)), 
	    				current.getString(ELEMENT_NOME), 
	    				current.getString(ELEMENT_COGNOME),
	    				current.getDate(ELEMENT_DATADINASCITA),
	    				RuoloDipendente.valueOf(current.getString(ELEMENT_RUOLO)), 
	    				current.getString(ELEMENT_USERNAME),
	    				current.getString(ELEMENT_PASSWORD));
	    		return dip;
	    	}
	    }		
		return null;
	}
//da testare	
	private MongoCollection<Document> setup(String COLLECTION) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION);
	    mongoClient.close();
	    return collection;
	}

	@SuppressWarnings("null")
	public List<Dipendente> getlistaDipendentiByRuolo(RuoloDipendente ruolo) {
		MongoClient mongoClient = new MongoClient(connectionString);
	    MongoDatabase database = mongoClient.getDatabase(db);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    
        List<Dipendente> dipendenti = null;
		
	    for (Document current : collection.find(eq(ELEMENT_RUOLO, ruolo))) {
	    		Dipendente dip = new Dipendente(new Cdf(current.getString(ELEMENT_CDF)), 
	    				current.getString(ELEMENT_NOME), 
	    				current.getString(ELEMENT_COGNOME),
	    				current.getDate(ELEMENT_DATADINASCITA),
	    				RuoloDipendente.valueOf(current.getString(ELEMENT_RUOLO)),
	    				current.getString(ELEMENT_USERNAME),
	    				current.getString(ELEMENT_PASSWORD));
	    		dipendenti.add(dip);
	    }		
	    mongoClient.close();
		return dipendenti;
	}

}