package it.unisannio.ingegneriaDelSoftware.DataManagers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.DatiSaccaCodec;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.DipendenteCodec;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.SaccaCodec;
import it.unisannio.ingegneriaDelSoftware.Exceptions.*;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Singleton
public class MongoDataManager implements DataManager {

    /**The mongoClient instance represents a pool of connections to the database;you will
     * only need one instance of class MongoClient even with multiple threads*/
    private  MongoClient mongoClient;


    /**Singleton instance*/
    private static MongoDataManager instance = new MongoDataManager();

    public static MongoDataManager getInstance(){
        return instance;
    }

    /**Costruisce un' istanza di mongoClient*/
   private MongoDataManager(){
        CodecRegistry pojoCodecRegistry =fromRegistries(
                CodecRegistries.fromCodecs(new SaccaCodec(), new DipendenteCodec(), new DatiSaccaCodec()),
                MongoClient.getDefaultCodecRegistry()
        );
        mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    }

    /**Rimuove il CTT DataBase*/
    public void dropDB() {
        MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
        database.drop();
        Seriale.restartSettings();
    }
    
    
    

    /**Restituisce una MongoCollection<Sacca> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<Sacca>*/
    private MongoCollection<Sacca> getCollectionSacca(){
        MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
        return  database.getCollection(Settings.COLLECTION_SACCHE,Sacca.class);
    }

    /**Restituisce una MongoCollection<DatiSacca> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<DatiSacca>*/
    private MongoCollection<DatiSacca> getCollectionDatiSacca(){
        MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
        return database.getCollection(Settings.COLLECTION_DATISACCHE, DatiSacca.class);
    }

    /**Restituisce una MongoCollection<Dipendenti> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<Dipendenti>*/
    private MongoCollection<Dipendente> getCollectionDipendente(){
        MongoDatabase database = mongoClient.getDatabase(Settings.DB_NAME);
        return database.getCollection(Settings.COLLECTION_DIPENDENTI, Dipendente.class);
    }

    
    
    
    /**Aggiunge una Sacca al database delle sacche solo se essa non è gia presente nel DB delle sacche
     * @param s Sacca da aggiungere al db
     * @throws EntityAlreadyExistsException se la Sacca che si vuole aggiungere è già presente nel DB
     */
    public void createSacca(Sacca s) throws EntityAlreadyExistsException {
        if (containsSacca(s.getSeriale())) throw new EntityAlreadyExistsException("Sacca con seriale"+s.getSeriale().getSeriale()+"gia presente nel DB");
        MongoCollection<Sacca> collection = getCollectionSacca();
        collection.insertOne(s);
    }

    /**Aggiunge una DatiSacca al database delle DatiSacche solo se esso non è già presente nel DB delle sacche
     * @param s DatiSacca da aggiungere al db
     * @throws EntityAlreadyExistsException se l'entita è già presente nel DB
     */
    public void createDatiSacca(DatiSacca s) throws EntityAlreadyExistsException {
        if(containsDatiSacca(s.getSeriale())) throw new EntityAlreadyExistsException("Dati Sacca con seriale"+s.getSeriale().getSeriale() +" già presente nel DB");
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        collection.insertOne(s);
    }

    /**Aggiunge un Dipendente al database dei Dipendenti solo se esso non è gia presente nel DB dei Dipendenti, criptando la password
     * @param d Dipendente da aggiungere al db
     * @throws EntityAlreadyExistsException se l'entità è gia presente nel DB
     */
    public void createDipendente(Dipendente d) throws EntityAlreadyExistsException {
        if (containsDipendente(d.getCdf())) throw new EntityAlreadyExistsException("Dipendente con cdf "+d.getCdf().getCodiceFiscale()+" gia presente nel DB");
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        collection.insertOne(d);
    }
    
    
    
    
    /**Restituisce una Sacca ricercata sul database dalle Sacche tramite il Seriale
     * @param ser Seriale della Sacca da ricercare
     * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
     * @throws EntityNotFoundException se la Sacca non è presente nel DB
     */
    public Sacca getSacca(Seriale ser) throws EntityNotFoundException {
        MongoCollection<Sacca> collection = getCollectionSacca();
       Sacca unaSacca =collection.find(eq(Constants.ELEMENT_SERIALE, ser.getSeriale())).first();
       if (unaSacca != null)
           return unaSacca;
        throw new EntityNotFoundException("La sacca ricercata"+ ser.getSeriale() +"non è stata trovata");
    }

    
    /**Restituisce una DatiSacca ricercata sul database dalle DatiSacche tramite il Seriale
     * @param ser Seriale della DatiSacca da ricercare
     * @return null se la DatiSacca non è stata trovata; la DatiSacca se essa è stata trovata
     * @throws EntityNotFoundException se i DatiSacca non sono presenti nel DB
     */
    public DatiSacca getDatiSacca(Seriale ser) throws EntityNotFoundException {
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        DatiSacca unDatiSacca = collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale())).first();
        if (unDatiSacca != null)
            return unDatiSacca;
        throw new EntityNotFoundException("Il DatiSacca ricercato"+ ser.getSeriale() +"non è stato trovato");
    }

    
    /**Restituisce un Dipendente ricercato sul database dei Dipendenti tramite il CodiceFiscale
     * @param cdf Codice fiscale del Dipendente da ricercare
     * @return null se il Dipendente non è stato trovato; il Dipendente se è stato trovato
     * @throws EntityNotFoundException se il Dipendente non è presente nel DB
     */
    public Dipendente getDipendente(Cdf cdf) throws EntityNotFoundException{
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        Dipendente unDipendente = collection.find(eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale())).first();
        if(unDipendente != null)
            return unDipendente;
        throw new EntityNotFoundException("Il dipendente ricercato"+ cdf.getCodiceFiscale() +"non è stata trovato");
    }

    
    /**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
     * @param username Username del Dipendente
     * @param password Password del Dipendente
     * @return unDipendente Dipendente cercato
     * @throws EntityNotFoundException se il Dipendente non è presente nel DB, oppure se non esiste un Dipendente con quella combinazione di username e password
     */
    public Dipendente getDipendente(String username, String password) throws EntityNotFoundException{
        MongoCollection<Dipendente> collection = getCollectionDipendente();

        Dipendente unDipendente = collection.find(and(eq(Constants.ELEMENT_USERNAME,username),
                eq(Constants.ELEMENT_PASSWORD, DigestUtils.sha256Hex(password)+"CryptoCTT"))).first();
        if(unDipendente != null)
            return unDipendente;

        throw new EntityNotFoundException("Impossibile trovare il dipendente. Username o Password errati");
    }

    
    
    
    /**Rimuove una Sacca dal DataBase identificata tramite il Seriale, solo se essa è gia presente
     * @param ser Seriale della Sacca da rimuovere dal db delle sacche
     * @throws EntityNotFoundException se la Sacca che si vuole rimuovere non è presente nel DB
     */
    public void removeSacca(Seriale ser) throws EntityNotFoundException{
        if(!containsSacca(ser)) throw new EntityNotFoundException("Non è possibile rimuovere una sacca non presente nel DB.\nSeriale sacca: "+ser.getSeriale());
        MongoCollection<Sacca> collection =getCollectionSacca();
        collection.deleteOne(eq(Constants.ELEMENT_SERIALE,ser.getSeriale()));
    }

    /**Elimina un Dipendente al database dei Dipendenti solo se esiste
     * @param cdf Codice fiscale del Dipendente da eliminare
     * @throws EntityNotFoundException se il Dipendente che si vuole eliminare non è presente nel DB
     */
    public void removeDipendente(Cdf cdf) throws EntityNotFoundException{
        if (!containsDipendente(cdf)) throw new EntityNotFoundException("Non è possibile rimuovere un Dipendente che non è presente nel db.\nCodiceFiscale del dipendente: "+cdf.getCodiceFiscale());
        MongoCollection<Dipendente> collection = getCollectionDipendente();
       collection.deleteOne(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale()));
    }



    
    /**Restituisce la lista dei Dipendenti del CTT presenti nel database dei Dipendenti
     * @return la lista dei Dipendenti del CTT
     */
    public List<Dipendente> getListaDipendenti(){
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        List<Dipendente> dipendenti = new ArrayList<>();
        for(Dipendente unDipendente : collection.find())
            dipendenti.add(unDipendente);
        return dipendenti;
    }

    
    /** Restituisce una lista contenente tutte le Sacche nel database delle sacche
     * @return la lista delle Sacche presenti in magazzino
     */
    public List<Sacca> getListaSacche(){
        MongoCollection<Sacca> collection = getCollectionSacca();
        List<Sacca> sacche = new ArrayList<Sacca>();

        for (Sacca unaSacca : collection.find())
            sacche.add(unaSacca);
        return sacche;
    }

    
    /**Restituisce la lista dei datiSacche presenti nel database dei DatiSacca
     * @return la lista dei DatiSacca che sono presenti nel database dei DatiSacca
     */
    public  List<DatiSacca> getListaDatiSacche(){
        MongoCollection<DatiSacca> collection =getCollectionDatiSacca();
        List<DatiSacca> datiSacche = new ArrayList<DatiSacca>();

        for (DatiSacca unDatiSacca : collection.find())
            datiSacche.add(unDatiSacca);
        return datiSacche;
    }

    
    
    
    /**Controlla se una Sacca è presente nel database delle Sacche
     * @param seriale Il seriale della Sacca che si vuole cercare
     * @return true se la Sacca è contenuta nel database delle Sacche
     */
    private boolean containsSacca(Seriale seriale) {
        try {
            getSacca(seriale);
            return true;
        }catch(EntityNotFoundException e) {
            return false;
        }
    }

    
    /**Controlla se un DatiSacca è presente nel database dei DatiSacca
     * @param seriale Il seriale del DatiSacca che si vuole cercare
     * @return true se i DatiSacca sono contenuti nel database dei DatiSacca
     */
    private boolean containsDatiSacca(Seriale seriale){
        try {
            getDatiSacca(seriale);
            return true;
        }catch(EntityNotFoundException e) {
            return false;
        }
    }

    
    /**Controlla se un Dipendente è presente nel database dei Dipendenti
     * @param cdf Il Codice fiscale del Dipendente che si vuole cercare
     * @return true se il Dipendente è contenuto nel database dei Dipendenti
     */
    private boolean containsDipendente(Cdf cdf){
        try {
            getDipendente(cdf);
            return true;
        }catch (EntityNotFoundException e) {
            return false;
        }
    }

    
    


    /**Cambia lo stato di prenotazione di una Sacca identificata tramite il Seriale solo se essa esiste
     * @param seriale Seriale della Sacca da ricercare
     * @throws EntityNotFoundException se la Sacca di cui si vuole aggiornare il Seriale non è presente nel DB
     */
    public void setPrenotatoSacca(Seriale seriale)throws EntityNotFoundException{
        if (!containsSacca(seriale)) throw new EntityNotFoundException("Non puoi aggiornare lo stato di una sacca che non è presente nel DB. Seriale della sacca: "+seriale.getSeriale());
        Sacca sacca = getSacca(seriale);
        sacca.setPrenotato();
        getCollectionSacca().replaceOne(eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()), sacca);
    }


    /**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param enteRichiedente Ente a cui sarà affidata la Sacca
     * @throws EntityNotFoundException se il DatiSacca che si vuole aggiornare non è presente nel DB
     */
    public void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente) throws EntityNotFoundException{
        if(!containsDatiSacca(seriale)) throw new EntityNotFoundException("Non puoi settare l'ente richiedente di un DatiSacca non presente nel DB. Seriale del DatiSacca: "+seriale.getSeriale());
        DatiSacca datiSacca = getDatiSacca(seriale);
        datiSacca.setEnteRichiedente(enteRichiedente);
        getCollectionDatiSacca().replaceOne( eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()), datiSacca);
    }

    
    /**Modifica il parametro indirizzoEnte di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param indirizzoEnte indirizzo dell'ente a cui sarà affidata la Sacca
     * @throws EntityNotFoundException se il DatiSacca di cui si vuole settare l'indiirzzo dell'ente non è presente nel DB
     */
    public void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzoEnte) throws EntityNotFoundException{
        if(!containsDatiSacca(seriale)) throw new EntityNotFoundException("Non puoi cambiare l'indirizzo dell'ente richiedente di una sacca non presente nel DB. Seriale del DatiSacca: "+seriale.getSeriale());
        DatiSacca datiSacca = getDatiSacca(seriale);
        datiSacca.setIndirizzoEnte(indirizzoEnte);
        getCollectionDatiSacca().replaceOne(eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),datiSacca);
    }

    
    /**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se il DatiSacca esiste
     * @param seriale Seriale della Sacca da ricercare
     * @param dataAffidamento Data in cui è stata affidata la Sacca
     * @throws EntityNotFoundException se il DatiSacca di cui si vuole settare la data di affidamento non è presente nel DB
     */
    public void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) throws EntityNotFoundException{
        if (!containsDatiSacca(seriale)) throw new EntityNotFoundException("Non puoi cambiare la data di affidamento di una sacca non presente nel DB. Seriale del DatiSacca: "+seriale.getSeriale());
        DatiSacca datiSacca = getDatiSacca(seriale);
        datiSacca.setDataAffidamento(dataAffidamento);
        getCollectionDatiSacca().replaceOne( eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()), datiSacca);
    }

    /**Modifica la password di un Dipendente all'interno del DB solo se esso esiste
     * @param password la nuova passworda da aggiungere
     * @param cdf  il codice fiscale del Dipendente di cui si vuole aggiornare la password
     * @throws EntityNotFoundException se il dipendente di cui si vuole cambiare la password non è presente nel DB
     * */
    public void setPassword(Cdf cdf, String password) throws EntityNotFoundException {
        if (!containsDipendente(cdf)) throw new EntityNotFoundException( "Non puoi cambiare la password di un utente non presente nel DB. Codice Fiscale del dipendente: "+cdf.getCodiceFiscale());
        Dipendente unDipendente = this.getDipendente(cdf);
        unDipendente.setPassword(password);
        getCollectionDipendente().replaceOne(eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale()), unDipendente);
    }

}