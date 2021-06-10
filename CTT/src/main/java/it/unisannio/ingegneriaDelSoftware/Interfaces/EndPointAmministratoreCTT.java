package it.unisannio.ingegneriaDelSoftware.Interfaces;


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
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param ruolo
	 * @param username
	 * @return
	 */
	public Response addDipendente(String cdf,
								  String nome, String cognome,
								  String dataDiNascita,
								  String ruolo,
								  String username,
								  UriInfo uriInfo) throws EntityAlreadyExistsException;


	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	public Response removeDipendente( String header,String cdf) throws EntityNotFoundException;


	/**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return la lista dei Dipendenti del Ruolo scelto
	 */
	public Response reportDipendentiCTT(String ruolo);


	/** Restituisce il numero di sacche presenti di ogni tipo nel database
	 * @return Response 200 OK e invia la lista dei datiSacca
	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	public Response reportStatisticoSacche(String headers);

	/** Restituisce la lista dei DatiSacche relativi alle sacche che sono state affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response 200 OK e invia la lista dei datiSacca
	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	public Response reportLocaleSaccheInviate(String dataInizio,
											  String dataFine) throws DateTimeParseException;

	/** Restituisce la lista dei DatiSacche relativi alle sacche che sono state ricevute in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response 200 OK e invia la lista dei datiSacca
	 * @return 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	public Response reportLocaleSaccheRicevute(String dataInizio,
											   String dataFine) throws DateTimeParseException;

	/**---------REPORT PERMANENZA MEDIA PER TIPO DI SANGUE------------
	 *
	 * @return Response 200 OK e invia la mappa key: gruppoSanguigno, value: permanenza media in giorni 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	public Response giacenzaMedia();

	/**Metodo tramite il quale è possibile accedere alla lista di dipendenti che lavorano al CTT
	 * @return  la lista di dipendenti che lavorano al CTT*/
	public List<Dipendente> getDipendenti(String headers) throws EntityNotFoundException;

	/**Metodo tramite il quale è possibile recuperare un pdf con cdf, username e password di un dipendente
	 * @param cdf il cdf del dipendente di cui si vogliono recuperare i dati
	 * @return StreamingOutput
	 * */
	public StreamingOutput getPDF(String cdf) throws EntityNotFoundException;
}