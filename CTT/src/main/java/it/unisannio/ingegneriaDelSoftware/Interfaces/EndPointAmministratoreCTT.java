package it.unisannio.ingegneriaDelSoftware.Interfaces;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;

import java.time.format.DateTimeParseException;
import java.util.List;


public interface EndPointAmministratoreCTT {

	/**Aggiunge un Dipendente al DataBase
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome Il nome del Dipendente
	 * @param cognome Il cognome del Dipendente
	 * @param dataDiNascita La data di nascita del Dipendente
	 * @param ruolo Il ruolo del Dipendente
	 * @param username L'username del Dipendente
	 * @return Response
	 * @throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException
	 */
	public Response addDipendente(String cdf, String nome, String cognome, String dataDiNascita, String ruolo, String username) throws DateTimeParseException, IllegalArgumentException, AssertionError, EntityAlreadyExistsException;


	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response 
	 * @throws EntityNotFoundException se si vuole rimuovere un Dipendente non presente nel DB
	 */
	public Response removeDipendente(String header, String cdf) throws EntityNotFoundException;



	 /**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response
	 */
	public Response reportDipendentiCTT(String ruolo);


	 /**Restituisce il numero di Sacche presenti di ogni gruppo sanguigno nel database delle Sacche
	  * @return Response 
	  */
	public Response reportStatisticoSacche(String headers);

	
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 */
	public Response reportLocaleSaccheInviate(String dataInizio, String dataFine) throws DateTimeParseException;

	
	/**Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response
	 */
	public Response reportLocaleSaccheRicevute(String dataInizio, String dataFine) throws DateTimeParseException;

	
	/**Calcola quanto è il tempo medio di giacenza delle Sacche di sangue all'interno del magazzino
	 * @return Response
	 */
	public Response giacenzaMedia();

	
	/**Restituisce la lista di Dipendenti contenuti nel database dei Dipendenti 
	 * @return la lista di Dipendenti che lavorano al CTT
	 * @throws EntityNotFoundException*/
	public List<Dipendente> getDipendenti(String header) throws EntityNotFoundException;


}