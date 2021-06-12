package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Notifiche.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.Functional.PDFGenerator;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("/magazziniere")
@Singleton
@Secured
@RolesAllowed({"MagazziniereCTT","CCS"})
public class EndPointRestMagazziniereCTT implements EndPointMagazziniereCTT {

	/**Riferimento all'unica istanza del MongoDataManager*/
	private MongoDataManager md = MongoDataManager.getInstance();

	/** Lista delle evasioni giornaliere */
	//private Map<String,List<Seriale>> evasioni = new HashMap<>();



	/**Metodo con il quale il Magazziniere aggiunge una Sacca al DataBase
	 * @param gruppo_sanguigno Gruppo sanguigno della Sacca
	 * @param data_scadenza Data di scadenza della Sacca
	 * @param data_produzione Data di produzione della Sacca
	 * @param ente_donatore Ente di provenienza della Sacca
	 * @param uriInfo info dell'uri relativo alla risorsa richiesta
	 * @return Response
	 * @throws EntityAlreadyExistsException se si vuole aggiungere una Sacca già presente nel DB
	 */
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response aggiuntaSaccaMagazzino(@FormParam("gruppo_sanguigno") String gruppo_sanguigno,
										   @FormParam("data_scadenza") String data_scadenza,
										   @FormParam("data_produzione") String data_produzione,
										   @FormParam("ente_donatore") String ente_donatore,
										   @Context UriInfo uriInfo) throws EntityAlreadyExistsException {
		//creo la sacca
		Sacca unaSacca = new Sacca(GruppoSanguigno.valueOf(gruppo_sanguigno),
				LocalDate.parse(data_produzione, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
				LocalDate.parse(data_scadenza, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)));

		//controllo che la sacca non sia scaduta
		if (unaSacca.getDataScadenza().isBefore(LocalDate.now()) || unaSacca.getDataScadenza().isEqual(LocalDate.now()))
			throw new WebApplicationException("Impossibile aggiungere una sacca scaduta", Response.Status.BAD_REQUEST);

		//inserisco la sacca nel DB
		md.createSacca(unaSacca);
		//creo un dati sacca
		DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(), unaSacca.getGruppoSanguigno(),
				LocalDate.now(), null, ente_donatore, null,null);
		//inserisco datiSacca nel DB
		md.createDatiSacca(datiSacca);
		//update Seriale settings
		Seriale.updateSettings();
		// The source resource was successfully copied.
		// The COPY operation resulted in the creation of a new resource.
		return Response
				.status(Response.Status.CREATED)
				.entity("Sacca con seriale " + unaSacca.getSeriale().getSeriale() + " aggiunta correttamente")
				.header("Location",uriInfo.getBaseUri()+"/sacche/"+unaSacca.getSeriale().getSeriale())
				.build();
	}


	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 * @param notificaEvasione la notifica evasione relativa all'ordine da evadere
	 * @return Response
	 * @throws EntityNotFoundException se la sacca da evadere non è presente nel DB
	 */
	@POST
	@Path("/evasione")
	@Produces("application/pdf")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response evasioneSacca(NotificaEvasione notificaEvasione) throws EntityNotFoundException {

		CttRestApplication.logger.info("Il terminale del magazzinere ha ricevuto una notifica di evasione: "+notificaEvasione);

		for(Seriale unSeriale : notificaEvasione.getListaSeriali()) {
			//recupero una sacca se presente altrimenti si solleva una eccezione SaccaNotFoundException
			Sacca unaSacca = md.getSacca(unSeriale);
			//aggiorno i dati del dati sacca
			md.setEnteRichiedenteDatiSacca(unSeriale, notificaEvasione.getEnteRichiedente());
			md.setDataAffidamentoDatiSacca(unSeriale, LocalDate.now());
			md.setIndirizzoEnteDatiSacca(unSeriale, notificaEvasione.getIndirizzoEnte());
			//rimuovo la sacca
			md.removeSacca(unaSacca.getSeriale());
		}

		//aggiorno la lista delle evasioni rimuovendo quella appena evasa
		new EndPointRestNotificheMagazziniere().removeNotificaEvasione(notificaEvasione);

		return Response
				.status(Response.Status.CREATED)
				.entity(getPDF(notificaEvasione.getListaSeriali(), notificaEvasione.getEnteRichiedente(), notificaEvasione.getIndirizzoEnte(), LocalDate.now()))
				.build();
	}

	
	/**Ottiene i dati di una evasione sotto forma di PDF
	 * @param seriali la lista dei seriali da evadere
	 * @param ente l'ente richiedente
	 * @param indirizzo l'indirizzo dell'ente richiedente
	 * @param dataAffidamento data in cui viene evasa la sacca dal magazzino
	 * @return StreamingOutput StreamingOutput da dove verrà aperto il pdf generato
	 * */
	private StreamingOutput getPDF( List<Seriale> seriali, String ente, String indirizzo, LocalDate dataAffidamento) {
		return new StreamingOutput(){
			public void write(OutputStream output){
				try {

					int numeroSacche = seriali.size();

					PDFGenerator.makeDocumentSacca(output, numeroSacche,seriali, ente, indirizzo, DateTimeFormatter.ISO_LOCAL_DATE.format(dataAffidamento));
				} catch (DocumentException | IOException e) {
					throw new WebApplicationException(Response
							.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity("Impossibile creare il PDF per l'evasione")
							.build());
				}
			}
		};
	}

}