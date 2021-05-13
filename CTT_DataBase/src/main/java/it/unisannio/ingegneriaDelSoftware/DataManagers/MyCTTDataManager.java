package it.unisannio.ingegneriaDelSoftware.DataManagers;

import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;;

public class MyCTTDataManager implements CTTDataManager{
	
	/**Restituisce una lista di tutte le sacche che scadono entro 72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 72 ore da oggi
	 */
	public List<Sacca> alertControlScadenza() {
		MyOperatoreCTTDataManager mm = new MyOperatoreCTTDataManager();
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
}
