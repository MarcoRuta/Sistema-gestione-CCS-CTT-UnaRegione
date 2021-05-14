package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;
import it.unisannio.ingegneriaDelSoftware.Util.DateConverter;;

public class MyCTTDataManager implements CTTDataManager{
	
	/**Restituisce una lista di tutte le sacche che scadono entro 72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 72 ore da oggi
	 */
	public List<Sacca> alertControlScadenza() {
		MyCTTDataManager mm = new MyCTTDataManager();
		return mm.getSaccheEntroScadenza();
	}
	
	
	/**Rimuove una Sacca dal Database e setta la dataAffidamento di DatiSacca alla data di scadenza e setta l'enteRichiedente con "Scaduta"
	 * @param s Sacca da rimuovere dal database delle Sacche
	 */
	public void removeSaccaScaduta(Sacca s) {
		MyMongoDataManager mm = new MyMongoDataManager();		
		mm.removeSacca(s.getSeriale());
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), s.getDataScadenza());
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), "Scaduta");

	}
	
	
	/** Restituisce una lista contenente tutte le Sacche con scadenza inferiore a 72 ore da oggi
	 * @return la lista delle Sacche in scadenza
	 */
	private List<Sacca> getSaccheEntroScadenza() {
    	MyMongoDataManager mm = new MyMongoDataManager();
		
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Sacca> saccheInScadenza = new ArrayList<Sacca>();
		
        Date oggi = new Date();
        long dataScadenza = oggi.getTime();
        long dataScadenza72 = dataScadenza + 259200000;

        for (Sacca sacca : listaSacche){
            if(DateConverter.convertLocalDateToDate(sacca.getDataScadenza()).getTime()>(dataScadenza) 
            		&& DateConverter.convertLocalDateToDate(sacca.getDataScadenza()).getTime()<(dataScadenza72)) {

            	saccheInScadenza.add(sacca);
            }
        }
        return saccheInScadenza;
    }
	
}
