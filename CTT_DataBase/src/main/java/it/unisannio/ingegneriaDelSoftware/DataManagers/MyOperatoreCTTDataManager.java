package it.unisannio.ingegneriaDelSoftware.DataManagers;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaLocaleNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.DipendenteCTT;
import it.unisannio.ingegneriaDelSoftware.Interfaces.OperatoreCTTDataManager;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;

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
     * @return la Sacca con le caratteristiche richieste
	 * @throws  
     */
	public Sacca ricercaSaccaLocale(GruppoSanguigno gs, LocalDate dataArrivoMassima, String enteRichiedente){
		MyOperatoreCTTDataManager ms = new MyOperatoreCTTDataManager();
		MyMongoDataManager mm = new MyMongoDataManager();
		
		Sacca s = null;
		s = ms.getSaccaPerRicerca(gs, dataArrivoMassima);
		if(s==null) s = ms.getSaccaCompatibilePerRicerca(gs, dataArrivoMassima);
		
		if(s!=null) {
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), enteRichiedente);
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), LocalDate.now());
		}
		else throw new SaccaLocaleNotFoundException();
		return s;		
	}
	

	/**Cerca e restituisce una Sacca all'interno del database delle Sacche che non scada entro una dataArrivoMassima
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la sacca non è stata trovata; la Sacca se essa è stata trovata 
	 */
	private Sacca getSaccaPerRicerca(GruppoSanguigno gs, LocalDate dataArrivoMassima) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		List<Sacca> listaSacche = mm.getListaSacche();			
		Sacca selez = null;
		Date dataScadenzaImminente = null;
		
		try {
			dataScadenzaImminente = Constants.sdf.parse("01-01-2999");
		} catch (ParseException e) {e.printStackTrace();}
		
		for (Sacca sacca : listaSacche) {
			if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(gs) 
					&& dataScadenzaImminente.after(DateConverter.convertLocalDateToDate(sacca.getDataScadenza())) 
					&& DateConverter.convertLocalDateToDate(sacca.getDataScadenza()).after(DateConverter.convertLocalDateToDate(dataArrivoMassima))) {
			
					dataScadenzaImminente = DateConverter.convertLocalDateToDate(sacca.getDataScadenza());					
					selez = sacca;
			}	
		}
		if(selez != null)mm.setPrenotatoSacca(selez.getSeriale());
		return selez;
	}	
	
	
	/**Cerca e restituisce una Sacca compatibile al gruppo ricercato e che non scada entro una dataArrivoMassima, all'interno del database delle sacche
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 */
	private  Sacca getSaccaCompatibilePerRicerca(GruppoSanguigno gs, LocalDate dataArrivoMassima) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		List<Sacca> listaSacche = mm.getListaSacche();	    		
		Sacca selez = null;
		Date dataScadenzaImminente = null;
		
		try {
			dataScadenzaImminente = Constants.sdf.parse("01-01-2999");
		} catch (ParseException e) {e.printStackTrace();}
		
		Iterator<GruppoSanguigno> i = GruppoSanguigno.puoRicevereDa(gs);		
		while(i.hasNext()) {
			GruppoSanguigno grs = i.next();
			for (Sacca sacca : listaSacche) {
				if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(grs) 
					&& dataScadenzaImminente.after(DateConverter.convertLocalDateToDate(sacca.getDataScadenza())) 
					&& DateConverter.convertLocalDateToDate(sacca.getDataScadenza()).after(DateConverter.convertLocalDateToDate(dataArrivoMassima))) {				
						
					dataScadenzaImminente = DateConverter.convertLocalDateToDate(sacca.getDataScadenza());						
					selez = sacca;
				}	
			}
		}
		
		if(selez != null) mm.setPrenotatoSacca(selez.getSeriale());
		return selez;
	}
}