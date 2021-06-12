package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import it.unisannio.ingegneriaDelSoftware.CcsRestApplication;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.CTTName;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.SaccheInScadenza.SaccheInScadenzaObserver;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.NotificaSaccaInScadenzaMaker;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointSaccheInScadenzaCCS;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Notifica;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Observer;
import it.unisannio.ingegneriaDelSoftware.Interfaces.Subject;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

@Path("/CCS")
@Singleton
public class EndPointRestSaccheInScadenza implements EndPointSaccheInScadenzaCCS, Subject{
	
	MongoDataManager mm = MongoDataManager.getInstance();
	Observer cttObserver = new SaccheInScadenzaObserver();

	/**Accetta una delle sacche in scadenza presenti nella rete
	 * L'iterazione del metodo termina con una richiesta di evasione presso il CTT che mantiene tale Sacca e una notifica di conferma per il CTT che l'ha richiesta
	 * @param seriale Seriale della sacca
	 * @param indirizzo Indirizzo ente richiedente
	 * @param ente_richiedente Ente richiedente
	 * @return Response
	 * @throws EntityNotFoundException
	 */
	@DELETE
	@Path("/prenotaSaccaInScadenza/{seriale}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response prenotaSacca(@PathParam("seriale") String seriale,
								 @QueryParam("enteRichiedente") String ente_richiedente,
								 @QueryParam("indirizzoEnte") String indirizzo) throws EntityNotFoundException {

		//Recupero l'indirizzo del CTT che possiede la sacca in scadenza
		String nomeCTT = seriale.substring(0,6);
		String indirizzoCTT = Settings.ip.get(mm.getCTT(CTTName.getCttName(nomeCTT)));
		CcsRestApplication.logger.info("Ecco IP del CTT che possiede la sacca: "+ indirizzoCTT);

		//Elimino la sacca dal database SACCHE_IN_SCADENZA del CCS
		Seriale ser = new Seriale();
		ser.setSeriale(seriale);
		mm.removeSacca(ser);
	
		//Converto il seriale in modo che coincida con quello accettato dal @POST di notificaevasione
		List<Seriale> listaSeriali = new ArrayList<Seriale>();
		listaSeriali.add(ser);

		Client client = ClientBuilder.newClient();
		WebTarget evasioneSacca = client.target("http://"+indirizzoCTT+":"+Settings.PORTA+"/rest/notifica/notificaEvasione");
		
		NotificaEvasione notifica = new NotificaEvasione(listaSeriali,ente_richiedente,indirizzo, "La tua sacca è stata richiesta da un CTT");
		CcsRestApplication.logger.info("Ho creato la notifica evasione Sacca che sto per inoltrare al CTT che possiede la sacca: "+notifica);
		Response response = evasioneSacca.request().post(Entity.entity(notifica, MediaType.APPLICATION_JSON));
		//Aggiorno la lista delle notifiche presenti sui CTT
		this.notifyCTT(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());
		
		if (response.getStatus() == 204){
			return Response.status(Response.Status.OK).entity("Sacca In Scadenza Prenotata Correttamente").header("Access-Control-Allow-Origin","*").build();
		} else return response;
	
	}

	/**Accetto il seriale di una sacca, precedentemente inviata al CCS perchè in scadenza, che è stata prenotata pressoil CTT mittente.
	 * La lista delle sacche in scadenza viene aggiornata ed inoltrata a tutti i ctt connessi
	 * @param seriale Seriale della sacca da prenotare
	 * @return Response
	 * @throws EntityNotFoundException
	 */
	@DELETE
	@Path("/ritiroAlertCTT/{seriale}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ritiroAlertCTTSacca(@PathParam("seriale") String seriale) throws EntityNotFoundException {

		Seriale ser = new Seriale();
		ser.setSeriale(seriale);
		mm.removeSacca(ser);
		CcsRestApplication.logger.info("Alert ritirato correttamente per la sacca con seriale: "+seriale);

		//Aggiorno la lista delle notifiche presenti sui CTT
		this.notifyCTT(NotificaSaccaInScadenzaMaker.creaNotificheSaccheInScadenza());

		return Response.status(Response.Status.OK).entity("Alert Ritirato").build();
	}

	@Override
	public void notifyCTT(List<Notifica> notifiche) {
		this.cttObserver.update(notifiche);
		CcsRestApplication.logger.info("Sto per inoltrare le sacche in scadenza ai CTT");
	}	
}