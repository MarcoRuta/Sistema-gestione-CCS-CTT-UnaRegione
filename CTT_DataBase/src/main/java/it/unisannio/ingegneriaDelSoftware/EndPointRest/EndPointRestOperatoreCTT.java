package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import MosquitoNotificationTry.NotificaEvasionePublisher;
import it.unisannio.ingegneriaDelSoftware.EndPointNotifiche.EndPointNotificheMagazziniere;
import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.NotificaEvasione;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.NotificaNonCreataException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccheInLocaleNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.Util.ScadenzeComparator;


@Path("/operatore")
@Singleton
@Secured
@RolesAllowed("OperatoreCTT")
public class EndPointRestOperatoreCTT implements EndPointOperatoreCTT{

	/**Restituisce la Sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
	 * @param gruppoSanguigno Gruppo sanguigno ricercato
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
	 * @param enteRichiedente Ente richiedente della Sacca
	 * @return la Sacca con le caratteristiche richieste
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la sacca inserita non viene trovata
	 */
	@GET
	@Path("/ricerca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ricercaSaccaLocale(@QueryParam("gruppoSanguigno") String gruppoSanguigno,
									   @QueryParam("numeroSacche") String numeroSacche,
									   @QueryParam("dataArrivoMassima") String dataArrivoMassima,
									   @QueryParam("enteRichiedente") String enteRichiedente,
									   @QueryParam("indirizzoEnte") String indirizzoEnte,
									   @QueryParam("priorità") String priorita){
		try {
			MongoDataManager mm = new MongoDataManager();
			List<Sacca> saccheTrovate = new ArrayList<Sacca>();
			int numSacche = Integer.parseInt(numeroSacche);
			saccheTrovate = ricercaSacca(GruppoSanguigno.valueOf(gruppoSanguigno), DateUtil.dateParser(dataArrivoMassima));

			if(saccheTrovate.size() < numSacche) {
				for(Sacca s : saccheTrovate) s.print(System.err);
				saccheTrovate.addAll(ricercaSaccaCompatibile(
						GruppoSanguigno.valueOf(gruppoSanguigno), (DateUtil.dateParser(dataArrivoMassima))));
				for(Sacca s : saccheTrovate) s.print(System.err);
			}
			 
			 //Nel caso in cui la richiesta non è stata soddisfatta completamente localmente, si crea una notifica di evasione diretta al magazziniere contenente i dati relativi all'evasione
			 //Viene lanciata anche una SaccheInLocaleNotFoundException, che causerà l'inoltro della query di ricerca presso il CCS
			if(numSacche > saccheTrovate.size()) {
				
				List<String> seriali = new ArrayList<String>();
			   
			   for(Sacca s : saccheTrovate) {
				   mm.setPrenotatoSacca(s.getSeriale());
				   seriali.add(s.getSeriale().getSeriale());
			
			   }
			   if(EndPointNotificheMagazziniere.aggiungiNotificaEvasione(seriali, enteRichiedente, indirizzoEnte) == 1) throw new NotificaNonCreataException("Non è stato possibile creare la notifica");
 
				throw new SaccheInLocaleNotFoundException("Non è stato possibile soddisfare completamente la richiesta in locale, verrà contattato il CCS");
			 }
			
			//Nel caso in cui la richiesta è stata soddisfatta completamente localmente, si crea una notifica di evasione diretta al magazziniere contenente i dati relativi all'evasione
			else {
				
				 List<String> seriali = new ArrayList<String>();
					
				  for(int i = 0; i < numSacche; i++) {
					  seriali.add(saccheTrovate.get(i).getSeriale().getSeriale());
					  mm.setPrenotatoSacca(saccheTrovate.get(i).getSeriale());	
				  }
				 
				  NotificaEvasionePublisher publisher = new NotificaEvasionePublisher();
				  publisher.setNotifica(new NotificaEvasione(seriali,enteRichiedente,indirizzoEnte));
				  publisher.run();
				
			
			

			return Response
					.status(Response.Status.OK) //In questo caso sono state trovate tutte le sacche, va inviata risposta 200 OK 
					.entity("Ricerca sacca completata in locale, il magazziniere è stato avvisato per evadere l'ordine")//Va gestito l'inoltro della notifica di evasione sacche presso l'interfaccia REST del magazziniere 
					.build();
			}
		}catch (SaccheInLocaleNotFoundException e) {
			return Response //in realtà deve avviare RicercaSaccaGlobale e passare i parametri al CCS
					.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage())
					.build();
		}catch(DateTimeParseException|IllegalArgumentException e) {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}catch(NotificaNonCreataException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
		
	}


	/**Cerca e restituisce una Sacca all'interno del database delle Sacche che non scada entro una dataArrivoMassima
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 * @throws SaccaNotFoundException
	 */
	private List<Sacca> ricercaSacca(GruppoSanguigno gs, LocalDate dataArrivoMassima) throws SaccaNotFoundException {
		assert(gs!=null) : "Il gruppo sanguigno non può essere nullo!";
		assert(dataArrivoMassima!=null) : "la data di arrivo non può essere nulla!";
		
		
		MongoDataManager mm = new MongoDataManager();
		List<Sacca> saccheTrovate = new ArrayList<Sacca>();
		List<Sacca> listaSacche = mm.getListaSacche();
			
			for (Sacca sacca : listaSacche)
				if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(gs)
						&& sacca.getDataScadenza().isAfter(dataArrivoMassima)
						&& sacca.getDataScadenza().isAfter(LocalDate.now()))
					saccheTrovate.add(sacca);

			saccheTrovate.sort(new ScadenzeComparator());
			

			return saccheTrovate;
		
	}


	/**Cerca e restituisce una Sacca compatibile al gruppo ricercato e che non scada entro una dataArrivoMassima, all'interno del database delle sacche
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 * @throws SaccaNotFoundException
	 */
	private List<Sacca> ricercaSaccaCompatibile(GruppoSanguigno gs, LocalDate dataArrivoMassima) throws SaccaNotFoundException {

		MongoDataManager mm = new MongoDataManager();
			
			List<Sacca> saccheTrovate = new ArrayList<Sacca>();
			List<Sacca> listaSacche = mm.getListaSacche();

			

			Iterator<GruppoSanguigno> i = GruppoSanguigno.puoRicevereDa(gs);
			while(i.hasNext()) {
				GruppoSanguigno grs = i.next();
				for (Sacca sacca : listaSacche)
					if(!sacca.isPrenotato()
							&& sacca.getGruppoSanguigno().equals(grs)
							&& sacca.getDataScadenza().isAfter(dataArrivoMassima)
							&& sacca.getDataScadenza().isAfter(LocalDate.now())
							&& !grs.equals(gs))
						saccheTrovate.add(sacca);
			}

			saccheTrovate.sort(new ScadenzeComparator());
			return saccheTrovate;
				
	}
}