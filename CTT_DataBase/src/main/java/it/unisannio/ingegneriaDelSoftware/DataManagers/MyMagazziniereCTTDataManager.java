package it.unisannio.ingegneriaDelSoftware.DataManagers;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DipendenteCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.MagazziniereCTTDataManager;
import java.time.LocalDate;


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
	
	
	/**Aggiorna i DatiSacca con l'Ente richiedente e la Data di affidamento e rimuove la Sacca dal DataBase 
	 * @param s Sacca da evadere
	 * @param enteRichiedente L'ente a cui la Sacca sarà affidata
	 * @param dataAffidamento Data in cui avverrà l' affidamento
	 * @return etichetta da stampare e inserire sulla Sacca da evadere
	 */
	public String evasioneSacca(Sacca s, String enteRichiedente, LocalDate dataAffidamento) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		mm.removeSacca(s.getSeriale());
		DatiSacca ds = mm.getDatiSacca(s.getSeriale());
		mm.setEnteRichiedenteDatiSacca(ds.getSeriale(), enteRichiedente);
		mm.setDataAffidamentoDatiSacca(ds.getSeriale(), dataAffidamento);
		
		return s.getEtichettaSacca()+ mm.getDatiSacca(s.getSeriale()).getEtichettaDatiSacca();		
	}
	
	
	/**Aggiungere una sacca al DataBase e aggiorna l'Ente donatore dei DatiSacca corrispondenti 
	 * @param unaSacca Sacca da aggiungere al Database
	 * @param enteDonatore Ente dal quale arriva la Sacca
	 */
	public void aggiuntaSaccaMagazzino(Sacca unaSacca, String enteDonatore) {
		MyMongoDataManager mm = new MyMongoDataManager();
		mm.createSacca(unaSacca);
		DatiSacca datiSacca = new DatiSacca(unaSacca.getSeriale(),unaSacca.getGruppoSanguigno(), LocalDate.now(), null, enteDonatore, null);
		mm.createDatiSacca(datiSacca);
	}
}