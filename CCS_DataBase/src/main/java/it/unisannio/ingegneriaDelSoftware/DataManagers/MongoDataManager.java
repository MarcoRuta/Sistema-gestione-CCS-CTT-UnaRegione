package it.unisannio.ingegneriaDelSoftware.DataManagers;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;

import org.bson.Document;

public class MongoDataManager implements DataManager {


	private static MongoClient mongoClient;
	
	static {
		mongoClient = new MongoClient();
	}
	
	/**
	 * Metodo che istanzia un Client per accedere al database
	 * 
	 */
	public MongoDataManager() {
		mongoClient = new MongoClient();
	}

	
	/**
	 *Metodo che elimina un database
	 * 
	 */
	public void dropDB() {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        database.drop();
        mongoClient.close();
    }

	/**
	 * Metodo che permettere di aggiungere un CTT al database
	 * 
	 * @param c il CTT da aggiungere al database
	 * 
	 */
	public void createCTT(CTT c) {
		if(this.containsCTT(c.getNumero())) throw new AssertionError("Non è possibile aggiungere un CTT avente lo stesso numero di uno gia registrato");
	    MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_CTT);
	    
	    Document ctt = new Document(Constants.ELEMENT_NUMERO, c.getNumero())
                .append(Constants.ELEMENT_DENOMINAZIONE, c.getDenominazione())
                .append(Constants.ELEMENT_PROVINCIA, c.getPosizione().getProvincia())
                .append(Constants.ELEMENT_CITTA, c.getPosizione().getCitta())
                .append(Constants.ELEMENT_INDIRIZZO, c.getPosizione().getIndirizzo())
                .append(Constants.ELEMENT_TELEFONO, c.getTelefono())
                .append(Constants.ELEMENT_EMAIL, c.getEmail())
                .append(Constants.ELEMENT_LATITUDINE, c.getPosizione().getLatitudine())
                .append(Constants.ELEMENT_LONGITUDINE, c.getPosizione().getLongitudine());
                
	    collection.insertOne(ctt);
	}

	/**
	 * Metodo che permette di rimuovere un CTT dal database
	 * 
	 * @param numero Numero del CTT da rimuovere dal database
	 * 
	 * 
	 */
	public void removeCTT(int numero){
		
		if(!this.containsCTT(numero)) throw new AssertionError("Non è possibile rimuovere un CTT non presente nel database");
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
		MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_CTT);
		    
		for (Document current : collection.find(eq(Constants.ELEMENT_NUMERO,numero)))
		    collection.deleteOne(current);
	}

	/**
	 * Metodo che permette di cercare un CTT all'interno del database
	 * 
	 * @param numero Numero del CTT di cui si vogliono ottenere i dati
	 * 
	 * @return CTT Il CTT ricercato
	 * @throws CTTNotFoundException 
	 * 
	 */
	public CTT getCTT(int numero) throws CTTNotFoundException {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_CTT);
	    CTT c= null;
	    
	    for (Document current : collection.find(eq(Constants.ELEMENT_NUMERO, numero))) {
	    	c = new CTT(current.getInteger(Constants.ELEMENT_NUMERO), 
	    			current.getString(Constants.ELEMENT_DENOMINAZIONE), 
	    			current.getString(Constants.ELEMENT_PROVINCIA),
	    			current.getString(Constants.ELEMENT_CITTA),
	    			current.getString(Constants.ELEMENT_TELEFONO),
	    			current.getString(Constants.ELEMENT_INDIRIZZO), 
	    			current.getString(Constants.ELEMENT_EMAIL),
	    			current.getDouble(Constants.ELEMENT_LATITUDINE),
	    			current.getDouble(Constants.ELEMENT_LONGITUDINE));
	    	
	    	return c;
	    }	
	    
	   throw new CTTNotFoundException("Non esiste nessun CTT con questo numero"); 
	}

	
	/**
	 * Metodo che restituisce la lista di tutti i CTT presenti nel database del CCS
	 * @return Lista di tutti i CTT
	 * 
	 */
	public List<CTT> getListaCTT() {
    	MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_CTT);

        List<CTT> listaCTT = new ArrayList<CTT>();
        List<Document> ctt = collection.find().into(new ArrayList<Document>());
        

        for (Document current : ctt){
            
                CTT c = new CTT(current.getInteger(Constants.ELEMENT_NUMERO),
                		current.getString(Constants.ELEMENT_DENOMINAZIONE),
                		current.getString(Constants.ELEMENT_PROVINCIA),
                		current.getString(Constants.ELEMENT_CITTA),
                		current.getString(Constants.ELEMENT_INDIRIZZO),
                		current.getString(Constants.ELEMENT_TELEFONO),
                		current.getString(Constants.ELEMENT_EMAIL),
                		current.getDouble(Constants.ELEMENT_LATITUDINE),
                		current.getDouble(Constants.ELEMENT_LONGITUDINE));
                
                listaCTT.add(c);
            
        }
        return listaCTT;
    }
	
	/**
	 * Metodo che restituisce la lista di tutti i dipendenti presenti nel database del CCS
	 * @return Lista di tutti i dipendenti
	 * 
	 */
	public List<Dipendente> getListaDip() {
    	MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);

        List<Dipendente> listaDip = new ArrayList<Dipendente>();
        List<Document> ctt = collection.find().into(new ArrayList<Document>());
        

        for (Document current : ctt){
            
        		Dipendente d = new Dipendente(new Cdf(current.getString(Constants.ELEMENT_CDF)), 
	    				current.getString(Constants.ELEMENT_NOME), 
	    				current.getString(Constants.ELEMENT_COGNOME),
	    				DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
	    				RuoloDipendente.valueOf(current.getString(Constants.ELEMENT_RUOLO)), 
	    				current.getString(Constants.ELEMENT_USERNAME),
	    				current.getString(Constants.ELEMENT_PASSWORD));

                listaDip.add(d);
            
        }
        return listaDip;
    }
	
	
	/**
	 * Metodo che restituisce un dipendente presente nel database
	 * 
	 * @param username Username del dipendente da cercare
	 * @param password Password del dipendente da cercare
	 * 
	 * @return Dipendente cercato
	 */
	public Dipendente getDipendente(String username, String password) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	    Dipendente dip = null;
	    
	    for (Document current : collection.find(eq(Constants.ELEMENT_USERNAME, username))) {
	    	if(password.equals(current.getString(Constants.ELEMENT_PASSWORD))) {
	    		dip = new Dipendente(new Cdf(current.getString(Constants.ELEMENT_CDF)), 
	    				current.getString(Constants.ELEMENT_NOME), 
	    				current.getString(Constants.ELEMENT_COGNOME),
	    				DateConverter.convertDateToLocalDate(current.getDate(Constants.ELEMENT_DATADINASCITA)),
	    				RuoloDipendente.valueOf(current.getString(Constants.ELEMENT_RUOLO)), 
	    				current.getString(Constants.ELEMENT_USERNAME),
	    				current.getString(Constants.ELEMENT_PASSWORD));
	    	}
	    }
	    return dip;
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

    /**
	 * *Metodo che aggiunge il dipendente al database
	 * 
	 * @param dip Dipendente da aggiungere
	 * 
	 */
	public void addDipendente(Dipendente dip) {
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	    
	    Document unDipendente = new Document(Constants.ELEMENT_CDF, dip.getCdf().getCodiceFiscale())
                .append(Constants.ELEMENT_NOME, dip.getNome())
                .append(Constants.ELEMENT_COGNOME, dip.getCognome())
                .append(Constants.ELEMENT_DATADINASCITA, DateConverter.convertLocalDateToDate(dip.getDataDiNascita()))
                .append(Constants.ELEMENT_RUOLO, dip.getRuolo().toString())
                .append(Constants.ELEMENT_USERNAME, dip.getUsername())
                .append(Constants.ELEMENT_PASSWORD, dip.getPassword());
	    collection.insertOne(unDipendente);
		
	}
	
	/**Elimina un Dipendente al database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da eliminare
	 */
	public void removeDipendente(String cdf) {
		assert this.containsDipendente(new Cdf(cdf)):"Non puoi eliminare un dipendente non presente nel DB";
		MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_DIPENDENTI);
	    
	    for (Document current : collection.find(eq(Constants.ELEMENT_CDF, cdf)))
	    	collection.deleteOne(current);
	}	
	
	/**@param numero il numero del CTT che si vuole cercare
     * @return true se il CTT è contenuto
	 */
    public boolean containsCTT(Integer numero) {
    	try {	
	        CTT ctt = this.getCTT(numero);
	        return true;
    	}catch(CTTNotFoundException e) {
    		return false;
    	}
    }
    

    /**@param cdf il codice fiscale del dipendente che si vuole cercare
     * @return true se la sacca è contenuta*/
    public boolean containsDipendente(Cdf cdf){
        Dipendente unDipendente = this.getDipendente(cdf);
        return unDipendente != null?true:false;
    }
	
}