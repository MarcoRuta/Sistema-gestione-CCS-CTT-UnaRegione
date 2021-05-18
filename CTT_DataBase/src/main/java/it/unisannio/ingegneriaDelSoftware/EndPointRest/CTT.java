package it.unisannio.ingegneriaDelSoftware.EndPointRest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.SaccaNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Interfaces.*;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;;

public class CTT implements CTTFunction {

	/**Restituisce una lista di tutte le sacche che scadono entro 72 ore
	 * @return la lista di sacche non ancora scadute ma che scadono entro 72 ore da oggi
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public List<Sacca> alertControlScadenza() throws SaccaNotFoundException {
		MongoDataManager mm = new MongoDataManager();
		List<Sacca> listaSacche = mm.getListaSacche();
		List<Sacca> saccheInScadenza = new ArrayList<Sacca>();
		Date oggi = new Date();
		long dataScadenza = oggi.getTime();
		long dataScadenza72 = dataScadenza + 259200000;

		for (Sacca sacca : listaSacche){
			if(DateUtil.convertLocalDateToDate(sacca.getDataScadenza()).getTime()>(dataScadenza)
					&& DateUtil.convertLocalDateToDate(sacca.getDataScadenza()).getTime()<(dataScadenza72)) {

				saccheInScadenza.add(sacca);
			}
		}
		return saccheInScadenza;
	}


	/**Rimuove tutte le Sacche scadute dal database delle Sacche e aggiorna i corrispettivi DatiSacca con enteRichiedente "Scaduta" e dataAffidamento con la data di scadenza
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	public void removeSaccheScadute() throws SaccaNotFoundException {
		MongoDataManager mm = new MongoDataManager();
		List<Sacca> listaSacche = mm.getListaSacche();

		for(Sacca sacca : listaSacche) {
			if(sacca.getDataScadenza().isBefore(LocalDate.now())) {
				removeSaccaScaduta(sacca);
			}
		}
	}


	/**Rimuove una Sacca dal Database e setta la dataAffidamento di DatiSacca alla data di scadenza e setta l'enteRichiedente con "Scaduta"
	 * @param s Sacca da rimuovere dal database delle Sacche
	 * @throws SaccaNotFoundException Eccezione che si verifica quando la Sacca inserita non viene trovata
	 */
	private void removeSaccaScaduta(Sacca s) throws SaccaNotFoundException {
		MongoDataManager mm = new MongoDataManager();

		mm.removeSacca(s.getSeriale());
		mm.setDataAffidamentoDatiSacca(s.getSeriale(), s.getDataScadenza());
		mm.setEnteRichiedenteDatiSacca(s.getSeriale(), "Scaduta");
		mm.setIndirizzoEnteDatiSacca(s.getSeriale(), "Scaduta");
	}
}