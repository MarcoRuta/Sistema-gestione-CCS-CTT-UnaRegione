package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
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

@Path("/CTT")
@Singleton
@Secured
@RolesAllowed("MagazziniereCTT")
public class EndPointRestMagazziniereCTT implements EndPointMagazziniereCTT {

	/**
	 * @param seriale seriale della sacca da evadere
	 * Metodo attivato dal magazziniere quando riceve una notifica evasione sacca
	 * esso aggiorna i dati sacca e rimuove la sacca dal DB attivo
	 * @return */
	@PUT
	@Path("/evasione/{seriale}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response evasioneSacca(@PathParam("seriale") String seriale,
								  @FormParam("enterichiedente") String ente_richiedente,
								  @FormParam("indirizzo")String indirizzo){
		Seriale unSeriale = null;
		try {
			unSeriale = new Seriale(seriale);
			DataManager mm = new MongoDataManager();
			DatiSacca datiSacca = mm.getDatiSacca(unSeriale);
			Sacca unaSacca = mm.getSacca(unSeriale);
			if (datiSacca== null || unaSacca == null)
				//The property does not exist.
				return Response
						.status(Response.Status.NOT_FOUND)
						.entity("Seriale non presente nel DataBase")
						.build();

			mm.setEnteRichiedenteDatiSacca(unSeriale, ente_richiedente);
			mm.setDataAffidamentoDatiSacca(unSeriale, LocalDate.now());
			mm.setIndirizzoEnteDatiSacca(unSeriale,indirizzo);

			datiSacca = mm.getDatiSacca(unSeriale);
			mm.removeSacca(unaSacca.getSeriale());
			//The property set or change succeeded.
			return Response
					.status(Response.Status.OK)
					.entity(unaSacca.getEtichettaSacca()+"\n"+ datiSacca.getEtichettaDatiSacca())
					.build();
		}catch(AssertionError assertionError){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Formattazione del seriale non corretto")
					.build();
		}
	}


	/**@return  messaggio corretta aggiunta sacca*/
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response aggiuntaSaccaMagazzino(@FormParam("gruppo_sanguigno") String gruppo_sanguigno,
										   @FormParam("data_scadenza") String data_scadenza,
										   @FormParam("data_produzione") String data_produzione,
										   @FormParam("ente_donatore") String ente_donatore) {
		DataManager mm = new MongoDataManager();
		try {
			Sacca unaSacca = new Sacca(GruppoSanguigno.valueOf(gruppo_sanguigno),
					DateUtil.dateParser(data_produzione),
					DateUtil.dateParser(data_scadenza));
			mm.createSacca(unaSacca);
			DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(), unaSacca.getGruppoSanguigno(),
					LocalDate.now(), null, ente_donatore, null,null);
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
		}catch (AssertionError assertionError){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Dati non corretti, impossibile aggiungere la sacca al Magazzino\n"+assertionError.getMessage())
					.build();
		}catch (DateTimeParseException dateTimeParseException){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Formato della data non valido")
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

	/**
	 * Metodo utilizzato per aggiunta automatica del seriale delle sacche
	 * disponibili nel magazzino per l'evasione
	 * @return serialiSacca*/
	@GET
	@Path("/sacche")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Seriale> listaSacca(){

		DataManager mm = new MongoDataManager();
		List<Sacca> listasacche = mm.getListaSacche();
		List<Seriale> serialiSacca = new ArrayList<Seriale>();

		for(int i=0; i< listasacche.size(); i++) {
			serialiSacca.add(listasacche.get(i).getSeriale());
		}

		return serialiSacca;

	}
}