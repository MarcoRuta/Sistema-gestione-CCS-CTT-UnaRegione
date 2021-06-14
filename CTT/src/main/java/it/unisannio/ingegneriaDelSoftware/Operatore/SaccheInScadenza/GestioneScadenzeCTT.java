package it.unisannio.ingegneriaDelSoftware.Operatore.SaccheInScadenza;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Wrapper.SaccaWrapper;
import it.unisannio.ingegneriaDelSoftware.CttRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;

@Component
public class GestioneScadenzeCTT implements CTTFunction {

	private MongoDataManager mm = MongoDataManager.getInstance();


	/**Metodo che viene schedulato attraverso spring nella classe CttDataBaseRestApplication.
	 * Viene eseguito ogni giorno all'1 di notte, elimina tutte le sacche scadute presenti nel database(con opportuna notifica al magazziniere) e notifica al CCS le sacche in scadenza tra 48-72h
	 * notazione cron, <minute> <hour> <day-of-month> <month> <day-of-week> <command>
	 * 0 1 -> 1:00
	 * * * ? -> Tutti i giorni dell'anno
	 * @throws EntityNotFoundException Se non ci sono sacche in scadenza
	 */
	@Scheduled(initialDelay = 1000*5, fixedRate = 1000*60*3)
	public void alertSaccheInScadenza() throws EntityNotFoundException {

		//recupero le sacche in scadenza
		List<Sacca> saccheInScadenza = getSaccheInScadenza();

		if(ConnectionVerifier.isCCSOnline() && !saccheInScadenza.isEmpty()) {
			try {
				SaccheInScadenzaClientEndPoint.session.getBasicRemote().sendObject(new SaccaWrapper(saccheInScadenza));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EncodeException e) {
				e.printStackTrace();
			}
		}

	}

	/**Restituisce una lista di tutte le sacche che scadono entro le prossime 72 ore
	 * @return List<Sacca> la lista di sacche non ancora scadute ma che scadono entro 72 ore a partire da LocalDate.now()
	 *@throws EntityNotFoundException Se non ci sono sacche in scadenza
	 */
	public List<Sacca> getSaccheInScadenza() throws EntityNotFoundException {
		
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Sacca> saccheInScadenza = new ArrayList<Sacca>();


		for (Sacca sacca : listaSacche)
			if( !this.isScaduta(sacca) && ( sacca.getDataScadenza().isBefore(LocalDate.now().plusDays(3)) || sacca.getDataScadenza().equals(LocalDate.now().plusDays(3)))
			&& !(sacca.isPrenotato()) ) {
				saccheInScadenza.add(sacca);
				CttRestApplication.logger.info("Sacca in scadenza aggiunta: "+sacca.getSeriale().getSeriale());
			}

		return saccheInScadenza;
	}


	/**Restituisce true se la sacca è scaduta*/
	public boolean isScaduta(Sacca s) {
		return  (LocalDate.now().isEqual(s.getDataScadenza()) || LocalDate.now().isAfter(s.getDataScadenza()));
	}





	
}