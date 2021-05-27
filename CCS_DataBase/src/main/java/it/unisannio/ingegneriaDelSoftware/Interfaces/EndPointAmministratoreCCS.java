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

import it.unisannio.ingegneriaDelSoftware.Classes.CTT;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;


public interface EndPointAmministratoreCCS {
	
	/**Aggiunge un nuovo CTT al Database dei CTT
	 * @param numero Il numero identificativo del CTT che si vuole inserire
	 * @param denominazione La denominazione del CTT che si vuole inserire
	 * @param provincia La provincia del CTT che si vuole inserire
	 * @param citta La città del CTT che si vuole inserire
	 * @param indirizzo L'indirizzo del CTT che si vuole aggiungere
	 * @param telefono Il telefono del CTT che si vuole aggiungere
	 * @param email L'email del CTT che si vuole aggiungere
	 * @param latitudine La latitudine del CTT che si vuole aggiungere
	 * @param longitudine La longitudine del CTT che si vuole aggiungere
	 * @return Response */
	@POST
	@Path("/aggiuntaCTT")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCTT(@FormParam("numero_ctt") String numero,
			   		   @FormParam("nome_ctt") String denominazione,
			   	       @FormParam("provincia") String provincia,
			           @FormParam("citta") String citta, 
				       @FormParam("indirizzo") String indirizzo,
				       @FormParam("telefono") String telefono, 
					   @FormParam("email") String email,
					   @FormParam("latitude") String latitudine,
					   @FormParam("longitude") String longitudine);
	
	
	/**Rimuove un CTT dal Database dei CTT
	 * @param numero Il numero identificativo del CTT che si vuole rimuovere
	 * @return Response */
	@DELETE
	@Path("/rimozioneCTT/{numero}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeCTT(@PathParam("numero") String numero) ;	
	
	
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
	 * @param ruolo Ruolo del Dipendente da aggiungere al DataBase
	 * @param username Username del Dipendente da aggiungere al DataBase
	 * @param password Password del Dipendente da aggiungere al DataBase
	 * @return Response 
	 */
	@POST
	@Path("/aggiuntaAmministratore")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username,
								  @FormParam("password")String password);

	
	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	@DELETE
	@Path("/rimozioneAmministratore/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDipendente(@PathParam("cdf") String cdf) ;
	
	
	/**
	 * Restituisce la lista di tutti i Dipendenti presenti nel database dei Dipendenti
	 * @return List<Dipendente> Lista di tutti i Dipendenti
	 */
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> listaDip();

}