package it.unisannio.ingegneriaDelSoftware.Interfaces;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import org.glassfish.jersey.server.Uri;


public interface EndPointAmministratoreCCS {
	
	/**Aggiunge un nuovo CTT al Database dei CTT
	 * @param numero Il numero identificativo del CTT che si vuole inserire
	 * @param provincia La provincia del CTT che si vuole inserire
	 * @param citta La città del CTT che si vuole inserire
	 * @param indirizzo L'indirizzo del CTT che si vuole aggiungere
	 * @param telefono Il telefono del CTT che si vuole aggiungere
	 * @param email L'email del CTT che si vuole aggiungere
	 * @param latitudine La latitudine del CTT che si vuole aggiungere
	 * @param longitudine La longitudine del CTT che si vuole aggiungere
	 * @return Response */
	public Response addCTT(String provincia,
						   String citta,
						   String indirizzo,
						   String telefono,
						   String email,
						   String latitudine,
						   String longitudine,
						   UriInfo uriInfo) throws EntityAlreadyExistsException;
	
	
	/**Rimuove un CTT dal Database dei CTT
	 * @param numero Il numero identificativo del CTT che si vuole rimuovere
	 * @return Response */
	public Response removeCTT(String numero) throws EntityNotFoundException;
	
	
	/**Restituisce la lista di tutti i CTT presenti nel Database dei CTT, utilizzato per l' aggiunta automatica dei CTT
	 * @return List<CTT> */
	@GET
	@Path("/centers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CTT> listaCTT();
	
	
	/**Aggiunge un AmministratoreCCS nel Database dei Dipendenti
	 * @param cdf Codice fiscale del Dipendente da aggiungere al DataBase
	 * @param nome Nome del Dipendente da aggiungere al DataBase
	 * @param cognome Cognome del Dipendente da aggiungere al DataBase
	 * @param dataDiNascita Data di nascita del Dipendente da aggiungere al DataBase
	 * @param username Username del Dipendente da aggiungere al DataBase
	 * @return Response 
	 */
	public Response addAmministratore(String cdf,
									  String nome,
									  String cognome,
									  String dataDiNascita,
									  String username,
									  UriInfo uriInfo) throws EntityAlreadyExistsException;

	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	public Response removeAmministratore(String headers,String cdf) throws EntityNotFoundException;
	
	
	/**
	 * Restituisce la lista di tutti i Dipendenti presenti nel database dei Dipendenti
	 * @return List<Dipendente> Lista di tutti i Dipendenti
	 */
	public List<Dipendente> getAmministratori(String headers) throws EntityNotFoundException;

}