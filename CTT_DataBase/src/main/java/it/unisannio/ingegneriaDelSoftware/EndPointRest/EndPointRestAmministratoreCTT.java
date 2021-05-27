package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.Util.*;

@Path("/amministratore")
@Singleton
@Secured
@RolesAllowed("AmministratoreCTT")
@PermitAll
public class EndPointRestAmministratoreCTT implements EndPointAmministratoreCTT {
	private MongoDataManager md = MongoDataManager.getInstance();

	/**Aggiunge un Dipendente al DataBase
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param ruolo
	 * @param username
	 * @return Response 200 ok, username password e codice fiscale del Dipendente se tutto è andato a buonfine.
	 */
	@POST
	@Path("/aggiuntaDipendente")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username) throws DateTimeParseException,IllegalArgumentException,AssertionError{

		//creo un dipendente
		String password = PasswordGenerator.getPassword();
		Dipendente d = new Dipendente(Cdf.getCDF(cdf), nome, cognome,
				LocalDate.parse(dataDiNascita, DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
				RuoloDipendente.valueOf(ruolo), username, password);
		//aggiungo il dipendente al DB
		md.createDipendente(d);
		return Response
				.status(Response.Status.OK)
				.entity("Dipendente: "+cdf+"\n"+"username: "+username+"\n"+"password: "+password)
				.build();
	}


	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return Response 200 OK
	 */
	@DELETE
	@Path("/rimozioneDipendente/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDipendente(@PathParam("cdf") String cdf) throws DipendenteNotFoundException {
		md.removeDipendente(Cdf.getCDF(cdf));
		return Response
				.status(Response.Status.OK)
				.entity("Corretta rimozione del Dipendente: " + cdf)
				.build();
	}


	/**---------REPORT OPERATORI CTT------------
	 * Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return Response 200 OK e invia la lista dei dipendenti del ruolo selezionato
	 */
	@GET
	@Path("/reportOperatoriCtt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportOperatoriCTT(@QueryParam("ruolo")String ruolo){
	
			List<Dipendente> listaDipendenti = getDipendenti();
			List<Dipendente> risultatoQuery = new ArrayList<Dipendente>();
			
			for(Dipendente d : listaDipendenti)
				if(d.getRuolo().toString().equals(ruolo)) risultatoQuery.add(d);
			
			return Response	 		
					.status(Response.Status.OK)
					.entity(risultatoQuery)
					.build();
	}


	/**---------REPORT SACCHE DI UN TIPO PRESENTI NEL DATABASE SACCHE------------
	 * Restituisce la lista delle Sacche di un determinato Gruppo sanguigno, presenti del database delle Sacche
	 * @param gs Gruppo sanguigno delle Sacche che si vogliono ricercare
	 * @return Response 200 OK e invia la lista dei datiSacca 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/reportStatisticoSacche")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportStatisticoSacche(@QueryParam("gs")String gs){

		List<Sacca> listaSacche = md.getListaSacche();
		List<Sacca> risultatoQuery = new ArrayList<Sacca>();
		
		for(Sacca s : listaSacche)
			if(s.getGruppoSanguigno().toString().equals(gs)) risultatoQuery.add(s);
		
		return Response	 		
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
}


	/**---------REPORT SACCHE INVIATE E RICEVUTE CTT------------
	 * Restituisce la lista dei DatiSacche relativi alle sacche che sono state caricate o affidate in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return Response 200 OK e invia la lista dei datiSacca 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/reportLocaleSaccheInviateERicevute")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportLocaleSaccheInviateERicevuteCTT(@QueryParam("dataInizio")String dataInizio,
															@QueryParam("dataFine")String dataFine) throws DateTimeParseException{
		LocalDate dataInizioReport = LocalDate.parse(dataInizio, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		LocalDate dataAffidamentoReport = LocalDate.parse(dataFine, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		List <DatiSacca> risultatoQuery = getDatiSaccaInATemporalAmount(dataInizioReport,dataAffidamentoReport);
		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}


	/**---------REPORT SACCHE INVIATE E RICEVUTE CTT PER OGNI TIPO DI SANGUE SCELTO------------
	 * Restituisce il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno scelto dall'AmministratoreCTT
	 * @param listaGS Lista dei Gruppi sanguigni scelti dall'AmministratoreCTT, una strigna con gruppo1:gruppo2:gruppo3
	 * @param dataInizio Data di inizio dell' arco temporale
	 * @param dataFine Data di fine dell' arco temporale
	 * @return Response 200 OK e invia la lista dei datiSacca / 400 BAD_REQUEST se i parametri inseriti non sono corretti
	 */
	@GET
	@Path("/ordinaGruppiSanguigniPerRichieste")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ordinaGruppiSanguigniPerRichieste(@QueryParam("listaGS") String listaGS,
														@QueryParam("dataInizio")String dataInizio,
														@QueryParam("dataFine")String dataFine) throws DateTimeParseException{
		String risultatoQuery = "";
		StringTokenizer aTokenizer = new StringTokenizer(listaGS);
		//recupero le sacche nll'arco di tempo di interesse
		LocalDate dataInizioReport = LocalDate.parse(dataInizio, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		LocalDate dataAffidamentoreport = LocalDate.parse(dataFine, DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
		List<DatiSacca> listaDatiSaccheInIntervallo = this.getDatiSaccaInATemporalAmount(dataInizioReport, dataAffidamentoreport);

		// per ogni gruppo sanguigno controllo le sacche che ho avuto di quel tipo in quell'intervalllo
		while (aTokenizer.hasMoreTokens()) {
			String gs = aTokenizer.nextToken(":");
			int contatore = 0;
			for (DatiSacca ds : listaDatiSaccheInIntervallo)
				if (gs.equals(ds.getGruppoSanguigno().toString()))
					contatore++;
			//aggiorno il risultato
			risultatoQuery = "Numero di sacche con gruppo sanguigno: " + gs + ": " + contatore;
			//ora vado al gruppo sanguigno successivo
		}
		return Response
				.status(Response.Status.OK)
				.entity(risultatoQuery)
				.build();
	}

	/**Metodo tramite il quale è possibile accedere alla lista di Dipendenti contenuti nel database dei Dipendenti 
	 * @return la lista di dipendenti che lavorano al CTT*/
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getDipendenti(){		
		return md.getListaDipendenti();
	}

	/**Restituisce una lista di DatiSacca che sono arrivate dopo di dataInizioReport oppure sono state affidate dopo prima di dataAffidamentoReport
	 * @param dataInizioReport  data dalla quale si selezionano le Sacche
	 * @param dataFineReport data oltre la quale non si selezionano più le Sacche
	 * @return lista di Sacche che erano nel CTT nell'arco temporale dataInizioReport-dataAffidamentoReport*/
	private List<DatiSacca> getDatiSaccaInATemporalAmount(LocalDate dataInizioReport, LocalDate dataFineReport){
		List<DatiSacca> datiSaccaTransitati = new ArrayList<>();
		
		//creo la lista dei dati sacca
		List<DatiSacca> listaDatiSacca = md.getListaDatiSacche();
		for (DatiSacca datiSacca : listaDatiSacca)
			if ((datiSacca.getDataArrivo().isAfter(dataInizioReport) && datiSacca.getDataArrivo().isBefore(dataFineReport))
					|| (datiSacca.getDataAffidamento().get().isAfter(dataInizioReport) && datiSacca.getDataAffidamento().get().isBefore(dataFineReport))
					|| (datiSacca.getDataArrivo().isEqual(dataInizioReport) && datiSacca.getDataAffidamento().get().isEqual(dataFineReport)))
				//se è verificata una delle 4 condizioni aggiungo alla lista
				datiSaccaTransitati.add(datiSacca);
		return datiSaccaTransitati;
	}


}	