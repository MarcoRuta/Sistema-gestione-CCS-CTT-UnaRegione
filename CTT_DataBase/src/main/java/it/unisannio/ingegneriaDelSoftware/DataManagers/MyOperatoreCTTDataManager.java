package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.time.LocalDate;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DipendenteCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.OperatoreCTTDataManager;

public class MyOperatoreCTTDataManager implements OperatoreCTTDataManager, DipendenteCTT {
	
	/**Login è l'operazione con la quale OperatoreCTT accede al sistema
	 * @param username Username che usa OperatoreCTT per entrare nel sistema
	 * @param password Password che usa OperatoreCTT per entrare nel sistema
	 * @return true se Username e Password corrispondono; false altrimenti
	 */
	public boolean login(String username, String password) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		if(mm.getDipendente(username, password)!= null) return true;
		else return false;
	}
	
	
	/**Restituisce la sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
     * @param gs Gruppo sanguigno ricercato
     * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
     * @param enteRichiedente Ente richiedente della Sacca
     * @return la sacca con le caratteristiche richieste
     */
	public Sacca ricercaSaccaLocale(GruppoSanguigno gs, LocalDate dataArrivoMassima, String enteRichiedente){
		MyMongoDataManager mm = new MyMongoDataManager();
		Sacca s = null;
		
		s = mm.getSaccaPerRicerca(gs, dataArrivoMassima);
		if(s==null) s = mm.getSaccaCompatibilePerRicerca(gs, dataArrivoMassima);
		
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), enteRichiedente);
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), LocalDate.now());
		return s;
	}
}
