package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DatiSaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Path("/magazziniere")
@Singleton
@Secured
@RolesAllowed("MagazziniereCTT")
public class EndPointRestMagazziniereCTT implements EndPointMagazziniereCTT {

	
	/**Metodo attivato dal magazziniere quando vuole inserire una sacca nel DB
	 * Esso crea datiSacca e Sacca
	 * @param gruppo_sanguigno il gruppo sanguigno della sacca
	 * @param data_produzione  la data di produzione della sacca yyyy-MM-dd
	 * @param data_scadenza la data di scadenza della sacca yyyy-MM-dd
	 * @param ente_donatore  l'ente che ha donato la sacca
	 * @return  messaggio corretta aggiunta sacca*/
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response aggiuntaSaccaMagazzino(@FormParam("gruppo_sanguigno") String gruppo_sanguigno,
										   @FormParam("data_scadenza") String data_scadenza,
										   @FormParam("data_produzione") String data_produzione,
										   @FormParam("ente_donatore") String ente_donatore) {

		try {
			DataManager mm = new MongoDataManager();
			//creo la sacca
			Sacca unaSacca = new Sacca(GruppoSanguigno.valueOf(gruppo_sanguigno),
					DateUtil.dateParser(data_produzione),
					DateUtil.dateParser(data_scadenza));
			//inserisco la sacca nel DB
			mm.createSacca(unaSacca);
			//creo un dati sacca
			DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(), unaSacca.getGruppoSanguigno(),
					LocalDate.now(), null, ente_donatore, null,null);
			//inserisco datiSacca nel DB
			mm.createDatiSacca(datiSacca);
			//update Seriale settings
			Seriale.updateSettings();
			return Response
					// The source resource was successfully copied.
					// The COPY operation resulted in the creation of a new resource.
					.status(Response.Status.CREATED)
					.entity("Sacca con seriale " + unaSacca.getSeriale().getSeriale() + " aggiunta correttamente")
					.header("Location", new URL("http://127.0.0.1:8080/sacche/"+unaSacca.getSeriale().getSeriale()))
					.build();
		}catch (AssertionError | DateTimeParseException  ex){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Dati non corretti, impossibile aggiungere la sacca al Magazzino\n"+ex.getMessage())
					.build();
		} catch (MalformedURLException e) {
			//something has gone wrong on the website's server,
			// but the server could not be more specific on what the exact problem is.
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Non è stato possibile creare l'uri per la nuova risorsa")
					.build();
		}
	}

	
	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca
	 * esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 * @param seriale Seriale della Sacca da evadere
	 * @param ente_richiedente l'ente che ha richiesto la sacca
	 * @param indirizzo l'indirizzo dell'ente che ha richiesto la sacca
	 * @return Response 200 ok se la sacca è evasa correttamente oppure BadRequest se i dati sono formattati non correttamente */
	@PUT
	@Path("/evasione/{seriale}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response evasioneSacca(@PathParam("seriale") String seriale,
								  @FormParam("enterichiedente") String ente_richiedente,
								  @FormParam("indirizzo")String indirizzo){
		try {
			//creo il seriale per controllare che sia corretto
			Seriale unSeriale = Seriale.getSeriale(seriale);
			//creo un data manager pre recuperare i dati
			DataManager mm = new MongoDataManager();
			//recupero un DatiSacca se presente altrimenti si solleva una eccezione DatiSaccaNotFoundException
			DatiSacca datiSacca = mm.getDatiSacca(unSeriale);
			//recupero una sacca se presente altrimenti si solleva una eccezione SaccaNotFoundException
			Sacca unaSacca = mm.getSacca(unSeriale);
			//aggiorno i dati del dati sacca
			mm.setEnteRichiedenteDatiSacca(unSeriale, ente_richiedente);
			mm.setDataAffidamentoDatiSacca(unSeriale, LocalDate.now());
			mm.setIndirizzoEnteDatiSacca(unSeriale,indirizzo);
			//recupero il DatiSacca aggiornato
			datiSacca = mm.getDatiSacca(unSeriale);
			//rimuovo la sacca
			mm.removeSacca(unaSacca.getSeriale());
			//The property set or change succeeded.
			return Response
					.status(Response.Status.OK)
					.entity(unaSacca.getEtichettaSacca()+"\n"+ datiSacca.getEtichettaDatiSacca())
					.build();
		}catch(AssertionError ex){
			System.err.println("Seertion Error");
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage())
					.build();
		}catch(SaccaNotFoundException | DatiSaccaNotFoundException ex){
			System.err.println("sacca not found exception");
			//non ho trovato la risorsa
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage())
					.build();
		}
	}
	
	
	/**
	 * Metodo utilizzato per aggiunta automatica del seriale delle sacche
	 * disponibili nel magazzino per l'evasione
	 * @return serialiSacca
	 */
	@GET
	@Path("/sacche")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Seriale> listaSacca(){

		DataManager mm = new MongoDataManager();
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Seriale> serialiSacca = new ArrayList<Seriale>();

		for(Sacca s : listaSacche)
			serialiSacca.add(s.getSeriale());

		return serialiSacca;
	}

}