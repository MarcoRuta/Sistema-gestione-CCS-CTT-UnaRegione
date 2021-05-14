package it.unisannio.ingegneriaDelSoftware.DataManagers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;
import org.bson.Document;

public class MyMongoDataManager implements DataManager {
	private static final String DB_NAME = "CCS";
	
	private static final String COLLECTION_CTT = "CTT";
	private static final String ELEMENT_NUMERO = "numero";
	private static final String ELEMENT_DENOMINAZIONE = "denominazione";
	private static final String ELEMENT_PROVINCIA = "provincia";
	private static final String ELEMENT_CITTA = "città";
	private static final String ELEMENT_INDIRIZZO = "indirizzo";
	private static final String ELEMENT_TELEFONO = "telefono";
	private static final String ELEMENT_EMAIL = "e_mail";
	private static final String ELEMENT_LATITUDINE = "latitudine";
	private static final String ELEMENT_LONGITUDINE = "longitudine";
	
	private static final String COLLECTION_DIPENDENTI = "DIPENDENTI";
	private static final String ELEMENT_CDF = "cdf";
	private static final String ELEMENT_NOME = "nome";
	private static final String ELEMENT_COGNOME = "cognome";
	private static final String ELEMENT_DATADINASCITA = "dataDiNascita";
	private static final String ELEMENT_RUOLO = "ruolo";
	private static final String ELEMENT_USERNAME = "username";
	private static final String ELEMENT_PASSWORD = "password";

	private static MongoClient mongoClient;
	
	static {
		mongoClient = new MongoClient();
	}
	
	/**
	 * Metodo che istanzia un Client per accedere al database
	 * 
	 */
	public MyMongoDataManager() {
		mongoClient = new MongoClient();
	}

	
	/**
	 *Metodo che elimina un database
	 * 
	 */
	public void dropDB() {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
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
	    MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_CTT);
	    
	    Document ctt = new Document(ELEMENT_NUMERO, c.getNumero())
                .append(ELEMENT_DENOMINAZIONE, c.getDenominazione())
                .append(ELEMENT_PROVINCIA, c.getPosizione().getProvincia())
                .append(ELEMENT_CITTA, c.getPosizione().getCittà())
                .append(ELEMENT_INDIRIZZO, c.getPosizione().getIndirizzo())
                .append(ELEMENT_TELEFONO, c.getTelefono())
                .append(ELEMENT_EMAIL, c.getEmail())
                .append(ELEMENT_LATITUDINE, c.getPosizione().getLatitudine())
                .append(ELEMENT_LONGITUDINE, c.getPosizione().getLongitudine());
                
	    collection.insertOne(ctt);
	}

	/**
	 * Metodo che permette di rimuovere un CTT dal database
	 * 
	 * @param numero Numero del CTT da rimuovere dal database
	 * 
	 */
	public void removeCTT(int numero) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = database.getCollection(COLLECTION_CTT);
		    
		for (Document current : collection.find(eq(ELEMENT_NUMERO,numero)))
		    collection.deleteOne(current);
	}

	/**
	 * Metodo che permette di cercare un CTT all'interno del database
	 * 
	 * @param numero Numero del CTT di cui si vogliono ottenere i dati
	 * 
	 * @return CTT Il CTT ricercato
	 * 
	 */
	public CTT getCTT(int numero) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_CTT);
	    CTT c= null;
	    
	    for (Document current : collection.find(eq(ELEMENT_NUMERO, numero))) {
	    	c = new CTT(current.getInteger(ELEMENT_NUMERO), 
	    			current.getString(ELEMENT_DENOMINAZIONE), 
	    			current.getString(ELEMENT_PROVINCIA),
	    			current.getString(ELEMENT_CITTA),
	    			current.getString(ELEMENT_TELEFONO),
	    			current.getString(ELEMENT_INDIRIZZO), 
	    			current.getString(ELEMENT_EMAIL),
	    			current.getDouble(ELEMENT_LATITUDINE),
	    			current.getDouble(ELEMENT_LONGITUDINE));
	    }		
	    return c;
	}

	
	/**
	 * Metodo che restituisce la lista di tutti i CTT presenti nel database del CCS
	 * @return Lista di tutti i CTT
	 * 
	 */
	public List<CTT> getListaCTT() {
    	MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_CTT);

        List<CTT> listaCTT = new ArrayList<CTT>();
        List<Document> ctt = collection.find().into(new ArrayList<Document>());
        

        for (Document current : ctt){
            
                CTT c = new CTT(current.getInteger(ELEMENT_NUMERO),
                		current.getString(ELEMENT_DENOMINAZIONE),
                		current.getString(ELEMENT_PROVINCIA),
                		current.getString(ELEMENT_CITTA),
                		current.getString(ELEMENT_INDIRIZZO),
                		current.getString(ELEMENT_TELEFONO),
                		current.getString(ELEMENT_EMAIL),
                		current.getDouble(ELEMENT_LATITUDINE),
                		current.getDouble(ELEMENT_LONGITUDINE));
                
                listaCTT.add(c);
            
        }
        return listaCTT;
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
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    Dipendente dip = null;
	    
	    for (Document current : collection.find(eq(ELEMENT_USERNAME, username))) {
	    	if(password.equals(current.getString(ELEMENT_PASSWORD))) {
	    		dip = new Dipendente(new Cdf(current.getString(ELEMENT_CDF)), 
	    				current.getString(ELEMENT_NOME), 
	    				current.getString(ELEMENT_COGNOME),
	    				DateConverter.convertDateToLocalDate(current.getDate(ELEMENT_DATADINASCITA)),
	    				RuoloDipendente.valueOf(current.getString(ELEMENT_RUOLO)), 
	    				current.getString(ELEMENT_USERNAME),
	    				current.getString(ELEMENT_PASSWORD));
	    	}
	    }
	    return dip;
}

	/*
	 * *Metodo che aggiunge il dipendente al database
	 * 
	 * @param dip Dipendente da aggiungere
	 * 
	 */
	public void addDipendente(Dipendente dip) {
		MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	    MongoCollection<Document> collection = database.getCollection(COLLECTION_DIPENDENTI);
	    
	    Document unDipendente = new Document(ELEMENT_CDF, dip.getCdf().getCodiceFiscale())
                .append(ELEMENT_NOME, dip.getNome())
                .append(ELEMENT_COGNOME, dip.getCognome())
                .append(ELEMENT_DATADINASCITA, DateConverter.convertLocalDateToDate(dip.getDataDiNascita()))
                .append(ELEMENT_RUOLO, dip.getRuolo().toString())
                .append(ELEMENT_USERNAME, dip.getUsername())
                .append(ELEMENT_PASSWORD, dip.getPassword());
	    collection.insertOne(unDipendente);
		
	}
	
}