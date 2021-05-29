package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointSaccheInScadenzaCCS;
import it.unisannio.ingegneriaDelSoftware.Util.CTTStaticIpMap;


@Path("/CCS")
@Singleton



public class EndPointRestSaccheInScadenza implements EndPointSaccheInScadenzaCCS{
	
	MongoDataManager mm = MongoDataManager.getInstance();

	
	/**Metodo utilizzato da un CTT per aggiungere al database delle sacche in scadenza una lista di sacche in scadenza presenti nel proprio database
	 * @param listaSacche la lista delle sacche in scadenza, in formato JSON
	 * @throws EntityAlreadyExistsException 
	 */
	@POST
	@Path("/saccheInScadenza")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public void aggiungiSaccaInScadenza(@RequestBody Sacca[] listaSaccheInScadenza) throws EntityAlreadyExistsException{
	
		System.err.println(listaSaccheInScadenza[0]);
		
		
		for(Sacca s : new ArrayList<>(Arrays.asList(listaSaccheInScadenza))) {
			s.setGruppo(GruppoSanguigno.Ap);
			System.err.println("i'm going to insert");
			mm.createSacca(s);
			System.err.println("agg fatt");
			
		}
			
			
		
	
	}
	
	/**Metodo utilizzato dall'endpoint operatore CTT per ricavare la lista delle sacche in scadenza presenti nella rete
	 * @return la lista delle sacche in scadenza presenti in rete
	 */
	@GET
	@Path("/listaSaccheInScadenza")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sacca> listaSaccheInScadenza(){
		
		return mm.getListaSacche();
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
		Seriale ser = new Seriale();
		ser.setSeriale(seriale);
		Sacca saccaPrenotata = mm.getSacca(ser);
		String nomeCTT = seriale.substring(0,6);
		String indirizzoCTT = CTTStaticIpMap.getIndirizzictt().get(nomeCTT);
		
		/**Questo metodo va completato, deve riuscire a creare una notificaEvasione avete come parametri seriale,enteRichiedente,indirizzoEnte
		 * Sarebbe una buona idea creare un metodo post nell'endpoint magazziniere/operatore su cui il CCS posta un notifica e poi il singolo CTT la fa vedere con l'observer
		 * 
		 */
		Client client = ClientBuilder.newClient();
		WebTarget evasioneSacca = client.target(indirizzoCTT+"/rest/notifica/notificaevasione?"+"listaSeriali=");
		
		
		return null;
		
		
	
	
}
	
}
		


