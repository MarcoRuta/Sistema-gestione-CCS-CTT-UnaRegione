package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.ingegneriaDelSoftware.Annotazioni.Secured;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaLocaleNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import it.unisannio.ingegneriaDelSoftware.Util.ScadenzeComparator;

//#######################################come mai si chiama CTT invece che OperatoreCTT come tutti gli altri?? LRM
@Path("/CTT")
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
	@PUT
	@Path("/ricerca")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response ricercaSaccaLocale(@FormParam("gruppoSanguigno") String gruppoSanguigno,
									   @FormParam("dataArrivoMassima") String dataArrivoMassima,
									   @FormParam("enteRichiedente") String enteRichiedente,
									   @FormParam("indirizzoEnte") String indirizzoEnte) throws SaccaNotFoundException{
		MongoDataManager mm = new MongoDataManager();
		Sacca s = null;
		try {
			s = ricercaSacca(GruppoSanguigno.valueOf(gruppoSanguigno), DateUtil.dateParser(dataArrivoMassima));

			if(s==null) s = ricercaSaccaCompatibile(GruppoSanguigno.valueOf(gruppoSanguigno), DateUtil.dateParser(dataArrivoMassima));
			
			mm.setPrenotatoSacca(s.getSeriale());
			
			return Response
					.status(Response.Status.OK)
					.entity(s)
					.build();
		}catch (SaccaLocaleNotFoundException e) {
			return Response //in realtà deve avviare RicercaSaccaGlobale e passare i parametri enteRichiedente e indirizzoEnte
					.status(Response.Status.NOT_FOUND)
					.entity("Sacca non presente nel Database")
					.build();
		}catch(DateTimeParseException|IllegalArgumentException e) {
			return Response
					.status(Response.Status.BAD_REQUEST)
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
	private Sacca ricercaSacca(GruppoSanguigno gs, LocalDate dataArrivoMassima) throws SaccaNotFoundException {
		assert(gs!=null);
		assert(dataArrivoMassima!=null);

		MongoDataManager mm = new MongoDataManager();
		List<Sacca> saccheTrovate = new ArrayList<Sacca>();
		try {
			List<Sacca> listaSacche = mm.getListaSacche();
			

			Sacca selez = null;

			for (Sacca sacca : listaSacche)
				if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(gs)
						&& sacca.getDataScadenza().isAfter(dataArrivoMassima)
						&& sacca.getDataScadenza().isAfter(LocalDate.now()))
					saccheTrovate.add(sacca);

			saccheTrovate.sort(new ScadenzeComparator());
			if(!saccheTrovate.isEmpty()) selez = saccheTrovate.get(0);

			return selez;
		}catch (AssertionError e) {
			return null;
		}
		
	}


	/**Cerca e restituisce una Sacca compatibile al gruppo ricercato e che non scada entro una dataArrivoMassima, all'interno del database delle sacche
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 * @throws SaccaNotFoundException
	 */
	private Sacca ricercaSaccaCompatibile(GruppoSanguigno gs, LocalDate dataArrivoMassima) throws SaccaNotFoundException {

		MongoDataManager mm = new MongoDataManager();
		List<Sacca> saccheTrovate = new ArrayList<Sacca>();
		try {
			List<Sacca> listaSacche = mm.getListaSacche();

			Sacca selez = null;

			Iterator<GruppoSanguigno> i = GruppoSanguigno.puoRicevereDa(gs);
			while(i.hasNext()) {
				GruppoSanguigno grs = i.next();
				for (Sacca sacca : listaSacche)
					if(!sacca.isPrenotato()
							&& sacca.getGruppoSanguigno().equals(grs)
							&& sacca.getDataScadenza().isAfter(dataArrivoMassima)
							&& sacca.getDataScadenza().isAfter(LocalDate.now()))
						saccheTrovate.add(sacca);
			}

			saccheTrovate.sort(new ScadenzeComparator());
			if(!saccheTrovate.isEmpty()) selez = saccheTrovate.get(0);

			return selez;		
		}catch (AssertionError e) {
			return null;
		}
	}
}