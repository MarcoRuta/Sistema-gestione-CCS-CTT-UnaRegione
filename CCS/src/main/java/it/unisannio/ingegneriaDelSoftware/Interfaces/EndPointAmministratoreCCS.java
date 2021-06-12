package it.unisannio.ingegneriaDelSoftware.Interfaces;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.zip.DataFormatException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTT;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

public interface EndPointAmministratoreCCS {

	/**Aggiunge un nuovo CTT al Database dei CTT
	 * @param provincia La provincia del CTT che si vuole inserire
	 * @param citta La città del CTT che si vuole inserire
	 * @param indirizzo L'indirizzo del CTT che si vuole aggiungere
	 * @param telefono Il telefono del CTT che si vuole aggiungere
	 * @param email L'email del CTT che si vuole aggiungere
	 * @param latitudine La latitudine del CTT che si vuole aggiungere
	 * @param longitudine La longitudine del CTT che si vuole aggiungere
	 * @param uriInfo Informazioni riguardo l'uri
	 * @return Response */
	public Response addCTT(String provincia, String citta, String indirizzo, String telefono, String email, String latitudine, String longitudine, UriInfo uriInfo) throws EntityAlreadyExistsException ;

	
	/**Rimuove un CTT dal Database dei CTT
	 * @param cttName Il nome del CTT da rimuovere
	 * @return Response
	 * @throws EntityNotFoundException, NumberFormatException
	 */
	public Response removeCTT(String cttName) throws EntityNotFoundException, NumberFormatException;

	
	/**Restituisce la lista di tutti i CTT presenti nel Database dei CTT, utilizzato per l' aggiunta automatica dei CTT
	 * @return La lista dei CTT
	 */
	public List<CTT> listaCTT();

	/**Aggiunge un AmministratoreCCS nel Database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da aggiungere al DataBase
	 * @param nome Nome del Dipendente da aggiungere al DataBase
	 * @param cognome Cognome del Dipendente da aggiungere al DataBase
	 * @param dataDiNascita Data di nascita del Dipendente da aggiungere al DataBase
	 * @param username Username del Dipendente da aggiungere al DataBase
	 * @return Response
	 * @throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException
	 */
	public Response addAmministratore(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("username")String username) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException;


	/**Rimuove un Dipendente dal DataBase
	 * @param header Il token di autentificazione
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response
	 * @throws EntityNotFoundException, WebApplicationException
	 */
	public Response removeAmministratore(String header, String cdf) throws EntityNotFoundException;

	
	/**Restituisce la lista di tutti i Dipendenti presenti nel database dei Dipendenti
	 * @param header Il token di autentificazione
	 * @return dipendenti Lista di tutti i Dipendenti
	 * @throws EntityNotFoundException
	 */

	public List<Dipendente> getAmministratori(String header) throws EntityNotFoundException;

	//--------------------REPORT DIPENDENTI RETE CTT--------------------
	/**Restituisce la lista dei Dipendenti di tutti i CTT presenti sulla rete del ruolo selezionato
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response
	 */

	public Response reportDipendentiCCS(String ruolo);

	//--------------------REPORT STATISTICO DEL NUMERO DI SACCHE PRESENTI A LIVELLO REGIONALE PER CIASCUN GRUPPO SANGUIGNO--------------------
	/**Restituisce il numero di sacche presenti di ogni tipo nella regione
	 * @param headers Il token di autentificazione
	 * @return Response
	 */
	public Response reportStatisticoSaccheRegionale(String headers);

	
	//--------------------REPORT SACCHE INVIATE RETE REGIONALE--------------------
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 * @throws DataFormatException
	 */

	public Response reportSaccheInviate(String dataInizio, String dataFine) throws DataFormatException;

	//--------------------REPORT SACCHE RICEVUTE RETE REGIONALE--------------------
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute dalla rete in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 * @throws DataFormatException
	 */

	public Response reportSaccheRicevute(String dataInizio, String dataFine) throws DataFormatException;

	//--------------------REPORT PERMANENZA MEDIA DELLE SACCHE DI SANGUE PER GRUPPO SANGUIGNO A LIVELLO REGIONALE--------------------
	/**Restituisce la giacenza media delle sacche di sangue all'interno dei magazzini dei CTT raggruppate per gruppo sanguigno
	 * @param headers Il token di autentificazione
	 * @return Response
	 */
	public Response giacenzaMediaSaccheRegionale(String headers);

	/**Restituisce una mappa di <String,Boolean>, true se il CTT è online, false altrimenti
	 * @param headers Il token di autentificazione
	 * @return Response
	 * @throws EntityNotFoundException
	 */
	public Response statusReteCtt(String headers) throws EntityNotFoundException;
}