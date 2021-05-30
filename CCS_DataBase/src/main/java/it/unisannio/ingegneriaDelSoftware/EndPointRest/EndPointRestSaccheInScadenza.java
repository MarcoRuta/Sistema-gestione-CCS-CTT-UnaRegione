package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.GestioneScadenze.NotificaSaccaInScadenza;
import it.unisannio.ingegneriaDelSoftware.GestioneScadenze.SaccheInScadenzaObserver;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointSaccheInScadenzaCCS;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Util.CTTStaticIpMap;


@Path("/CCS")
@Singleton



public class EndPointRestSaccheInScadenza implements EndPointSaccheInScadenzaCCS, Subject{
	
	MongoDataManager mm = MongoDataManager.getInstance();
	Observer cttObserver = new SaccheInScadenzaObserver();

	
	/**Metodo utilizzato da un CTT per aggiungere al database delle sacche in scadenza una lista di sacche in scadenza presenti nel proprio database
	 * @param listaSaccheInScadenza la lista delle sacche in scadenza, in formato JSON
	 * @throws EntityAlreadyExistsException 
	 */
	@POST
	@Path("/saccheInScadenza")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public void aggiungiSaccaInScadenza(Sacca[] listaSaccheInScadenza) throws EntityAlreadyExistsException{
		
		for(Sacca s : new ArrayList<>(Arrays.asList(listaSaccheInScadenza))) mm.createSacca(s);

		this.notifyCTT(this.creaNotificheSaccheInScadenza());
	}




	private List<Notifica> creaNotificheSaccheInScadenza() {
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Notifica> listaNotifiche = new ArrayList<Notifica>();
		for(Sacca s : listaSacche) listaNotifiche.add(new NotificaSaccaInScadenza(s.getSeriale().getSeriale(),s.getDataScadenza(),s.getGruppoSanguigno().toString()));
		return listaNotifiche;
	}

	/**Metodo utilizzato dal un CTT per accettare una delle sacche in scadenza presenti nella rete
	 * L'iterazione del metodo termina con una richiesta di evasione presso il CTT che mantiene tale sacca e una notifica di conferma per il CTT che l'ha richiesta
	 * @throws EntityNotFoundException 
	 */
	@DELETE
	@Path("/prenotaSaccaInScadenza/{seriale}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prenotaSacca(@PathParam("seriale") String seriale,
								 @FormParam("enteRichiedente") String ente_richiedente,
								 @FormParam("indirizzoEnte")String indirizzo) throws EntityNotFoundException {
		
		//Recupero l'indirizzo del CTT che possiede la sacca in scadenza
		System.err.println("seriale:"+seriale);
		String nomeCTT = seriale.substring(0,6);
		String indirizzoCTT = CTTStaticIpMap.indirizziCTT.get(nomeCTT);
		
		//Elimino la sacca dal database SACCHE_IN_SCADENZA del CCS
		Seriale ser = new Seriale();
		ser.setSeriale(seriale);
		mm.removeSacca(ser);
		System.err.println("Sacca con seriale: "+ser.getSeriale()+"eliminata");
	
		//Converto il seriale in modo che coincida con quello accettato dal @POST di notificaevasione
		List<String> listaSeriali = new ArrayList<String>();
		listaSeriali.add(seriale+",");
		
		
		Client client = ClientBuilder.newClient();
		WebTarget evasioneSacca = client.target(indirizzoCTT+"/rest/notifica/notificaEvasione");
		
		NotificaEvasione notifica = new NotificaEvasione(listaSeriali,ente_richiedente,indirizzo);
		
		Response response = evasioneSacca.request().post(Entity.entity(notifica, MediaType.APPLICATION_JSON));
	
		//Aggiorno la lista delle notifiche presenti sui CTT
		this.notifyCTT(this.creaNotificheSaccheInScadenza());
		
		return response;
	
}



	@Override
	public void notifyCTT(List<Notifica> notifiche) {
		this.cttObserver.update(notifiche);
	}
	
}
		


