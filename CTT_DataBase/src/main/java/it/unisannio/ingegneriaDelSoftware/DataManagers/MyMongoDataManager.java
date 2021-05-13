package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Util.*;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;


public class MyMongoDataManager implements DataManager {
	
	private static final String DB_NAME = "CTT";
	
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
	
	private MongoClient mongoClient;
	
	public MyMongoDataManager() {
		mongoClient = new MongoClient();
	}

	public void dropDB() {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        database.drop();
        mongoClient.close();
    }

	
	/**Aggiunge una Sacca al database delle sacche
	 * @param s Sacca da aggiungere al db
	 */
	public void createSacca(Sacca s) {
	    MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	    
	    Document sacca = new Document(ELEMENT_SERIALE, s.getSeriale().getSeriale())
                .append(ELEMENT_GRUPPO, s.getGruppoSanguigno().toString())
                .append(ELEMENT_DATAPRODUZIONE, DateConverter.convertLocalDateToDate(s.getDataProduzione()))
                .append(ELEMENT_DATASCADENZA, DateConverter.convertLocalDateToDate(s.getDataScadenza()))
                .append(ELEMENT_PRENOTATO, s.isPrenotato());
	    collection.insertOne(sacca);
	}

	
	/**Aggiunge un DatiSacca al database dei datiSacca
	 * @param ds datiSacca da aggiungere al db
	 */
	public void createDatiSacca(DatiSacca ds) {
	    MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
	    
	    Document datiSacca = new Document(ELEMENT_SERIALE, ds.getSeriale().getSeriale())
	    		.append(ELEMENT_GRUPPO, ds.getGruppoSanguigno().toString())
                .append(ELEMENT_DATAARRIVO, DateConverter.convertLocalDateToDate(ds.getDataArrivo()))
                .append(ELEMENT_DATAAFFIDAMENTO, DateConverter.convertLocalDateToDate(ds.getDataAffidamento()))
                .append(ELEMENT_ENTEDONATORE, ds.getEnteDonatore())
                .append(ELEMENT_ENTERICHIEDENTE, ds.getEnteRichiedente());
	    collection.insertOne(datiSacca);
	}

	
	/**Rimuove una Sacca dal DataBase identificata tramite il Seriale
	 * @param ser Seriale della sacca da rimuovere dal db delle sacche
	 */
	public void removeSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		    
		for (Document current : collection.find(eq(ELEMENT_SERIALE,ser.getSeriale())))
		    collection.deleteOne(current);
	}

	
	/**Restituisce una Sacca ricercata sul database dalle sacche tramite il Seriale
	 * @param ser Seriale della sacca da ricercare
	 * @return null se la sacca non è stata trovata; la sacca se essa è stata trovata
	 */
	public Sacca getSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		
		Sacca s = null;
		for (Document current : collection.find(eq(ELEMENT_SERIALE, ser.getSeriale())))
			s = new Sacca( new Seriale(current.getString(ELEMENT_SERIALE)),
				GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)),
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAPRODUZIONE)),
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATASCADENZA)),
				current.getBoolean(ELEMENT_PRENOTATO));
			return s;

	}

	
	/**Restituisce i dati di una Sacca ricercata sul database delle sacche tramite il Seriale
	 * @param ser Seriale della sacca da ricercare
	 * @return null se la sacca non è stata trovata; DatiSacca se essa è stata trovata
	 */
	public DatiSacca getDatiSacca(Seriale ser) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		DatiSacca ds = null;
		for (Document current : collection.find(eq(ELEMENT_SERIALE,ser.getSeriale())))
			ds = new DatiSacca(new Seriale(current.getString(ELEMENT_SERIALE)),
					GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)),
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAARRIVO)),
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAAFFIDAMENTO)),
					current.getString(ELEMENT_ENTEDONATORE),
					current.getString(ELEMENT_ENTERICHIEDENTE));
			return ds;
	}

	/**Cambia lo stato di prenotazione di una Sacca identificata tramite il Seriale
	 * @param seriale Seriale della Sacca da ricercare
	 */
	public void setPrenotatoSacca(Seriale seriale) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
		collection.updateOne(
				eq(ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(ELEMENT_PRENOTATO, true));
	}
	
	
	/**Cerca e resituisce una Sacca all'interno del database delle sacche che non scada entro una dataArrivoMassima
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la sacca non è stata trovata; la Sacca se essa è stata trovata 
	 */
	public Sacca getSaccaPerRicerca(GruppoSanguigno gs, LocalDate dataArrivoMassima) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	    
		Sacca s=null;
		Sacca selez = new Sacca();
		Date dataScadenzaImminente = null;
		try {
			dataScadenzaImminente = Constants.sdf.parse("01-01-2999");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (Document current : collection.find(eq(ELEMENT_GRUPPO, gs.toString()))) {
			if(current.getBoolean(ELEMENT_PRENOTATO)==false) { //ricerca solo su sacche non prenotate
				Date dataScadenza = current.getDate(ELEMENT_DATASCADENZA);
				if(dataScadenzaImminente.after(dataScadenza) && dataScadenza.after(DateConverter.convertLocalDateToDate(dataArrivoMassima))) {
					dataScadenzaImminente = dataScadenza;
					s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
							GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
							DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAPRODUZIONE)),
							DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATASCADENZA)),
							current.getBoolean(ELEMENT_PRENOTATO));
					selez = s;
				}	
			}	
		}
		setPrenotatoSacca(selez.getSeriale());
		selez.setPrenotato();
		return selez;
	}
	
	
	/**Cerca e restituisce una Sacca compatibile al gruppo ricercato e che non scada entro una dataArrivoMassima, all'interno del database delle sacche
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la sacca non è stata trovata; la Sacca se essa è stata trovata
	 */
	public Sacca getSaccaCompatibilePerRicerca(GruppoSanguigno gs, LocalDate dataArrivoMassima) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
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
					
					if(dataScadenzaImminente.after(dataScadenza) && dataScadenza.before(DateConverter.convertLocalDateToDate(dataArrivoMassima))) {
						dataScadenzaImminente = dataScadenza;
						s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
							GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
							DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAPRODUZIONE)),
							DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATASCADENZA)),
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
	
	
	/** Restituisce una lista contenente tutte le Sacche con scadenza inferiore a 72 ore da oggi
	 * @return la lista delle Sacche in scadenza
	 */
    public List<Sacca> getSaccheEntroScadenza() {
    	MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
        List<Sacca> saccheInScadenza = new ArrayList<Sacca>();
        
        List<Document> sacche = collection.find().into(new ArrayList<Document>());
        Date oggi = new Date();
        long dataScadenza = oggi.getTime();
        long dataScadenza72 = dataScadenza + 259200000;

        for (Document current : sacche){
            if(current.getDate(ELEMENT_DATASCADENZA).getTime()>(dataScadenza) 
            		&& current.getDate(ELEMENT_DATASCADENZA).getTime()<(dataScadenza72)) {
                Sacca s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
                        GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)),
                        DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAPRODUZIONE)),
                        DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATASCADENZA)),
                        current.getBoolean(ELEMENT_PRENOTATO));
                saccheInScadenza.add(s);
            }
        }
        mongoClient.close();
        return saccheInScadenza;
    }
      
    
    /**Restituisce la lista delle Sacche di un determinato Gruppo sanguigno, presenti del database delle Sacche
	 * @param g Gruppo sanguigno delle Sacche che si vogliono ricercare
	 * @return la lista di Sacche di un determinato Gruppo sanguigno
	 */
	public List<Sacca> listaSaccheGS(GruppoSanguigno g){		
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_SACCHE);
	
	    List<Sacca> sacche = new ArrayList<Sacca>();
	    
		for (Document current : collection.find(eq(ELEMENT_GRUPPO,g.toString()))) {
			Sacca s = new Sacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
					GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAPRODUZIONE)), 
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATASCADENZA)),
					current.getBoolean(ELEMENT_PRENOTATO));
		    sacche.add(s);
		}
		mongoClient.close();	
		return sacche;
	}
	
	
	/**Restituisce la lista dei datiSacche che sono transitate per un CTT in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return la lista di sacche che sono transitate per un CTT in un determinato arco temporale
	 */
	public List<DatiSacca> listaDatiSaccheInIntervallo(Date dataInizio, Date dataFine) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
	    List<DatiSacca> datiSacche = new ArrayList<DatiSacca>();
	    
	    Date dataArrivo = null;
    	Date dataAffidamento = null;
	    
	    for (Document current : collection.find()) {
	    	 dataArrivo = current.getDate(ELEMENT_DATAARRIVO);
	    	 dataAffidamento = current.getDate(ELEMENT_DATAAFFIDAMENTO);
	    	     	 
	    	if((dataArrivo.after(dataInizio) && dataArrivo.before(dataFine))
	    			|| (dataAffidamento.after(dataInizio) && dataAffidamento.before(dataFine))) {

			DatiSacca ds = new DatiSacca(new Seriale(current.getString(ELEMENT_SERIALE)), 
					GruppoSanguigno.valueOf(current.getString(ELEMENT_GRUPPO)), 
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAARRIVO)), 
					DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATAAFFIDAMENTO)),
					current.getString(ELEMENT_ENTEDONATORE),
					current.getString(ELEMENT_ENTERICHIEDENTE));
			
	    	datiSacche.add(ds);
	    	}
		}	    
		return datiSacche;
	}
		
	
	/** Modifica il parametro dataArrivo di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della sacca da modificare
	 * @param dataArrivo Data di arrivo aggiornata
	 */
	public void setDataArrivoDatiSacca(Seriale seriale, LocalDate dataArrivo) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(ELEMENT_DATAARRIVO, dataArrivo));
	}
    	
	
	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della Sacca da modificare
	 * @param enteRichiedente Ente a cui sarà affidata la Sacca
	 */
	public void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(ELEMENT_SERIALE, seriale.getSeriale()),
				Updates.set(ELEMENT_ENTERICHIEDENTE, enteRichiedente));
	}

	
	/**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca
	 * @param seriale Seriale della Sacca da ricercare
	 * @param dataAffidamento Data in cui è stata affidata la Sacca
	 */
	public void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DATISACCHE);
		collection.updateOne(
				eq(ELEMENT_SERIALE,seriale.getSeriale()),
				Updates.set(ELEMENT_DATAAFFIDAMENTO,DateConverter.convertLocalDateToDate(dataAffidamento)));

	}

	
	/**Aggiungere un Dipendente al database dei Dipendenti
	 * @param d Dipendente da aggiungere al database dei Dipendenti
	 */
	public void addDipendente(Dipendente d) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    
	    Document unDipendente = new Document(ELEMENT_CDF, d.getCdf().getCodiceFiscale())
                .append(ELEMENT_NOME, d.getNome())
                .append(ELEMENT_COGNOME, d.getCognome())
                .append(ELEMENT_DATADINASCITA, DateConverter.convertLocalDateToDate(d.getDataDiNascita()))
                .append(ELEMENT_RUOLO, d.getRuolo().toString())
                .append(ELEMENT_USERNAME, d.getUsername())
                .append(ELEMENT_PASSWORD, d.getPassword());
	    collection.insertOne(unDipendente);
	      
	}
	
	
	/**Elimina un Dipendente al database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da eliminare
	 */
	public void removeDipendente(Cdf cdf) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    
	    for (Document current : collection.find(eq(ELEMENT_CDF, cdf.getCodiceFiscale()))) {
	    	collection.deleteOne(current);
	    }
	    mongoClient.close();
	}	
	
	
	/**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
	 * @param username Username del Dipendente
	 * @param password Password del Dipendente
	 * @return il Dipendente cercato
	 */
	public Dipendente getDipendente(String username, String password) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	        
	    for (Document current : collection.find(eq(ELEMENT_USERNAME, username))) {
	    	if(password.equals(current.getString(ELEMENT_PASSWORD))) {
	    		Dipendente dip = new Dipendente(new Cdf(current.getString(ELEMENT_CDF)), 
	    				current.getString(ELEMENT_NOME), 
	    				current.getString(ELEMENT_COGNOME),
	    				DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATADINASCITA)),
	    				RuoloDipendente.valueOf(current.getString(ELEMENT_RUOLO)), 
	    				current.getString(ELEMENT_USERNAME),
	    				current.getString(ELEMENT_PASSWORD));
	    		return dip;
	    	}
	    }		
		return null;
	}

	
	/**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return la lista dei Dipendenti del Ruolo scelto
	 */
	public List<Dipendente> getlistaDipendentiByRuolo(RuoloDipendente ruolo) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);

        List<Dipendente> dipendenti = new ArrayList<Dipendente>();

        for (Document current : collection.find(eq(ELEMENT_RUOLO, ruolo.toString()))) {
                Dipendente dip = new Dipendente(new Cdf(current.getString(ELEMENT_CDF)), 
                        current.getString(ELEMENT_NOME), 
                        current.getString(ELEMENT_COGNOME),
                        DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATADINASCITA)),
                        RuoloDipendente.valueOf(current.getString(ELEMENT_RUOLO)),
                        current.getString(ELEMENT_USERNAME),
                        current.getString(ELEMENT_PASSWORD));
               dipendenti.add(dip);
        }
        mongoClient.close();
        return dipendenti;
    }

}