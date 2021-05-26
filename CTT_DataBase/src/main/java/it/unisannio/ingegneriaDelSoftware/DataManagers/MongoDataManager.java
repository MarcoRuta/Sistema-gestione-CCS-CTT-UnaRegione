package it.unisannio.ingegneriaDelSoftware.DataManagers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.DatiSaccaCodec;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.DipendenteCodec;
import it.unisannio.ingegneriaDelSoftware.DataManagers.Codec.SaccaCodec;
import it.unisannio.ingegneriaDelSoftware.Exceptions.*;
import it.unisannio.ingegneriaDelSoftware.Util.*;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDataManager {

    private static MongoClient mongoClient;

    static{
        CodecRegistry pojoCodecRegistry =fromRegistries(
                CodecRegistries.fromCodecs(new SaccaCodec(), new DipendenteCodec(), new DatiSaccaCodec()),
                MongoClient.getDefaultCodecRegistry()
        );
        mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    }

    public static void dropDB() {
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        database.drop();
        mongoClient.close();
    }





    /**Metodo che restituisce una MongoCollection<SaccaBean> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<SaccaBean>*/
    private static MongoCollection<Sacca> getCollectionSacca(){
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        return  database.getCollection(Constants.COLLECTION_SACCHE,Sacca.class);
    }

    /**Metodo che restituisce una MongoCollection<DatiSaccaBean> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<DatiSaccaBean>*/
    private static MongoCollection<DatiSacca> getCollectionDatiSacca(){
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        return database.getCollection(Constants.COLLECTION_DATISACCHE, DatiSacca.class);
    }

    /**Metodo che restituisce una MongoCollection<DipendentiBean> registrando il codec di base di Mongo per la serializzazione in BSON
     * @return MongoCollection<DipendentiBean>*/
    private static MongoCollection<Dipendente> getCollectionDipendente(){
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        return database.getCollection(Constants.COLLECTION_DIPENDENTI, Dipendente.class);
    }





    /**Aggiunge una Sacca al database delle sacche solo se essa non è gia presente nel DB delle sacche
     * @param s Sacca da aggiungere al db
     * @throws SaccaGiaPresenteException se la sacca che si vuole aggiungere è gia presente nel DB
     */
    public static void createSacca(Sacca s) throws SaccaNotFoundException{
        if (containsSacca(s.getSeriale())) throw new EntityGiaPresenteException("Sacca con seriale"+s.getSeriale().getSeriale()+"gia presente nel DB");
        MongoCollection<Sacca> collection = getCollectionSacca();
        collection.insertOne(s);
    }

    /**Aggiunge una DatiSacca al database delle DatiSacche solo se essa non è gia presente nel DB delle sacche
     * @param s DatiSacca da aggiungere al db
     */
    public static void createDatiSacca(DatiSacca s) {
        if(containsDatiSacca(s.getSeriale())) throw new EntityGiaPresenteException("Dati Sacca con seriale"+s.getSeriale().getSeriale() +" già presente nel DB");
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        collection.insertOne(s);
    }

    /**Aggiunge un Dipendente al database dei Dipendenti solo se essa non è gia presente nel DB dei Dipendenti
     * @param d Dipendente da aggiungere al db
     */
    public static void createDipendente(Dipendente d) {
        if (containsDipendente(d.getCdf())) throw new EntityGiaPresenteException("Dipendente con cdf "+d.getCdf().getCodiceFiscale()+" gia presente nel DB");
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        collection.insertOne(d);
    }





    /**Restituisce una Sacca ricercata sul database dalle Sacche tramite il Seriale
     * @param ser Seriale della Sacca da ricercare
     * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
     * @throws SaccaNotFoundException
     */
    public static Sacca getSacca(Seriale ser) throws SaccaNotFoundException {
        MongoCollection<Sacca> collection = getCollectionSacca();
       Sacca unaSacca =collection.find(eq(Constants.ELEMENT_SERIALE, ser.getSeriale())).first();
       if (unaSacca != null)
           return unaSacca;
        throw new SaccaNotFoundException("La sacca ricercata"+ ser.getSeriale() +"non è stata trovata");
    }

    /**Restituisce una DatiSacca ricercata sul database dalle DatiSacche tramite il Seriale
     * @param ser Seriale della DatiSacca da ricercare
     * @return null se la DatiSacca non è stata trovata; la DatiSacca se essa è stata trovata
     * @throws DatiSaccaNotFoundException
     */
    public static DatiSacca getDatiSacca(Seriale ser) throws DatiSaccaNotFoundException {
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        DatiSacca unDatiSacca = collection.find(eq(Constants.ELEMENT_SERIALE,ser.getSeriale())).first();
        if (unDatiSacca != null)
            return unDatiSacca;
        throw new DatiSaccaNotFoundException("Il DatiSacca ricercato"+ ser.getSeriale() +"non è stato trovato");
    }

    /**Restituisce un Dipendete ricercato sul database dei Dipendenti tramite il CodiceFiscale
     * @param cdf Codice fiscale del Dipendente da ricercare
     * @return null se il Dipendente non è stato trovato; il Dipendente se è stato trovato
     * @throws DipendenteNotFoundException
     */
    public static Dipendente getDipendente(Cdf cdf) throws DipendenteNotFoundException{
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        Dipendente unDipendente = collection.find(eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale())).first();
        if(unDipendente != null)
            return unDipendente;
        throw new DipendenteNotFoundException("Il dipendente ricercato"+ cdf.getCodiceFiscale() +"non è stata trovato");
    }

    /**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
     * @param username Username del Dipendente
     * @param password Password del Dipendente
     * @return il Dipendente cercato
     */
    public static Dipendente getDipendente(String username, String password) throws DipendenteNotFoundException{
        MongoCollection<Dipendente> collection = getCollectionDipendente();

       Dipendente unDipendente = collection.find(and(eq(Constants.ELEMENT_USERNAME, username),
               eq(Constants.ELEMENT_PASSWORD,password))).first();
       if(unDipendente != null)
           return  unDipendente;
        throw new DipendenteNotFoundException("Impossibile trovare il dipendente. Username o Password errati");
    }





    /**@param seriale il seriale della sacca che si vuole cercare
     * @return true se la sacca è contenuta
     */
    public static boolean containsSacca(Seriale seriale) {
        try {
            Sacca unSacca = getSacca(seriale);
            return unSacca != null?true:false;
        }catch(SaccaNotFoundException e) {
            return false;
        }
    }

    /**@param seriale il seriale della sacca che si vuole cercare
     * @return true se i dati sacca sono contenuti*/
    public static boolean containsDatiSacca(Seriale seriale){
        try {
            DatiSacca unDatiSacca = getDatiSacca(seriale);
            return unDatiSacca != null?true:false;
        }catch(DatiSaccaNotFoundException e) {
            return false;
        }
    }

    /**@param cdf il codice fiscale del dipendente che si vuole cercare
     * @return true se la sacca è contenuta*/
    public static boolean containsDipendente(Cdf cdf){
        try {
            Dipendente unDipendente = getDipendente(cdf);
            return unDipendente != null?true:false;
        }catch (DipendenteNotFoundException e) {
            return false;
        }
    }





    /**Rimuove una Sacca dal DataBase identificata tramite il Seriale, solo se essa è gia presente
     * @param ser Seriale della sacca da rimuovere dal db delle sacche
     * @throws SaccaNotFoundException se la sacca che si vuole rimuovere non è presente nel DB
     */
    public static void removeSacca(Seriale ser) throws SaccaNotFoundException{
        if(!containsSacca(ser)) throw new SaccaNotFoundException("Non è possibile rimuovere una sacca non presente nel DB.\nSeriale sacca: "+ser.getSeriale());
        MongoCollection<Sacca> collection =getCollectionSacca();
        collection.deleteOne(eq(Constants.ELEMENT_SERIALE,ser.getSeriale()));
    }

    /**Elimina un Dipendente al database dei Dipendenti solo se siste
     * @param cdf Codice fiscale del Dipendente da eliminare
     * @throws DipendenteNotFoundException se il dipendente che si vuole eliminare non è presente nel DB
     */
    public static void removeDipendente(Cdf cdf) throws DipendenteNotFoundException{
        if (!containsDipendente(cdf)) throw new DipendenteNotFoundException("Non è possibile rimuovere un Dipendente che non è presente nel db.\nCodiceFiscale del dipendente: "+cdf.getCodiceFiscale());
        MongoCollection<Dipendente> collection = getCollectionDipendente();
       collection.deleteOne(eq(Constants.ELEMENT_CDF, cdf.getCodiceFiscale()));
    }





    /**Restituisce la lista dei Dipendenti del CTT presenti nel database
     * @return la lista dei Dipendenti del CTT
     */
    public static List<Dipendente> getListaDipendenti(){
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        List<Dipendente> dipendenti = new ArrayList<>();
        for(Dipendente unDipendente : collection.find())
            dipendenti.add(unDipendente);
        return dipendenti;
    }

    /** Restituisce una lista contenente tutte le Sacche presenti in magazzino
     * @return la lista delle Sacche presenti in magazzino
     */
    public static List<Sacca> getListaSacche(){
        MongoCollection<Sacca> collection = getCollectionSacca();
        List<Sacca> sacche = new ArrayList<Sacca>();

        for (Sacca unaSacca : collection.find())
            sacche.add(unaSacca);
        return sacche;
    }

    /**Restituisce la lista dei datiSacche presenti nel database
     * @return la lista dei DatiSacca che sono presenti nel database
     */
    public static List<DatiSacca> getListaDatiSacche(){
        MongoCollection<DatiSacca> collection =getCollectionDatiSacca();
        List<DatiSacca> datiSacche = new ArrayList<DatiSacca>();

        for (DatiSacca unDatiSacca : collection.find())
            datiSacche.add(unDatiSacca);
        return datiSacche;
    }





    /**Cambia lo stato di prenotazione di una Sacca identificata tramite il Seriale solo se essa esiste
     * @param seriale Seriale della Sacca da ricercare
     * @throws SaccaNotFoundException se la sacca di cui si vuole aggiornare il valore non è presente nel DB
     */
    public static void setPrenotatoSacca(Seriale seriale)throws SaccaNotFoundException{
        if (!containsSacca(seriale)) throw new SaccaNotFoundException("Non puoi aggiornare lo stato di una sacca che non è presente nel DB. Seriale della sacca: "+seriale.getSeriale());
        MongoCollection<Sacca> collection = getCollectionSacca();

        collection.updateOne(
                eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
                set(Constants.ELEMENT_PRENOTATO, true));
    }


    /**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param enteRichiedente Ente a cui sarà affidata la Sacca
     * @throws DatiSaccaNotFoundException se il DatiSacca che si vuole aggiornare non è presente nel DB
     */
    public static void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente) throws DatiSaccaNotFoundException{
        if(!containsDatiSacca(seriale)) throw new DatiSaccaNotFoundException("Non puoi settare l'ente richiedente di un DatiSacca non presente nel DB. Seriale del DatiSacca: "+seriale.getSeriale());
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        collection.updateOne(
                eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),
                set(Constants.ELEMENT_ENTERICHIEDENTE, enteRichiedente));
    }

    /**Modifica il parametro indirizzoEnte di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param indirizzoEnte indirizzo dell'ente a cui sarà affidata la Sacca
     * @throws DatiSaccaNotFoundException se il DatiSacca di cui si vuole settare l'indiirzzo dell'ente non è presente nel DB
     */
    public static void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzoEnte) throws DatiSaccaNotFoundException{
        if(!containsDatiSacca(seriale)) throw new DatiSaccaNotFoundException("Non puoi cambiare l'indirizzo dell'ente richiedente di una sacca non presente nel DB. Seriale del DatiSacca: "+seriale.getSeriale());
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        collection.updateOne(
                eq(Constants.ELEMENT_SERIALE, seriale.getSeriale()),
                set(Constants.ELEMENT_INDIRIZZOENTE, indirizzoEnte));
    }

    /**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se il DatiSacca esiste
     * @param seriale Seriale della Sacca da ricercare
     * @param dataAffidamento Data in cui è stata affidata la Sacca
     * @throws DatiSaccaNotFoundException se il DatiSacca di cui si vuole settare la data di affidamento non è presente nel DB
     */
    public static void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) throws DatiSaccaNotFoundException{
        if (!containsDatiSacca(seriale)) throw new DatiSaccaNotFoundException("Non puoi cambiare la data di affidamento di una sacca non presente nel DB. Seriale del DatiSacca: "+seriale.getSeriale());
        MongoCollection<DatiSacca> collection = getCollectionDatiSacca();
        collection.updateOne(
                eq(Constants.ELEMENT_SERIALE,seriale.getSeriale()),
                set(Constants.ELEMENT_DATAAFFIDAMENTO, DateTimeFormatter.ISO_LOCAL_DATE.format(dataAffidamento)));
    }

    /**Modifica la password di un Dipendente all'interno del DB solo se esso esiste
     * @param password la nuova passworda da aggiungere
     * @param cdf  il codice fiscale del Dipendente di cui si vuole aggiornare la password
     * @throws DipendenteNotFoundException se il dipendente di cui si vule cambiare la password non è presente nel DB
     * */
    public static void setPassword(Cdf cdf, String password) throws DipendenteNotFoundException {
        if (!containsDipendente(cdf)) throw new DipendenteNotFoundException( "Non puoi cambiare la password di un utente non presente nel DB. Codice Fiscale del dipendente: "+cdf.getCodiceFiscale());
        MongoCollection<Dipendente> collection = getCollectionDipendente();
        collection.updateOne(
                eq(Constants.ELEMENT_CDF,cdf.getCodiceFiscale()),
                set(Constants.ELEMENT_PASSWORD, password ));
    }







}
