package it.unisannio.ingegneriaDelSoftware.SaccheInScadenzaManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Classes.Notifiche.NotificaSmaltimentoSacche;
import it.unisannio.ingegneriaDelSoftware.ClientRest.CTTRestClient;
import it.unisannio.ingegneriaDelSoftware.CttDataBaseRestApplication;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.EndPointRest.Magazziniere.EndPointNotificheMagazziniere;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Functional.ConnectionVerifier;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;


public class GestioneScadenzeCTT implements CTTFunction {

	private MongoDataManager mm = MongoDataManager.getInstance();




	/**Metodo che viene schedulato attraverso spring nella classe CttDataBaseRestApplication.
	 * Viene eseguito ogni giorno all'1 di notte, elimina tutte le sacche scadute presenti nel database(con opportuna notifica al magazziniere) e notifica al CCS le sacche in scadenza tra 48-72h
	 * notazione cron, <minute> <hour> <day-of-month> <month> <day-of-week> <command>
	 * 0 1 -> 1:00
	 * * * ? -> Tutti i giorni dell'anno
	 * @throws EntityNotFoundException Se non ci sono sacche in scadenza
	 */
    public void alertSaccheInScadenza() throws EntityNotFoundException {
    	//rimuovo eventuali saccheScadute
		removeSaccheScadute();

		//recupero le sacche in scadenza
		List<Sacca> saccheInScadenza = getSaccheInScadenza();
		
		
		if(ConnectionVerifier.isCCSOnline() && !saccheInScadenza.isEmpty()) {
			CTTRestClient.notifySaccaInScadenzaToCCS(saccheInScadenza);
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
			if( ( sacca.getDataScadenza().isBefore(LocalDate.now().plusDays(3)) || sacca.getDataScadenza().equals(LocalDate.now().plusDays(3)))
			&& !(sacca.isPrenotato())) {
				saccheInScadenza.add(sacca);
				CttDataBaseRestApplication.logger.info("Sacca in scadenza aggiunta: "+sacca.getSeriale().getSeriale());
			}

		return saccheInScadenza;
	}


	/**Rimuove tutte le sacche scadute dal database delle sacche e aggiorna i corrispettivi DatiSacca
	 * con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza.
	 * Notifica il MagazziniereObserver con le sacche da smaltire
	 * @throws EntityNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public void removeSaccheScadute() throws EntityNotFoundException  {
		List<Seriale> serialiDaSmaltire = new ArrayList<>();
		for(Sacca sacca : mm.getListaSacche())
			if(sacca.getDataScadenza().isBefore(LocalDate.now())) {
				serialiDaSmaltire.add(sacca.getSeriale());
				removeSaccaScaduta(sacca);
			}
		if(!(serialiDaSmaltire.isEmpty()))
			new EndPointNotificheMagazziniere().notifyMagazziniereObserver(new NotificaSmaltimentoSacche(serialiDaSmaltire));
	}


	/**Rimuove tutte le sacche scadute dal database delle sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
	 * @param s Sacca da rimuovere dal database delle Sacche
	 * @throws EntityNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	private void removeSaccaScaduta(Sacca s) throws EntityNotFoundException {

		mm.removeSacca(s.getSeriale());
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), s.getDataScadenza());
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), "Scaduta");
		mm.setIndirizzoEnteDatiSacca(s.getSeriale(), "Scaduta");
		CttDataBaseRestApplication.logger.info("Ho rimosso la sacca: "+s.getSeriale().getSeriale()+" perchè era scaduta!");
	}


	
}