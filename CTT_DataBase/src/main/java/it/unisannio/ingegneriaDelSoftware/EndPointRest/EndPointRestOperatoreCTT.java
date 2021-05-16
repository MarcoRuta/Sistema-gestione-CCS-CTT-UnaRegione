package it.unisannio.ingegneriaDelSoftware.EndPointRest;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Sacca;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaLocaleNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointLogin;
import it.unisannio.ingegneriaDelSoftware.Interfaces.EndPointOperatoreCTT;
import it.unisannio.ingegneriaDelSoftware.Util.ScadenzeComparator;

public class EndPointRestOperatoreCTT implements EndPointOperatoreCTT{

	/**Restituisce la sacca del GruppoSanguigno richiesto con Data di scadenza più vicina nel DataBase locale.
     * @param gs Gruppo sanguigno ricercato
     * @param dataArrivoMassima Data entro la quale la Sacca non deve scadere e deve arrivare all'Ente richiedente
     * @param enteRichiedente Ente richiedente della Sacca
     * @return la Sacca con le caratteristiche richieste
	 * @throws  
     */
	public Sacca ricercaSaccaLocale(GruppoSanguigno gs, LocalDate dataArrivoMassima, String enteRichiedente, String indirizzoEnte){
		EndPointRestOperatoreCTT ms = new EndPointRestOperatoreCTT();
		MongoDataManager mm = new MongoDataManager();
		
		Sacca s = null;
		s = ms.ricercaSacca(gs, dataArrivoMassima);
		if(s==null) s = ms.ricercaSaccaCompatibile(gs, dataArrivoMassima);
		
		if(s!=null) {
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), enteRichiedente);
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), LocalDate.now());
		mm.setIndirizzoEnteDatiSacca(s.getSeriale(), indirizzoEnte);
		}
		else throw new SaccaLocaleNotFoundException();
		return s;		
	}
	

	/**Cerca e restituisce una Sacca all'interno del database delle Sacche che non scada entro una dataArrivoMassima
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la sacca non è stata trovata; la Sacca se essa è stata trovata 
	 */
	private Sacca ricercaSacca(GruppoSanguigno gs, LocalDate dataArrivoMassima) {
		
		MongoDataManager mm = new MongoDataManager();
		List<Sacca> listaSacche = mm.getListaSacche();	
		List<Sacca> saccheTrovate = new ArrayList<Sacca>();
		
		Sacca selez = null;
		
				
		for (Sacca sacca : listaSacche) 
			if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(gs) && sacca.getDataScadenza().isAfter(dataArrivoMassima) && sacca.getDataScadenza().isAfter(LocalDate.now())) 
				saccheTrovate.add(sacca);
		
		saccheTrovate.sort(new ScadenzeComparator());
		if(!saccheTrovate.isEmpty()) selez = saccheTrovate.get(0);
		
		if(selez != null)mm.setPrenotatoSacca(selez.getSeriale());
		return selez;
	}	
	
	
	/**Cerca e restituisce una Sacca compatibile al gruppo ricercato e che non scada entro una dataArrivoMassima, all'interno del database delle sacche
	 * @param gs Gruppo sanguigno della Sacca che si vuole ricercare
	 * @param dataArrivoMassima Data entro la quale la Sacca non deve essere scaduta
	 * @return null se la Sacca non è stata trovata; la Sacca se essa è stata trovata
	 */
	private  Sacca ricercaSaccaCompatibile(GruppoSanguigno gs, LocalDate dataArrivoMassima) {
		
		MongoDataManager mm = new MongoDataManager();
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Sacca> saccheTrovate = new ArrayList<Sacca>();
		
		Sacca selez = null;
		
		
		Iterator<GruppoSanguigno> i = GruppoSanguigno.puoRicevereDa(gs);		
		while(i.hasNext()) {
			GruppoSanguigno grs = i.next();
			for (Sacca sacca : listaSacche) 
				if(!sacca.isPrenotato() && sacca.getGruppoSanguigno().equals(grs) && sacca.getDataScadenza().isAfter(dataArrivoMassima)&& sacca.getDataScadenza().isAfter(LocalDate.now())) 
					saccheTrovate.add(sacca);
			}
		
		saccheTrovate.sort(new ScadenzeComparator());
		if(!saccheTrovate.isEmpty()) selez = saccheTrovate.get(0);
			
				
		if(selez != null) mm.setPrenotatoSacca(selez.getSeriale());
		return selez;
			
	}
	
}	
	
