package it.unisannio.ingegneriaDelSoftware.SaccheInScadenzaManager;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;

public class GestioneScadenzeCTT implements CTTFunction {
	Map<Seriale,Sacca> saccheInviate = new HashMap<>();
	Client client = ClientBuilder.newClient();
	WebTarget gestioneSaccheInscadenza = client.target(Constants.CCSIP+"/rest/CCS/saccheInScadenza");
	MongoDataManager mm = MongoDataManager.getInstance();
    

	   
	/** Metodo che viene schedulato attraverso spring nella classe CttDataBaseRestApplication
	   *  viene eseguito ogni giorno all'1 di notte
	   *  Elimina tutte le sacche scadute presenti nel database e notifica al CCS le sacche in scadenza tra 48-72h
	   *  notazione cron, <minute> <hour> <day-of-month> <month> <day-of-week> <command>
	   *   0 1 -> 1:00 
	   *   * * ? -> Tutti i giorni dell'anno
	 * @throws EntityNotFoundException 
	   */
    public void alertSaccheInScadenza() throws EntityNotFoundException {
    		//rimuovo eventuali saccheScadute
	    	removeSaccheScadute();

	    	//recupero le sacche in scadenza
	    	List<Sacca> saccheInScadenza = getSaccheInScadenza();
	    	if(!saccheInScadenza.isEmpty())
	    		gestioneSaccheInscadenza.request().post(Entity.json(saccheInScadenza));

	   
	}
	
	/**Restituisce una lista di tutte le sacche che scadono entro le prossime 48-72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 48-72 ore da oggi
	 *Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public List<Sacca> getSaccheInScadenza() throws EntityNotFoundException {
		
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Sacca> saccheInScadenza = new ArrayList<Sacca>();

		for (Sacca sacca : listaSacche){
			if( (sacca.getDataScadenza().isBefore(LocalDate.now().plusDays(3))
					|| sacca.getDataScadenza().equals(LocalDate.now().plusDays(3)))
		   && (sacca.getDataScadenza().isAfter(LocalDate.now().plusDays(2)) 
				   || sacca.getDataScadenza().equals(LocalDate.now().plusDays(2)))
			&& !(this.saccheInviate.containsKey(sacca.getSeriale())))
				saccheInScadenza.add(sacca);
		}
		return saccheInScadenza;
	}


	/**Rimuove tutte le Sacche scadute dal database delle Sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
	 * @throws EntityNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public void removeSaccheScadute() throws EntityNotFoundException  {
		List<Sacca> listaSacche = mm.getListaSacche();

		for(Sacca sacca : listaSacche) {
			if(sacca.getDataScadenza().isBefore(LocalDate.now())) {
				removeSaccaScaduta(sacca);
				if (this.saccheInviate.containsKey(sacca.getSeriale()))
					this.saccheInviate.remove(sacca.getSeriale());
			}
		}
	}


	/**Rimuove una Sacca dal Database e setta la dataAffidamento di DatiSacca alla data di scadenza e setta l'enteRichiedente con "Scaduta"
	 * @param s Sacca da rimuovere dal database delle Sacche
	 * @throws EntityNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	private void removeSaccaScaduta(Sacca s) throws EntityNotFoundException {

		mm.removeSacca(s.getSeriale());
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), s.getDataScadenza());
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), "Scaduta");
		mm.setIndirizzoEnteDatiSacca(s.getSeriale(), "Scaduta");
	}


	
}