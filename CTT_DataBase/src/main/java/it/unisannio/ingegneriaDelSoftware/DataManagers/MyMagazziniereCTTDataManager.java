package it.unisannio.ingegneriaDelSoftware.DataManagers;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DipendenteCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.MagazziniereCTTDataManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Path("/CTT")
@Singleton

public class MyMagazziniereCTTDataManager implements MagazziniereCTTDataManager, DipendenteCTT {
	
	/**Login è l'operazione con la quale MagazziniereCTT accede al sistema
	 * @param username Username che usa MagazziniereCTT per entrare nel sistema
	 * @param password Password che usa MagazziniereCTT per entrare nel sistema
	 * @return true se Username e Password corrispondono; false altrimenti
	 */
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}


	/**
	 * @param seriale seriale della sacca da evadere
	 * Metodo attivato dal magazziniere quando riceve una notifica evasione sacca
	 * esso aggiorna i dati sacca e rimuove la sacca dal DB attivo
	 * @return */
	@PUT
	@Path("/evasione/{seriale}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	//CTT001
	public Response evasioneSacca(@PathParam("seriale") String seriale, String ente_richiedente){
		Seriale unSeriale = null;
		try {
			unSeriale = new Seriale(seriale);
		}catch(AssertionError assertionError){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("Formattazione del seriale non corretto")
					.build();
		}

		DataManager mm = new MyMongoDataManager();
		DatiSacca datiSacca = mm.getDatiSacca(unSeriale);
		Sacca unaSacca = mm.getSacca(unSeriale);
		if (datiSacca == null || unaSacca == null)
			//The property does not exist.
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity("Seriale non presente nel DataBase")
					.build();

		mm.setEnteRichiedenteDatiSacca(unSeriale, ente_richiedente);
		mm.setDataAffidamentoDatiSacca(unSeriale, LocalDate.now());

		datiSacca = mm.getDatiSacca(unSeriale);
		mm.removeSacca(unaSacca.getSeriale());
		//The property set or change succeeded.
		return Response
				.status(Response.Status.OK)
				.entity(unaSacca.getEtichettaSacca()+"\n"+ datiSacca.getEtichettaDatiSacca())
				.build();
	}


	/**@return  messaggio corretta aggiunta sacca*/
	@POST
	@Path("/aggiuntaSacca")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response aggiuntaSaccaMagazzino(@FormParam("gruppo_sanguigno") String gruppo_sanguigno,
										   @FormParam("data_produzione") String data_produzione,
										   @FormParam("data_scadenza") String data_scadenza,
										   @FormParam("ente_donatore") String ente_donatore) {
		DataManager mm = new MyMongoDataManager();
		try {
			Sacca unaSacca = new Sacca(GruppoSanguigno.valueOf(gruppo_sanguigno),
					LocalDate.parse(data_produzione, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					LocalDate.parse(data_scadenza, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			mm.createSacca(unaSacca);
			DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(), unaSacca.getGruppoSanguigno(), LocalDate.now(), null, ente_donatore, null);
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
					.entity("Dati non corretti, impossibile aggiungere la sacca al Magazzino")
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
}