package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.Cdf;
import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.Dipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.RuoloDipendente;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DataManager;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointAmministratoreCTT;
import it.unisannio.ingegneriaDelSoftware.Util.*;

@Path("/amministratore")
@Singleton
@Secured
@RolesAllowed("AmministratoreCTT")
public class EndPointRestAmministratoreCTT implements EndPointAmministratoreCTT {

	/**Aggiunge un Dipendente al DataBase
	 * @param cdf Dipendente da aggiungere al DataBase
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param ruolo
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/aggiuntadipendente")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addDipendente(@FormParam("cdf")String cdf,
								  @FormParam("nome")String nome,
								  @FormParam("cognome")String cognome,
								  @FormParam("dataDiNascita")String dataDiNascita,
								  @FormParam("ruolo")String ruolo,
								  @FormParam("username")String username,
								  @FormParam("password")String password){

		try {
			//creo il dataManager
			MongoDataManager mm = new MongoDataManager();
			//creo un dipendente
			Dipendente d = new Dipendente(Cdf.getCDF(cdf), nome, cognome, DateUtil.dateParser(dataDiNascita),
					RuoloDipendente.valueOf(ruolo), username, password);
			//aggiungo il dipendente al DB
			mm.addDipendente(d);
			return Response
					.status(Response.Status.OK)
					.entity("Corretta aggiunta del Dipendente: "+cdf)
					.build();
		}catch(DateTimeParseException| IllegalArgumentException | AssertionError e) {
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
	}


	/**Rimuove un Dipendente dal DataBase
	 * @param cdf Codice fiscale del Dipendente da rimuovere dal DataBase
	 * @return
	 */
	@DELETE
	@Path("/rimozionedipendente/{cdf}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDipendente(@PathParam("cdf") String cdf) {
		try {			
			MongoDataManager mm = new MongoDataManager();			
			mm.removeDipendente(Cdf.getCDF(cdf));
			return Response
					.status(Response.Status.OK)
					.entity("Corretta rimozione del Dipendente: " + cdf)
					.build();
		}catch (AssertionError ex){
			//il dipendente che si vuole rimuovere non è presente
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity("Rimozione del Dipendente: " + cdf+" non riuscita\n"+ex.getMessage())
					.build();
		}
	}


	/**Restituisce la lista dei Dipendenti del CTT che occupano il Ruolo scelto
	 * @param ruolo Ruolo dei Dipendenti da cercare
	 * @return la lista dei Dipendenti del Ruolo scelto
	 */
	@POST
	@Path("/reportoperatorictt")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public List<Dipendente> reportOperatoriCTT(String ruolo){
		MongoDataManager mm = new MongoDataManager();
		
		List<Dipendente> dipendenti = mm.getListaDipendenti();
		List<Dipendente> dipendentiRuolo = new ArrayList<Dipendente>();		
		
		for (Dipendente dipendente : dipendenti)
			if(dipendente.getRuolo().equals(RuoloDipendente.valueOf(ruolo)))
				dipendentiRuolo.add(dipendente);
		return dipendentiRuolo;
	}


	/**Restituisce la lista delle Sacche di un determinato Gruppo sanguigno, presenti del database delle Sacche
	 * @param gs Gruppo sanguigno delle Sacche che si vogliono ricercare
	 * @return la lista di Sacche di un determinato Gruppo sanguigno
	 */
	@POST
	@Path("/reportstatisticosacche")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public List<Sacca> reportStatisticoSacche(String gs){
		//creo la lista da restituire
		List<Sacca> saccheGS = new ArrayList<Sacca>();
		//creo il data manager
		MongoDataManager mm = new MongoDataManager();
		//recupero la lista delle sacche dal db
		List<Sacca> listaSacche = mm.getListaSacche();
		//seleziono solo quelle di mio interesse
		for (Sacca sacca : listaSacche)
			if(sacca.getGruppoSanguigno().equals(GruppoSanguigno.valueOf(gs)))
				saccheGS.add(sacca);
		return saccheGS;
	}


	/**Restituisce la lista dei datiSacche che sono transitate per un CTT in un determinato arco temporale
	 * @param dataInizio Data inizio dell' arco temporale
	 * @param dataFine Data fine dell' arco temporale
	 * @return la lista di sacche che sono transitate per un CTT in un determinato arco temporale
	 */
	@POST
	@Path("/reportlocalesaccheinviateericevutectt")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response ReportLocaleSaccheInviateERicevuteCTT(@FormParam("dataInizio")String dataInizio,
															@FormParam("dataFine")String dataFine){
		try {
			LocalDate dataInizioReport = DateUtil.dateParser(dataInizio);
			LocalDate dataAffidamentoReport = DateUtil.dateParser(dataFine);
			return Response
					.status(Response.Status.OK)
					.entity(this.getDatiSaccaInATemporalAmount(dataInizioReport,dataAffidamentoReport))
					.build();
		}catch (DateTimeParseException ex){
			//The request could not be understood by the server due to malformed syntax.
			// The client SHOULD NOT repeat the request without modifications.
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage())
					.build();
		}
	}


	/**Restituisce il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno scelto dall'AmministratoreCTT
	 * @param listaGS Lista dei Gruppi sanguigni scelti dall'AmministratoreCTT, una strigna con gruppo1:gruppo2:gruppo3
	 * @param dataInizio Data di inizio dell' arco temporale
	 * @param dataFine Data di fine dell' arco temporale
	 * @return la stringa contenente il numero di Sacche ricevute e inviate in un arco temporale, per ogni Gruppo sanguigno
	 */
	@POST
	@Path("/ordinagruppisanguigniperrichieste")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response OrdinaGruppiSanguigniPerRichieste(@FormParam("listaGS") String listaGS,
														@FormParam("dataInizio")String dataInizio,
														@FormParam("dataFine")String dataFine) {
		try {
			String risultatoQuery = "";
			StringTokenizer aTokenizer = new StringTokenizer(listaGS);
			//recupero le sacche nll'arco di tempo di interesse
			LocalDate dataInizioReport = DateUtil.dateParser(dataInizio);
			LocalDate dataAffidamentoreport = DateUtil.dateParser(dataFine);
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
			}catch(DateTimeParseException ex){
				return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage())
					.build();
			}
	}

	/**Metodo tramite il quale è possibile accedere alla lista di dipendenti che lavorano al CTT
	 * @return  la lista di dipendenti che lavorano al CTT*/
	@GET
	@Path("/dipendenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> getDipendenti(){
		
		DataManager dataManager = new MongoDataManager();
		return dataManager.getListaDipendenti();
		
	}

	/**Restituisce una lista di dati sacca che sono arrivate dopo di dataInizioReport oppure sono state affidate dopo prima
	 * di dataAffidamentoReport
	 * @param dataInizioReport  data dalla quale selezioniamo le sacche
	 * @param dataAffidamentoReport data oltre la quale non selezioniamo piu le sacche
	 * @return  lista di sacche che erano nel CTT nell'arco temporale dataInizioReport-dataAffidamentoReport*/
	private List<DatiSacca> getDatiSaccaInATemporalAmount(LocalDate dataInizioReport, LocalDate dataAffidamentoReport){
		List<DatiSacca> datiSaccaTransitati = new ArrayList<>();
		//creo un data manager
		MongoDataManager mm = new MongoDataManager();
		//creo la lista dei dati sacca
		List<DatiSacca> listaDatiSacca = mm.getListaDatiSacche();
		for (DatiSacca datiSacca : listaDatiSacca)
			if (datiSacca.getDataArrivo().isAfter(dataInizioReport) || datiSacca.getDataAffidamento().isBefore(dataAffidamentoReport)
					|| datiSacca.getDataArrivo().isEqual(dataInizioReport) || datiSacca.getDataAffidamento().isEqual(dataAffidamentoReport))
				//se è verificata una delle 4 condizioni aggiungo alla lista
				datiSaccaTransitati.add(datiSacca);
		return datiSaccaTransitati;
	}


}	