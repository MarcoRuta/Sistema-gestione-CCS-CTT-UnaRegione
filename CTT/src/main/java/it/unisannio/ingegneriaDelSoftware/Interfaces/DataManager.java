package it.unisannio.ingegneriaDelSoftware.Interfaces;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface DataManager {

	/**Aggiunge una Sacca al database delle sacche solo se essa non è già presente nel DB delle sacche
     * @param s Sacca da aggiungere al db
     * @throws EntityAlreadyExistsException se la Sacca che si vuole aggiungere è già presente nel DB
     */
    public void createSacca(Sacca s) throws EntityAlreadyExistsException;

	
    /**Aggiunge una DatiSacca al database delle DatiSacche solo se essa non è già presente nel DB dei DatiSacca
     * @param s DatiSacca da aggiungere al db
     * @throws EntityAlreadyExistsException se i DatiSacca sono già presenti nel DB
     */
    public void createDatiSacca(DatiSacca s) throws EntityAlreadyExistsException;

    
    /**Aggiunge un Dipendente al database dei Dipendenti solo se essa non è gia presente nel DB dei Dipendenti
     * @param d Dipendente da aggiungere al db
     * @throws EntityAlreadyExistsException se il Dipendente è gia presente nel DB
     */
    public void createDipendente(Dipendente d) throws EntityAlreadyExistsException;
    
	
	
	
    /**Restituisce una Sacca ricercata sul database dalle Sacche tramite il Seriale
     * @param ser Seriale della Sacca da ricercare
     * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
     * @throws EntityNotFoundException se la Sacca non è presente nel DB
     */
    public Sacca getSacca(Seriale ser) throws EntityNotFoundException;

	
    /**Restituisce una DatiSacca ricercata sul database dalle DatiSacche tramite il Seriale
     * @param ser Seriale della DatiSacca da ricercare
     * @return null se la DatiSacca non è stata trovata; la DatiSacca se essa è stata trovata
     * @throws EntityNotFoundException se i DatiSacca non sono presenti nel DB
     */
    public DatiSacca getDatiSacca(Seriale ser) throws EntityNotFoundException;
	
    
    /**Restituisce un Dipendente ricercato sul database dei Dipendenti tramite il CodiceFiscale
     * @param cdf Codice fiscale del Dipendente da ricercare
     * @return null se il Dipendente non è stato trovato; il Dipendente se è stato trovato
     * @throws EntityNotFoundException se il Dipendente non è presente nel DB
     */
    public Dipendente getDipendente(Cdf cdf) throws EntityNotFoundException;
    
    
    /**Cerca e restituisce un Dipendente con un determinato Username e Password, presente all'interno del database dei Dipendenti
     * @param username Username del Dipendente
     * @param password Password del Dipendente
     * @return Dipendente cercato
     * @throws EntityNotFoundException se il Dipendente non è presente nel DB, oppure se non esiste un Dipendente con quella combinazione di username e password
     */
    public Dipendente getDipendente(String username, String password) throws EntityNotFoundException;
    
    
    
    
    /**Controlla se una Sacca è presente nel database delle Sacche
     * @param seriale Il seriale della Sacca che si vuole cercare
     * @return true se la Sacca è contenuta nel database delle Sacche
     */
    public boolean containsSacca(Seriale seriale);
    
    
    /**Controlla se un DatiSacca è presente nel database dei DatiSacca
     * @param seriale Il seriale del DatiSacca che si vuole cercare
     * @return true se i DatiSacca sono contenuti nel database dei DatiSacca
     */
    public boolean containsDatiSacca(Seriale seriale);
    
    
    /**Controlla se un Dipendente è presente nel database dei Dipendenti
     * @param cdf Il Codice fiscale del Dipendente che si vuole cercare
     * @return true se il Dipendente è contenuto nel database dei Dipendenti
     */
    public boolean containsDipendente(Cdf cdf);
    
    
    
    
    /**Rimuove una Sacca dal DataBase identificata tramite il Seriale, solo se essa è gia presente
     * @param ser Seriale della Sacca da rimuovere dal db delle sacche
     * @throws EntityNotFoundException se la Sacca che si vuole rimuovere non è presente nel DB
     */
    public void removeSacca(Seriale ser) throws EntityNotFoundException;

	
    /**Elimina un Dipendente dal database dei Dipendenti solo se esiste
     * @param cdf Codice fiscale del Dipendente da eliminare dal database dei Dipendenti
     * @throws EntityNotFoundException se il Dipendente che si vuole eliminare non è presente nel DB
     */
    public void removeDipendente(Cdf cdf) throws EntityNotFoundException;
    
    
    
    
    /** Restituisce una lista contenente tutte le Sacche presenti in magazzino
     * @return la lista delle Sacche presenti in magazzino
     */
    public List<Sacca> getListaSacche();
	
	
    /**Restituisce la lista dei datiSacche presenti nel database dei DatiSacca
     * @return la lista dei DatiSacca che sono presenti nel database dei DatiSacca
     */
    public List<DatiSacca> getListaDatiSacche();
	
	
	/**Restituisce la lista dei Dipendenti del CTT presenti nel database dei Dipendenti
     * @return la lista dei Dipendenti del CTT
     */
    public  List<Dipendente> getListaDipendenti();
    
    
    
    
    /**Cambia lo stato di prenotazione di una Sacca identificata tramite il Seriale solo se essa esiste
     * @param seriale Seriale della Sacca da ricercare
     * @throws EntityNotFoundException se la Sacca di cui si vuole aggiornare il Seriale non è presente nel DB
     */
    public void setPrenotatoSacca(Seriale seriale)throws EntityNotFoundException;

	
    /**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param enteRichiedente Ente a cui sarà affidata la Sacca
     * @throws EntityNotFoundException se il DatiSacca che si vuole aggiornare non è presente nel DB
     */
    public void setEnteRichiedenteDatiSacca(Seriale seriale, String enteRichiedente) throws EntityNotFoundException;

	
    /**Modifica il parametro enteRichiedente di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se il DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param dataAffidamento Data in cui è stata affidata la Sacca
     * @throws EntityNotFoundException se il DatiSacca di cui si vuole settare la data di affidamento non è presente nel DB
     */
    public void setDataAffidamentoDatiSacca(Seriale seriale, LocalDate dataAffidamento) throws EntityNotFoundException;

	
    /**Modifica il parametro indirizzoEnte di una DatiSacca identificata tramite Seriale nel database dei DatiSacca solo se DatiSacca esiste
     * @param seriale Seriale della Sacca da modificare
     * @param indirizzoEnte indirizzo dell'ente a cui sarà affidata la Sacca
     * @throws EntityNotFoundException se il DatiSacca di cui si vuole settare l'indirzzo dell'ente non è presente nel DB
     */
    public void setIndirizzoEnteDatiSacca(Seriale seriale, String indirizzoEnte) throws EntityNotFoundException;

	
	/**Modifica la password di un Dipendente all'interno del DB solo se esso esiste
     * @param password la nuova password da aggiungere
     * @param cdf il codice fiscale del Dipendente di cui si vuole aggiornare la password
     * @throws EntityNotFoundException se il Dipendente di cui si vule cambiare la password non è presente nel DB
     * */
    public void setPassword(Cdf cdf, String password) throws EntityNotFoundException;
}