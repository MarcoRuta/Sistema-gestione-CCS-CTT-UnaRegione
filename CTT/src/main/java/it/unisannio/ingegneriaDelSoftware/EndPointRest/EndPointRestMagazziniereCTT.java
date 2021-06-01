package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import com.itextpdf.text.DocumentException;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityAlreadyExistsException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointMagazziniereCTT;
import it.unisannio.ingegneriaDelSoftware.PDF.PDFGenerator;
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
@RolesAllowed("MagazziniereCTT")
public class EndPointRestMagazziniereCTT implements EndPointMagazziniereCTT {

	private MongoDataManager md = MongoDataManager.getInstance();
	private Map<String,List<Seriale>> evasioni = new HashMap<>();


	/**Metodo con il quale il Magazziniere aggiunge una Sacca al DataBase
	 *
	 * @param gruppo_sanguigno Gruppo sanguigno della Sacca
	 * @param data_scadenza Data di scadenza della Sacca
	 * @param data_produzione Data di produzione della Sacca
	 * @param ente_donatore Ente di provenienza della Sacca
	 * @param uriInfo  info dell'uri relativo alla risorsa richiesta
	 * @return Messaggio di errore in caso di problema di inserimento dati;
	 * @throws EntityAlreadyExistsException se si vuole aggiungere una sacca già presente nel DB
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
		//inserisco la sacca nel DB
		md.createSacca(unaSacca);
		//creo un dati sacca
		DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(), unaSacca.getGruppoSanguigno(),
				LocalDate.now(), null, ente_donatore, null,null);
		//inserisco datiSacca nel DB
		md.createDatiSacca(datiSacca);
		//update Seriale settings
		Seriale.updateSettings();
		return Response
				.status(Response.Status.CREATED)
				.entity("Sacca con seriale " + unaSacca.getSeriale().getSeriale() + " aggiunta correttamente")
				.header("Location",uriInfo.getBaseUri()+"/sacche/"+unaSacca.getSeriale().getSeriale())
				.build();
	}


	/**Metodo attivato dal magazziniere quando riceve una notifica evasione Sacca esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo
	 *
	 * @param listaseriali Seriale della Sacca da evadere
	 * @param ente_richiedente Ente che richiede la Sacca
	 * @param indirizzo Indirizzo dell'enteRichiedente
	 * @param uriInfo  info dell'uri relativo alla risorsa richiesta
	 * @return messaggio di corretta evasione.
	 * @throws EntityNotFoundException se la sacca da evadere non è presente nel DB
	 */
	@POST
	@Path("/evasione")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response evasioneSacca(@FormParam("listaSeriali") String listaseriali,
								  @FormParam("enteRichiedente") String ente_richiedente,
								  @FormParam("indirizzoEnte")String indirizzo,
								  @Context UriInfo uriInfo) throws EntityNotFoundException {

		//Prendo dalla stringa listaSeriali la lista dei seriali attraverso una tokenizzazione, i seriali arrivano nel formato SERIALE/SERIALE/SERIALE/..../SERIALE/
		List<Seriale> listaSeriali = new ArrayList<Seriale>();
		StringTokenizer st = new StringTokenizer(listaseriali,",");
		while (st.hasMoreTokens())
			listaSeriali.add(Seriale.getSeriale(st.nextToken()));


		for(Seriale unSeriale : listaSeriali) {
			//recupero una sacca se presente altrimenti si solleva una eccezione SaccaNotFoundException
			Sacca unaSacca = md.getSacca(unSeriale);
			//aggiorno i dati del dati sacca
			md.setEnteRichiedenteDatiSacca(unSeriale, ente_richiedente);
			md.setDataAffidamentoDatiSacca(unSeriale, LocalDate.now());
			md.setIndirizzoEnteDatiSacca(unSeriale,indirizzo);
			//rimuovo la sacca
			md.removeSacca(unaSacca.getSeriale());
			
		}

		//registro l'evasione
		String id_evasione = IDGenerator.getID();
		this.evasioni.put(id_evasione,listaSeriali);

		return Response
				.status(Response.Status.CREATED)
				.entity("Sacche evase correttamente")
				.header(HttpHeaders.CONTENT_LOCATION,
						uriInfo.getAbsolutePath().getPath()+"/pdf/"+id_evasione)
				.build();
	}

	/**
	 *Metodo che permette di ottenere i dati di una evasione sottoforma di PDF
	 * @param id_evasione id dell'evasione cercata
	 * @return StreamingOutput
	 * */
	@GET
	@Path("/evasione/pdf/{id_evasione}")
	@Produces("application/pdf")
	@Consumes(MediaType.TEXT_PLAIN)
	public StreamingOutput getPDF(@PathParam("id_evasione")String id_evasione) {
		return new StreamingOutput(){
			public void write(OutputStream output){
				try {
					if(!evasioni.containsKey(id_evasione))
						throw new WebApplicationException(
										Response
										.status(Response.Status.NOT_FOUND)
										.entity("Evasione non trovata")
										.build());

					List<Seriale> seriali = evasioni.get(id_evasione);
					int numeroSacche = seriali.size();
					Seriale unSeriale = seriali.get(0);
					DatiSacca unSacca = MongoDataManager.getInstance().getDatiSacca(unSeriale);
					String indirizzo =unSacca.getIndirizzoEnte();
					String dataAffidamento = unSacca.getDataAffidamento().get().toString();
					String gruppoSanguigno = unSacca.getGruppoSanguigno().toString();
					String ente = unSacca.getEnteRichiedente();
					PDFGenerator.makeDocumentSacca(output, numeroSacche, ente, indirizzo, dataAffidamento, gruppoSanguigno);
				} catch (DocumentException | IOException | EntityNotFoundException e) {
					throw new WebApplicationException(Response
							.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity("Impossibile creare il PDF per l'evasione con id "+id_evasione)
							.build());
				}
			}
		};
	}



}