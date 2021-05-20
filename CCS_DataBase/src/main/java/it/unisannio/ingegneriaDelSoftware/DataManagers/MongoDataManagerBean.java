package it.unisannio.ingegneriaDelSoftware.DataManagers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.unisannio.ingegneriaDelSoftware.Beans.DipendenteBean;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.CTTNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Util.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDataManagerBean {

    private static MongoClient mongoClient;

    static{
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    }

    public static void dropDB() {
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        database.drop();
        mongoClient.close();
    }


    /**Metodo che restituisce una MongoCollection<SaccaBean> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<SaccaBean>*/
    private static MongoCollection<CTT> getCollectionCTT(){
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        return  database.getCollection(Constants.COLLECTION_CTT, CTT.class);
    }

    
    /**Metodo che restituisce una MongoCollection<DipendentiBean> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<DipendentiBean>*/
    private static MongoCollection<DipendenteBean> getCollectionDipendenteBean(){
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        return database.getCollection(Constants.COLLECTION_DIPENDENTI, DipendenteBean.class);
    }


    
    
    /**Aggiunge un CTT al database dei CTT solo se esso non è gia presente nel DB dei CTT
     * @param s CTT da aggiungere al db
     */
    public static void createCTT(CTT s) {
//        if (containsSacca(s.getNumero())) throw new AssertionError("CTT gia presente nel DB");
        MongoCollection<CTT> collection = getCollectionCTT();
        collection.insertOne(s);
    }

    
    /**Aggiunge un Dipendente al database dei Dipendenti solo se essa non è gia presente nel DB dei Dipendenti
     * @param d Dipendente da aggiungere al db
     */
    public static void createDipendente(Dipendente d) {
        if (containsDipendente(d.getCdf())) throw new AssertionError("Dipendente gia presente");
        MongoCollection<DipendenteBean> collection = getCollectionDipendenteBean();
        collection.insertOne(DipendenteConverter.convertDipendenteToDipendenteBean(d));
    }

  
    
    

	/**
	 * Metodo che permette di cercare un CTT all'interno del database
	 * @param numero Numero del CTT di cui si vogliono ottenere i dati
	 * @return CTT Il CTT ricercato
	 * @throws CTTNotFoundException 
	 * 
	 */
    public static CTT getCTT(int numero) throws CTTNotFoundException {
        MongoCollection<CTT> collection = getCollectionCTT();
       CTT unCTT =collection.find(eq(Constants.ELEMENT_NUMERO, numero)).first();
       if (unCTT != null)
           return unCTT;
        throw new CTTNotFoundException("Il CTT ricercato"+ numero +"non è stato trovato");
    }


	/**Restituisce un Dipendete ricercato sul database dei Dipendenti tramite il CodiceFiscale
     * @param cdf Codice fiscale del Dipendente da ricercare
     * @return null se il Dipendente non è stato trovato; il Dipendente se è stato trovato
     * @throws DipendenteNotFoundException
     */
    public static Dipendente getDipendente(Cdf cdf) throws DipendenteNotFoundException{
        MongoCollection<DipendenteBean> collection = getCollectionDipendenteBean();
        DipendenteBean unBean = collection.find(eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale())).first();
        if(unBean != null)
            return DipendenteConverter.convertDipendenteBeanToDipendente(unBean);
        throw new DipendenteNotFoundException("Il dipendente ricercato"+ cdf.getCodiceFiscale() +"non è stata trovato");
    }

    
    /**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
     * @param username Username del Dipendente
     * @param password Password del Dipendente
     * @return il Dipendente cercato
     * @throws DipendenteNotFoundException 
     */	
	public static Dipendente getDipendente(String username, String password) throws DipendenteNotFoundException {
		   MongoCollection<DipendenteBean> collection = getCollectionDipendenteBean();

	       DipendenteBean unBean = collection.find(and(eq(Constants.ELEMENT_USERNAME, username),
	               eq(Constants.ELEMENT_PASSWORD,password))).first();
	       if(unBean != null)
	           return  DipendenteConverter.convertDipendenteBeanToDipendente(unBean);
	        throw new DipendenteNotFoundException("Impossibile trovare il dipedenten associato a username: "+username +" password:"+password);
	   }
	
	
	
	
	private static boolean containsCTT(int numero) {
		try {
            CTT unCTT = getCTT(numero);
            return unCTT != null?true:false;
        }catch (CTTNotFoundException e) {
            return false;
        }
	}

	
    /**@param cdf il codice fiscale del dipendente che si vuole cercare
     * @return true se la sacca è contenuta*/
    private static boolean containsDipendente(Cdf cdf){
        try {
            Dipendente unDipendente = getDipendente(cdf);
            return unDipendente != null?true:false;
        }catch (DipendenteNotFoundException e) {
            return false;
        }
    }
    
    
    
    
	/**
	 * Metodo che permette di rimuovere un CTT dal database
	 * @param numero Numero del CTT da rimuovere dal database
	 */
	public static void removeCTT(int numero) {
		if(!containsCTT(numero)) throw new AssertionError("Non è possibile rimuovere una sacca non presente nel DB");
        MongoCollection<CTT> collection = getCollectionCTT();
        collection.deleteOne(eq(Constants.ELEMENT_NUMERO, numero));
	}

	
	   /**Elimina un Dipendente al database dei Dipendenti solo se siste
     * @param cdf Codice fiscale del Dipendente da eliminare
     */
    public static void removeDipendente(Cdf cdf){
        if (!containsDipendente(cdf)) throw new AssertionError("Dipendente non presente nel db");
        MongoCollection<DipendenteBean> collection = getCollectionDipendenteBean();
       collection.deleteOne(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale()));
    }
    
    
    
    
    /**
	 * Metodo che restituisce la lista di tutti i CTT presenti nel database del CCS
	 * @return Lista di tutti i CTT
	 * 
	 */
	public static List<CTT> getListaCTT() {
	    MongoCollection<CTT> collection = getCollectionCTT();
        List<CTT> Ctts = new ArrayList<>();
        for(CTT ctt : collection.find())
            Ctts.add(ctt);
        return Ctts;
   
    }
    
   

	   /**Restituisce la lista dei Dipendenti del CTT presenti nel database
     * @return la lista dei Dipendenti del CTT
     */
    public static List<Dipendente> getListaDipendenti(){
        MongoCollection<DipendenteBean> collection = getCollectionDipendenteBean();
        List<Dipendente> dipendenti = new ArrayList<>();
        for(DipendenteBean bean : collection.find())
            dipendenti.add(DipendenteConverter.convertDipendenteBeanToDipendente(bean));
        return dipendenti;
    }
	
	
 

    /**Modifica la password di un Dipendente all'interno del DB solo se esso esiste
     * @param password la nuova passworda da aggiungere
     * @param cdf  il codice fiscale del Dipendente di cui si vuole aggiornare la password
     * */
    public static void setPassword(Cdf cdf, String password) throws DipendenteNotFoundException {
        if (!containsDipendente(cdf)) throw new AssertionError( "Non puoi cambiare la password di un utente non esistente");
        MongoCollection<DipendenteBean> collection = getCollectionDipendenteBean();
        collection.updateOne(
                eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale()),
                set(Constants.ELEMENT_PASSWORD, password ));
    }


	
}