package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManagerBean;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DatiSaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
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
import java.util.StringTokenizer;

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
			//creo la sacca
			Sacca unaSacca = new Sacca(GruppoSanguigno.valueOf(gruppo_sanguigno),
					DateUtil.dateParser(data_produzione),
					DateUtil.dateParser(data_scadenza));
			//inserisco la sacca nel DB
			MongoDataManagerBean.createSacca(unaSacca);
			//creo un dati sacca
			DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(), unaSacca.getGruppoSanguigno(),
					LocalDate.now(), null, ente_donatore, null,null);
			//inserisco datiSacca nel DB
			MongoDataManagerBean.createDatiSacca(datiSacca);
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

	
	/**Metodo attivato dal magazziniere quando riceve una notifica evasione sacche
	 * esso aggiorna i datiSacca e rimuove la Sacca dal DB attivo per ogni sacca presente nell'ordine
	 * @param listaseriali Seriali della Sacca da evadere, i seriali sono separati da una ","
	 * @param ente_richiedente l'ente che ha richiesto la sacca
	 * @param indirizzo l'indirizzo dell'ente che ha richiesto la sacca
	 * @return Response OK se le sacche sono state rimosse correttamente BAD_REQUEST se si Ã¨ tetntato di evadere sacche non presenti nel DB
	 * @return Response 
	 * */
	 
	@PUT
	@Path("/evasione")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response evasioneSacca(@FormParam("listaSeriali") String listaseriali,					
								  @FormParam("enteRichiedente") String ente_richiedente,
								  @FormParam("indirizzoEnte")String indirizzo){

			//Prendo dalla stringa listaSeriali la lista dei seriali attraverso una tokenizzazione, i seriali arrivano nel formato SERIALE/SERIALE/SERIALE/..../SERIALE/
			try {
				List<Seriale> listaSeriali = new ArrayList<Seriale>();

				StringTokenizer st = new StringTokenizer(listaseriali,",");
				while (st.hasMoreTokens())
					 listaSeriali.add(Seriale.getSeriale(st.nextToken()));
				//Etichetta da restituire al client
				String etichetta = "";

				for(Seriale unSeriale : listaSeriali) {

					//recupero una sacca se presente altrimenti si solleva una eccezione SaccaNotFoundException
					Sacca unaSacca = MongoDataManagerBean.getSacca(unSeriale);
					//aggiorno i dati del dati sacca
					MongoDataManagerBean.setEnteRichiedenteDatiSacca(unSeriale, ente_richiedente);
					MongoDataManagerBean.setDataAffidamentoDatiSacca(unSeriale, LocalDate.now());
					MongoDataManagerBean.setIndirizzoEnteDatiSacca(unSeriale,indirizzo);
					//recupero il DatiSacca aggiornato
					DatiSacca datiSacca = MongoDataManagerBean.getDatiSacca(unSeriale);
					//aggiorno etichetta
					etichetta = etichetta + unaSacca.getEtichettaSacca()+"\n"+ datiSacca.getEtichettaDatiSacca() +"\n";
					//rimuovo la sacca
					MongoDataManagerBean.removeSacca(unaSacca.getSeriale());
					//The property set or change succeeded.
			}
			
				return Response
						.status(Response.Status.OK)
						.entity(etichetta + "\nEnte richiedente: " + ente_richiedente + "\nIndirizzo: " + indirizzo)
						.build();
			}catch(AssertionError ex){
				//The request could not be understood by the server due to malformed syntax.
				// The client SHOULD NOT repeat the request without modifications.
				return Response
						.status(Response.Status.BAD_REQUEST)
						.entity(ex.getMessage())
						.build();
			}catch(SaccaNotFoundException | DatiSaccaNotFoundException ex){
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

		List<Sacca> listaSacche = MongoDataManagerBean.getListaSacche();
		List<Seriale> serialiSacca = new ArrayList<Seriale>();

		for(Sacca s : listaSacche)
			serialiSacca.add(s.getSeriale());

		return serialiSacca;
	}

}