package it.unisannio.ingegneriaDelSoftware.DataManagers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.CTTCodec;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.DipendenteCodec;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.SaccaCodec;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDataManager {
	
	private MongoClient mongoClient;
	private static MongoDataManager instance = new MongoDataManager();

	/**
	 * Metodo che offre un'istanza di un MongoDataManager
	 * @return Istanza di un MongoDataManager
	 */
	public static MongoDataManager getInstance() {
		return instance;
	}
	
	/**
	 * Metodo costruttore del MongoClient
	 */
	private MongoDataManager(){
	    CodecRegistry pojoCodecRegistry = fromRegistries(CodecRegistries.fromCodecs(new DipendenteCodec(), new CTTCodec(), new SaccaCodec()), MongoClient.getDefaultCodecRegistry());
	    mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
	}
	
	/**
	 * Metodo che rimuove un database
	 */
	public void dropDB() {
	    MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
	    database.drop();
	}

	/**
	 * Metodo che droppa la collezione delle sacche in scadenza, utilizzato per evitare inconsistenze quando il CCS viene riavviato
	 */
	public void dropSaccheInScadenza(){
		MongoCollection<Sacca> sacche = getCollectionSacca();
		sacche.drop();
	}

	/**
	 * Restituisce una MongoCollection<SaccaBean> registrando il codec di base di Mongo per la serializzazione in BSON
	 * @return MongoCollection<SaccaBean>
	 */
	private MongoCollection<Sacca> getCollectionSacca(){
	    MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
	    return  database.getCollection(Settings.COLLECTION_SACCHE, Sacca.class);
	}
	
	/**
	 * Restituisce una MongoCollection<CTT> registrando il codec di base di Mongo per la serializzazione in BSON
	 * @return MongoCollection<CTT>
	 */
	private MongoCollection<CTT> getCollectionCTT(){
	    MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
	    return  database.getCollection(Settings.COLLECTION_CTT, CTT.class);
	}
	
	/**
	 * Restituisce una MongoCollection<Dipendente> registrando il codec di base di Mongo per la serializzazione in BSON
	 * @return MongoCollection<Dipendente>
	 */
	private MongoCollection<Dipendente> getCollectionDipendente(){
		MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
	    return database.getCollection(Settings.COLLECTION_DIPENDENTI, Dipendente.class);
	}
	
	/**
	 * Aggiunge una Sacca al database delle sacche solo se essa non è gia presente nel DB delle sacche
	 * @param s Sacca da aggiungere al db
	 * @throws EntityAlreadyExistsException se la Sacca che si vuole aggiungere è già presente nel DB
	 */
	public void createSacca(Sacca s) throws EntityAlreadyExistsException {
	    if (containsSacca(s.getSeriale())) throw new EntityAlreadyExistsException("Sacca con seriale"+s.getSeriale()+"gia presente nel DB");
	    MongoCollection<Sacca> collection = getCollectionSacca();
	    collection.insertOne(s);
	}
	
	/**
	 * Aggiunge un CTT al database dei CTT solo se esso non è gia presente nel DB dei CTT
	 * @param ctt CTT da aggiungere al db
	 * @throws EntityAlreadyExistsException se il CTT che si vuole aggiungere è già presente nel DB
	 */
	public void createCTT(CTT ctt) throws EntityAlreadyExistsException {
	    if (containsCTT(ctt.getDenominazione())) throw new EntityAlreadyExistsException("CTT con numero "+ctt.getDenominazione()+" già presente nel DB");
	    MongoCollection<CTT> collection = getCollectionCTT();
	    collection.insertOne(ctt);
	}
	
	/**
	 * Aggiunge un Dipendente al database dei Dipendenti solo se essa non è gia presente nel DB dei Dipendenti
	 * @param d Dipendente da aggiungere al db
	 * @throws EntityAlreadyExistsException se il Dipedente che si vuole aggiungere è già presente nel DB
	 */
	public void createDipendente(Dipendente d) throws EntityAlreadyExistsException {
	    if (containsDipendente(d.getCdf())) throw new EntityAlreadyExistsException("Dipendente con cdf "+d.getCdf().getCodiceFiscale()+" già presente nel DB");
	    MongoCollection<Dipendente> collection = getCollectionDipendente();
	    collection.insertOne(d);
	}
	
	/**
	 * Restituisce una Sacca ricercata sul database dalle Sacche tramite il Seriale
	 * @param ser Seriale della Sacca da ricercare
	 * @return SaccaBean la sacca se è presente nel database
	 * @throws EntityNotFoundException se l'entità non è presente nel DB
	 */
	public Sacca getSacca(Seriale ser) throws EntityNotFoundException {
	   MongoCollection<Sacca> collection = getCollectionSacca();
	   Sacca unaSacca =collection.find(eq(Constants.ELEMENT_SERIALE, ser.getSeriale())).first();
	   if (unaSacca != null) return unaSacca;
	   throw new EntityNotFoundException("La sacca ricercata"+ ser +"non è stata trovata");
	}
	
	/**
	 * Restituisce un CTT ricercato all'interno del database dei CTT tramite il suo numero
	 * @return CTT Il CTT ricercato
	 * @throws EntityNotFoundException se l'entità non è presente nel DB
	 */
	public CTT getCTT(CTTName cttName) throws EntityNotFoundException {
	   MongoCollection<CTT> collection = getCollectionCTT();
	   CTT unCTT =collection.find(eq(Constants.ELEMENT_DENOMINAZIONE, cttName.getCttname())).first();
	   if (unCTT != null) return unCTT;
	   throw new EntityNotFoundException("Il CTT ricercato "+ cttName.getCttname() +" non è stato trovato");
	}
	
	/**
	 * Restituisce un Dipendente ricercato all'interno del database dei Dipendenti tramite il CodiceFiscale
	 * @param cdf Codice fiscale del Dipendente da ricercare
	 * @return Dipendente Il Dipendente se è stato trovato
	 * @throws EntityNotFoundException se l'entità non è presente nel DB
	 */
	public Dipendente getDipendente(Cdf cdf) throws EntityNotFoundException{
	    MongoCollection<Dipendente> collection = getCollectionDipendente();
	    Dipendente unDipendente = collection.find(eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale())).first();
	    if(unDipendente != null) return unDipendente;
	    throw new EntityNotFoundException("Il dipendente ricercato "+ cdf.getCodiceFiscale() +" non è stata trovato");
	}
	
	/**
	 * Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
	 * @param username Username del Dipendente
	 * @param password Password del Dipendente
	 * @return Dipendente il Dipendente cercato se presente
	 * @throws EntityNotFoundException se l'entità non è presente nel DB
	 */
	public Dipendente getDipendente(String username, String password) throws EntityNotFoundException{
	   MongoCollection<Dipendente> collection = getCollectionDipendente();
	
	   Dipendente unDipendente = collection.find(and(eq(Constants.ELEMENT_USERNAME, username),
			   eq(Constants.ELEMENT_PASSWORD, DigestUtils.sha256Hex(password)+"CryptoCTT"))).first();
	   if(unDipendente != null) return unDipendente;
	   throw new EntityNotFoundException("Impossibile trovare il dipendente. Username o Password errati");
	}
	
	/**
	 * Metodo che verifica se una sacca è presente o meno nel database
	 * @param seriale Il seriale della Sacca che si vuole cercare
	 * @return true se la Sacca è contenuta nel database delle Sacche, false altrimenti
	 */
	public  boolean containsSacca(Seriale seriale) {
	    try {
	        getSacca(seriale);
	        return true;
	    }catch(EntityNotFoundException e) {
	        return false;
	    }
	}
	
	/**
	 * Controlla se il CTT selezionato tramite il numero è presente nel Database dei CTT
	 * @param cttName il nome del ctt da ricercare nel database
	 * @return true se il CTT è presente nel database dei CTT, false se il CTT non è presente
	 */
	private boolean containsCTT(CTTName cttName) {
		try {
	        getCTT(cttName);
	        return true;
	    }catch (EntityNotFoundException e) {
	        return false;
	    }
	}
	
	/**
	 * Controlla se il Dipendente selezionato tramite Codice fiscale è presente nel Database dei Dipendenti
	 * @param cdf il codice fiscale del dipendente che si vuole cercare
	 * @return true se il dipendente è presente nel database dei Dipendenti, false se il Dipendente non è presente
	 */
	public boolean containsDipendente(Cdf cdf){
	    try {
	        getDipendente(cdf);
	        return true;
	    }catch (EntityNotFoundException e) {
	        return false;
	    }
	}

	/**
	 * Rimuove una Sacca dal DataBase identificata tramite il Seriale, solo se essa è gia presente
	 * @param ser Seriale della Sacca da rimuovere dal database delle sacche
	 * @throws EntityNotFoundException se la Sacca che si vuole rimuovere non è presente nel database
	 */
	public  void removeSacca(Seriale ser) throws EntityNotFoundException{
	    if(!containsSacca(ser)) throw new EntityNotFoundException("Non è possibile rimuovere una sacca non presente nel database.\nSeriale sacca: "+ser);
	    MongoCollection<Sacca> collection =getCollectionSacca();
	    collection.deleteOne(eq(Constants.ELEMENT_SERIALE,ser.getSeriale()));
	}
	
	/**
	 * Rimuove un CTT dal database dei CTT solo se esiste e partendo dal suo numero
	 * @param cttName il nome del ctt da rimuovere
	 * @throws EntityNotFoundException se il CTT che si vuole eliminare non è presente nel database
	 */
	public void removeCTT(CTTName cttName) throws EntityNotFoundException{
		if(!containsCTT(cttName)) throw new EntityNotFoundException("Non è possibile rimuovere un CTT non presente nel database. "+cttName.getCttname());
	    MongoCollection<CTT> collection = getCollectionCTT();
	    collection.deleteOne(eq(Constants.ELEMENT_DENOMINAZIONE, cttName.getCttname()));
	}
	
	/**
	 * Rimuove un Dipendente al database dei Dipendenti solo se siste
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal Database dei Dipendenti
	 * @throws EntityNotFoundException se il dipendente che si vuole eliminare non è presente nel database
	 */
	public void removeDipendente(Cdf cdf) throws EntityNotFoundException{
	   if (!containsDipendente(cdf)) throw new EntityNotFoundException("Non è possibile rimuovere un Dipendente che non è presente nel database.\nCodiceFiscale del dipendente: "+cdf.getCodiceFiscale());
	   MongoCollection<Dipendente> collection = getCollectionDipendente();
	   collection.deleteOne(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale()));
	}
	
	/**
	 * Restituisce una lista contenente tutte le Sacche presenti in magazzino
	 * @return la lista delle Sacche presenti in magazzino
	 */
	public  List<Sacca> getListaSacche(){
	    MongoCollection<Sacca> collection = getCollectionSacca();
	    List<Sacca> sacche = new ArrayList<Sacca>();
	
	    for (Sacca unaSacca : collection.find()) sacche.add(unaSacca);
	    return sacche;
	}
	
	/**
	 * Restituisce la lista di tutti i CTT presenti nel database del CCS
	 * @return List<CTT> Lista di tutti i CTT
	 */
	public List<CTT> getListaCTT() {
	    MongoCollection<CTT> collection = getCollectionCTT();
        List<CTT> Ctts = new ArrayList<>();
        for(CTT ctt : collection.find()) Ctts.add(ctt);
        return Ctts;
   
    }
	       
	 /**
	 * Restituisce la lista di tutti i Dipendenti del CTT presenti nel database dei Dipendenti
	 * @return List<Dipendente> La lista dei Dipendenti del CTT
	 */
	public List<Dipendente> getListaDipendenti(){
	    MongoCollection<Dipendente> collection = getCollectionDipendente();
	    List<Dipendente> dipendenti = new ArrayList<>();
	    for(Dipendente unDipendente : collection.find()) dipendenti.add(unDipendente);
	    return dipendenti;
	}
	
	/**
	 * Modifica la password di un Dipendente selezionato tramite Codice fiscale all'interno del DB solo se esso esiste
	 * @param password la nuova password da aggiungere
	 * @param cdf il codice fiscale del Dipendente di cui si vuole aggiornare la password
	 * @throws EntityNotFoundException se il dipendente di cui si vule cambiare la password non è presente nel DB dei Dipendenti
	 */
	public void setPassword(Cdf cdf, String password) throws EntityNotFoundException {
	   if (!containsDipendente(cdf)) throw new EntityNotFoundException( "Non puoi cambiare la password di un utente non presente nel DB. Codice Fiscale del dipendente: "+cdf.getCodiceFiscale());
	   Dipendente unDipendente = getDipendente(cdf);
	   unDipendente.setPassword(password);
	   getCollectionDipendente().replaceOne(eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale()), unDipendente);
	}

	/**
	 * Rimuovo le sacche in scadenza appartenenti ad un dato CTT quando va offline
	 * @throws EntityNotFoundException se il CTT non viene trovato
	 */
	public void removeSaccheCttOffline(CTTName cttOffline) throws EntityNotFoundException {
		List<Sacca> listaSacche = this.getListaSacche();
		for(Sacca s : listaSacche)
			if(s.getSeriale().getSeriale().substring(0,6).equals(cttOffline.getCttname())) {
				this.removeSacca(s.getSeriale());
			}
	}


}